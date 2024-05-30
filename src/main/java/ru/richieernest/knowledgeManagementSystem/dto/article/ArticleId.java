package ru.richieernest.knowledgeManagementSystem.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleId {
    private List<ArticleLinkBranchDto> articleLinkBranchDto;
    private Article article;
}
