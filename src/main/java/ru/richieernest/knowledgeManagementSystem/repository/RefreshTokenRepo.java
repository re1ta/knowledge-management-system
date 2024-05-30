package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;
import ru.richieernest.knowledgeManagementSystem.entity.RefreshToken;

import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);

    @Query(value = "SELECT a FROM RefreshToken a WHERE a.employee = :EMP")
    Optional<RefreshToken> findByEmployee(@Param("EMP") Employee employee);

    @Transactional
    @Modifying
    @Query("update RefreshToken a set a.token = ?1, a.expiryDate = ?2 WHERE a.employee = ?3")
    void updateToken(String token, Instant time, Employee employee);

}
