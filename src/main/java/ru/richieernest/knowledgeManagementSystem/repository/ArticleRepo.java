package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.dto.article.ArticleLink;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    @Query(value = "SELECT * FROM Article ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Article lastArticle();
    @Query("SELECT a FROM Article a WHERE a.articleParentId IS NULL")
    List<Article> findAllRootArticles();
    @Query(value = "SELECT new ru.richieernest.knowledgeManagementSystem.dto.article.ArticleLink(a.id, a.title) FROM Article a WHERE a.articleParentId IS NULL")
    List<ArticleLink> findAllArticleLink();

    @Query(value = "SELECT new ru.richieernest.knowledgeManagementSystem.dto.article.ArticleLink(a.id, a.title) FROM Article a WHERE a.articleParentId = :ParentId")
    List<ArticleLink> findByParentId(@Param("ParentId") Long id);

    @Query(value = "SELECT new ru.richieernest.knowledgeManagementSystem.dto.article.ArticleLink(a.id, a.title) FROM Article a WHERE a.id = :idArt")
    ArticleLink findByIdArticleLink(@Param("idArt") Long id);

    List<Article> findAll();
    @Query("SELECT new ru.richieernest.knowledgeManagementSystem.dto.article.ArticleLink(a.id, a.title) FROM Article a WHERE a.articleParentId = :articleParentId")
    List<ArticleLink> findArticleIdAndTitleByArticleParentId(@Param("articleParentId") Long articleParentId);

    @Query("SELECT a.articleParentId FROM Article a WHERE a.id = :id")
    Long findArticleParentIdById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE Article SET content = ?1, title = ?2 WHERE id = ?3", nativeQuery = true)
    void updateContent(String content, String title, Long id);

    List<Article> findByArticleParentId(Long articleParentId);

}
