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
public class ArticleLinkBranchDto {
    private ArticleLink articleLink;
    private List<ArticleLinkBranchDto> childArticles;
}
