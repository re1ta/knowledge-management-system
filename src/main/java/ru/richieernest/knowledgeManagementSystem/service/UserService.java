package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public void changePass(String password, String id){
        var email = jwtService.extractUsername(id);
        var pass = passwordEncoder.encode(password);
        System.out.println(pass);
        employeeRepo.updatePass(pass, email);
    }
}
