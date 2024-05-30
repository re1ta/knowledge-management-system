package ru.richieernest.knowledgeManagementSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.exception.NotFoundException;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;

@Component
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee userInfo = employeeRepo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username %s not found", username));
        return new UserInfoDetails(userInfo);
    }
}
