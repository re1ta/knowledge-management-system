package ru.richieernest.knowledgeManagementSystem.dto.historyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateVersionDto {

    private String title;
    private String content;
    private Long id_article;
    private Long id_version;

}
