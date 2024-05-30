package ru.richieernest.knowledgeManagementSystem.dto.historyDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextDto {
    private String title;
    private String content;
}
