package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String createdAt;
    private String content;
    private Long articleParentId;

}
