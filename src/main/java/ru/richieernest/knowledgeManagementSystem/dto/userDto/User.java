package ru.richieernest.knowledgeManagementSystem.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;

    private String password;

    private String name;

    private String surname;

    private String patronymic;

    private String email;
}
