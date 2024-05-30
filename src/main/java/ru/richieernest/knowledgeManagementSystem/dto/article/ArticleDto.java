package ru.richieernest.knowledgeManagementSystem.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String author;
    private String createdAt;
    private String content;
    private Long articleParentId;

    private List<ArticleLink> childArticles;
}
