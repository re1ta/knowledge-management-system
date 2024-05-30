package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.ArticleVersion;

@Repository
public interface ArticleVersionRepo extends JpaRepository<ArticleVersion, Long> {

    @Modifying
    @Query(value = "UPDATE article_version SET id_version = ?2 WHERE id_article = ?1", nativeQuery = true)
    void changeVersion(Long id_article, Long id_version);
}
