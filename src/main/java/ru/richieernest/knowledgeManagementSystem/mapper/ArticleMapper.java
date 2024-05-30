package ru.richieernest.knowledgeManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.dto.article.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.dto.article.ArticlePostRequestDto;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

@RequiredArgsConstructor
@Component
public class ArticleMapper {

    private final ArticleRepo articleRepo;
    public ArticleDto toDto(Article article){

        ArticleDto articleDto = ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .createdAt(article.getCreatedAt())
                .content(article.getContent())
                .articleParentId(article.getArticleParentId())
                .childArticles(articleRepo.findArticleIdAndTitleByArticleParentId(article.getId()))
                .build();

        return articleDto;
    }

    //метод для преобразования ArticlePostRequestDto в Article
    public Article toArticle(ArticlePostRequestDto articlePostRequestDto){
        Article article = Article.builder()
                .title(articlePostRequestDto.getTitle())
                .author(articlePostRequestDto.getAuthor())
                .content(articlePostRequestDto.getContent())
                .articleParentId(articlePostRequestDto.getArticleParentId())
                .build();
        return article;
    }
}
