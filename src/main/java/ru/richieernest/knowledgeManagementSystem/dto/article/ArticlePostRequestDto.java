package ru.richieernest.knowledgeManagementSystem.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePostRequestDto {
    private String title;
    private String author;
    private String content;
    private Long articleParentId;
}
