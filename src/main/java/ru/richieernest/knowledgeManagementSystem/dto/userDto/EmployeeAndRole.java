package ru.richieernest.knowledgeManagementSystem.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeAndRole {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String role;
}
