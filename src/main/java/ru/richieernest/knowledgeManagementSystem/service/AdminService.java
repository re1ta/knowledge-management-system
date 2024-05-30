package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.EmployeeAndRole;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.User;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.UserRole;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    public List<EmployeeAndRole> findAllEmployees(){
        List<Employee> employees = employeeRepo.getEmployees();
        List<EmployeeAndRole> employeeAndRoles = new ArrayList<>();
        for(Employee e : employees){
            EmployeeAndRole ear = EmployeeAndRole.builder()
                    .id(e.getId())
                    .username(e.getUsername())
                    .password(e.getPassword())
                    .name(e.getName())
                    .surname(e.getSurname())
                    .patronymic(e.getPatronymic())
                    .email(e.getEmail())
                    .role(String.valueOf(e.getRoles().toArray()[0]).toLowerCase())
                    .build();
            employeeAndRoles.add(ear);
        }
        return employeeAndRoles;
    }
    @Transactional
    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    @Transactional
    public void createAdmin(User user) {
        Employee employee = Employee.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singleton(Role.ADMIN))
                .build();

        employeeRepo.save(employee);
    }
    @Transactional
    public void changeUserRole(UserRole userRole){
        System.out.println(userRole.getId() + " " + userRole.getRole());
        employeeRepo.updateRole(userRole.getId(),userRole.getRole());
    }
}
