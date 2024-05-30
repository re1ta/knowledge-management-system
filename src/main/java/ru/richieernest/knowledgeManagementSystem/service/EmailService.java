package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JwtService jwtService;
    public void sendEmailMessage(String email){
        Properties properties = new Properties();

        String from = "ernest.ibatov@mail.ru";
        String to = email;
        String password_mail = "sp1smauE0MaxsGuvGAvM";

        properties.put("mail.smtp.host","smtp.mail.ru");
        properties.put("mail.smtp.post","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password_mail);
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            String token = jwtService.generateToken(email,300000);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, to);
            message.setSubject("Замена пароля");
            message.setText("Перейдите по ссылке, которая действует 5 минут, чтобы поменять пароль: http://localhost:55555/forgetPassword/" + token);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
