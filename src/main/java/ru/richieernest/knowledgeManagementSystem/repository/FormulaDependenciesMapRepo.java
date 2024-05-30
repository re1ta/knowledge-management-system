package ru.richieernest.knowledgeManagementSystem.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.FormulaDependenciesMap;

import java.util.List;
@Repository
public interface FormulaDependenciesMapRepo extends JpaRepository<FormulaDependenciesMap, Long> {

    @Query(value = "SELECT * FROM formula_dependencies_map WHERE parent_formula_id = ?1", nativeQuery = true)
    List<FormulaDependenciesMap> findByParentId(Long parentId);

    @Query(value = "SELECT * FROM formula_dependencies_map WHERE parent_formula_id = ?1", nativeQuery = true)
    List<FormulaDependenciesMap> findLettersByParentId(Long parentId);

    @Modifying
    @Query(value = "DELETE FROM formula_dependencies_map WHERE letter = ?1", nativeQuery = true)
    void deleteByLetter(String l);

    @Modifying
    @Query(value = "DELETE FROM formula_dependencies_map WHERE parent_formula_id = ?1", nativeQuery = true)
    void deleteByParentId(Long ParentId);


    @Modifying
    @Query(value = "SELECT * FROM formula_dependencies_map WHERE child_formula_id = ?1", nativeQuery = true)
    List<FormulaDependenciesMap> findByChildId(Long parentId);
}
