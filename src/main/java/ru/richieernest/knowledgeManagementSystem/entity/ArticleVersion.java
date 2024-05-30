package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articleVersion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleVersion {

    @Id
    private Long id_article;
    private Long id_version;
}
