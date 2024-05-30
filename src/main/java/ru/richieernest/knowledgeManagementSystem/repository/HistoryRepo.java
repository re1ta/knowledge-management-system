package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.VersionDto;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryArticle, Long> {

    @Query(value = "SELECT new ru.richieernest.knowledgeManagementSystem.dto.historyDto.VersionDto(a.id, a.createdAt) FROM HistoryArticle a WHERE a.id_article = :idArt")
    List<VersionDto> findAllByIdArticle(@Param("idArt") Long id);

    @Query(value = "SELECT a FROM HistoryArticle a WHERE a.id = :ID")
    HistoryArticle findByIdVersion(@Param("ID") Long id);

    @Modifying
    @Query(value = "DELETE FROM history_article WHERE id = ?1",nativeQuery = true)
    void deleleByIdVersion(Long id);

    @Modifying
    @Query(value = "DELETE FROM history_article WHERE id_article = ?1",nativeQuery = true)
    void deleleAllById(Long id);
}
