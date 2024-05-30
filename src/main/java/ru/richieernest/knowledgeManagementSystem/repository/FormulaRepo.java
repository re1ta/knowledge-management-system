package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
@Repository
public interface FormulaRepo extends JpaRepository<Formula, Long> {

    @Modifying
    @Query(value = "UPDATE Formula SET formula = ?1, result = ?2, title = ?3 WHERE id = ?4", nativeQuery = true)
    void updateFormula(String formula, Double result, String title, Long id);

    @Modifying
    @Query(value = "DELETE FROM formula WHERE id = ?1", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "SELECT result FROM formula a WHERE a.id = :id", nativeQuery = true)
    Double findResultById(@Param("id") Long id);

    @Query(value = "SELECT id FROM formula ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long lastAddId();

    @Query(value = "SELECT * FROM formula ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Formula lastAddFormula();

    @Query(value = "SELECT * FROM formula a WHERE a.id = :id", nativeQuery = true)
    Formula searchFormulaById(@Param("id") Long id);

    @Query(value = "SELECT result FROM formula a WHERE a.letter = :letter", nativeQuery = true)
    Long searchResultFormulaByLetter(@Param("letter") String letter);
}
