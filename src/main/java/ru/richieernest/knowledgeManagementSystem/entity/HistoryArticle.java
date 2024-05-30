package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "historyArticle")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HistoryArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_article;
    private String title;
    private String content;
    private String createdAt;
}
