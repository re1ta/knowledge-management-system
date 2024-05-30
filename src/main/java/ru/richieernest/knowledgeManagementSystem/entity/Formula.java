package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formula")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String formula;

    private Double result;

}
