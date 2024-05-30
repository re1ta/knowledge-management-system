package ru.richieernest.knowledgeManagementSystem.service.Auth;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.RefreshToken;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import ru.richieernest.knowledgeManagementSystem.repository.RefreshTokenRepo;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {


    private final RefreshTokenRepo refreshTokenRepo;

    private final EmployeeRepo employeeRepo;

    @Transactional
    public RefreshToken createRefreshToken(String username) {
        Employee employee = employeeRepo.findByUsername(username).get();
        RefreshToken refreshToken = RefreshToken.builder()
                .employee(employee)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))//10
                .build();
        if(refreshTokenRepo.findByEmployee(employee).isEmpty()) {
            return refreshTokenRepo.save(refreshToken);
        }
        else {
            refreshTokenRepo.updateToken(refreshToken.getToken(), refreshToken.getExpiryDate(), employee);
            return refreshToken;
        }
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}
