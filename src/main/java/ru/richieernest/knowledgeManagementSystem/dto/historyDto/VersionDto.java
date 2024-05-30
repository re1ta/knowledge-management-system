package ru.richieernest.knowledgeManagementSystem.dto.historyDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionDto {

    private Long id_version;
    private String createdAt;
}
