package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.User;
import ru.richieernest.knowledgeManagementSystem.dto.token.JwtResponse;
import ru.richieernest.knowledgeManagementSystem.entity.RefreshToken;
import ru.richieernest.knowledgeManagementSystem.service.Auth.AuthService;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;
import ru.richieernest.knowledgeManagementSystem.service.Auth.RefreshTokenService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/registration")
    public String registration(@RequestBody User user){
        authService.createUser(user);
        return "registration";
    }

    @GetMapping("/login")
    public void login(Principal principal){
        userDetailsService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(user.getUsername(), 120000))
                    .token(refreshToken.getToken()).build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/logout")
    public void logout(){
    }
}
