package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.article.*;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.mapper.ArticleMapper;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;


    public ArticleAndChildrenDto getArticleChildren(Long id){
        ArticleAndChildrenDto AACD = ArticleAndChildrenDto.builder()
                .childArticles(articleRepo.findByParentId(id))
                .article(getArticleById(id))
                .build();
        return AACD;
    }
    public Article getArticleById(Long id){
        return articleRepo.findById(id).orElseThrow(() -> new RuntimeException("Статья не найдена с id: " + id));
    }

    public ArticleId loadTreeAndArticle(Long id){
        return ArticleId.builder()
                .articleLinkBranchDto(getArticleBranches(id))
                .article(getArticleById(id))
                .build();
    }

    public List<ArticleLinkBranchDto> getArticleBranches(Long id) {
        List<Long> pathToRoot = findPathToRoot(id);

        // Получаем список всех корневых статей
        List<ArticleLink> rootArticles = articleRepo.findAllArticleLink();

        // Строим ветку для каждой корневой статьи, если она присутствует в pathToRoot
        return rootArticles.stream()
                .map(rootArticle -> {
                    if (pathToRoot.contains(rootArticle.getId())) {
                        return buildBranch(rootArticle.getId(), id, pathToRoot);
                    } else {
                        ArticleLinkBranchDto branchDto = new ArticleLinkBranchDto();
                        branchDto.setArticleLink(rootArticle);
                        return branchDto;
                    }
                })
                .collect(Collectors.toList());
    }

    //method for getting the id and title of the main articles
    public List<ArticleLink> getAllRootArticleLinks() { return articleRepo.findAllArticleLink(); }

    public Article addArticle(Article article) {
        Article art = articleRepo.lastArticle();
        if(art!=null) {
            if (!art.getContent().equals(article.getContent())) {
                return articleRepo.save(article);
            }
        }
        return articleRepo.save(article);
    }

    public List<Article> addArticles(List<ArticlePostRequestDto> newArticles) {
        List<Article> articles = newArticles.stream().map(articleMapper::toArticle).collect(Collectors.toList());
        return articleRepo.saveAll(articles);
    }

    public Article updateArticle(Article article) {
        return addArticle(article);
    }

    public void deleteById(Long id) {
        articleRepo.deleteById(id);
    }

    private List<Long> findPathToRoot(Long id) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Статья не найдена с id: " + id));

        List<Long> pathToRoot;
        if (article.getArticleParentId() == null) {
            // Если у статьи нет родительской статьи, значит она является корневой
            pathToRoot = new ArrayList<>();
        } else {
            // Если у статьи есть родительская статья, рекурсивно вызываем метод для родительской статьи
            pathToRoot = findPathToRoot(article.getArticleParentId());
        }
        pathToRoot.add(article.getId());
        return pathToRoot;
    }
    private ArticleLinkBranchDto buildBranch(Long rootId, Long targetId, List<Long> pathToRoot) {
        ArticleLink rootArticle = articleRepo.findByIdArticleLink(rootId);

        ArticleLinkBranchDto branchDto = new ArticleLinkBranchDto();
        branchDto.setArticleLink(rootArticle);

        if (rootId.equals(targetId) || pathToRoot.contains(rootId)) {
            List<ArticleLinkBranchDto> childBranches = articleRepo.findByArticleParentId(rootId).stream()
                    .map(childArticle -> buildBranch(childArticle.getId(), targetId, pathToRoot))
                    .collect(Collectors.toList());
            branchDto.setChildArticles(childBranches);
        }

        return branchDto;
    }
}
