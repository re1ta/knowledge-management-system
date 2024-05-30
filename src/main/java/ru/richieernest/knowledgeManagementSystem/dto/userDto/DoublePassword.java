package ru.richieernest.knowledgeManagementSystem.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoublePassword {

    private String password;

    private String repeatPassword;

}
