package ru.richieernest.knowledgeManagementSystem.config;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;

import java.io.Serial;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class UserInfoDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = -4612525987873300081L;

    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employee.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRoleName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
