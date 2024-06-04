package ru.richieernest.knowledgeManagementSystem.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeAndRole {

    private Long id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Date regDate;

    private String role;
}
