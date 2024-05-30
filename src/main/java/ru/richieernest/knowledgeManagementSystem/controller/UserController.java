package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.token.JwtResponse;
import ru.richieernest.knowledgeManagementSystem.dto.token.RefreshTokenRequest;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.RefreshToken;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;
import ru.richieernest.knowledgeManagementSystem.service.Auth.RefreshTokenService;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/admin")
    public void admin(){
        Employee employee = Employee.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Collections.singleton(Role.ADMIN))
                .email("ernest.ibatov@mail.ru")
                .build();
        employeeRepo.save(employee);
    }
    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getEmployee)
                .map(employee -> {
                    String accessToken = jwtService.generateToken(employee.getUsername(),60000);
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}
