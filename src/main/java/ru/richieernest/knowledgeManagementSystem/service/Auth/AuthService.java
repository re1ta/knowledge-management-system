package ru.richieernest.knowledgeManagementSystem.service.Auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.User;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.Role;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService{

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(User user) {
       Employee employee = Employee.builder()
              .username(user.getUsername())
               .password(passwordEncoder.encode(user.getPassword()))
               .name(user.getName())
               .surname(user.getSurname())
               .patronymic(user.getPatronymic())
               .email(user.getEmail())
               .roles(Collections.singleton(Role.READER))
               .build();

       employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployeeByPrincipal(Principal principal) {
        return employeeRepo.findByUsername(principal.getName());
    }
}
