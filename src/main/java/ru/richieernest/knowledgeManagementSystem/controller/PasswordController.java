package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.userDto.DoublePassword;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;
import ru.richieernest.knowledgeManagementSystem.service.EmailService;
import ru.richieernest.knowledgeManagementSystem.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgetPassword")
public class PasswordController {

    private final JwtService jwtService;
    private final EmailService emailService;
    private final UserService userService;

    @PostMapping("/")
    public void forgetPassword(@RequestBody String email){
        emailService.sendEmailMessage(email);
    }

    @GetMapping("/{id}")
    public void changePasswordToken(@PathVariable String id){
        if (!jwtService.isTokenExpired(id)){
            System.out.println("Переход на POST");
        }
        else{
            throw new RuntimeException("Срок ссылки истёк!");
        }
    }
    @PostMapping("/{id}")
    public void changePassword(@PathVariable String id, @RequestBody DoublePassword doublePassword){
        if (doublePassword.getPassword().compareTo(doublePassword.getRepeatPassword()) == 0){
            userService.changePass(doublePassword.getPassword(), id);
        }
    }
}
