package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    void deleteById(Long id);

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getEmployees();

    @Modifying
    @Query(value = "UPDATE employee SET password = ?1 WHERE email = ?2", nativeQuery = true)
    void updatePass(String pass, String email);

    @Modifying
    @Query(value = "UPDATE user_role SET roles = ?2 WHERE user_username = ?1", nativeQuery = true)
    void updateRole(Long id, String role);
}