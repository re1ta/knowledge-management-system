package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formulaDependenciesMap")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FormulaDependenciesMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long parentFormulaId;
    private Long childFormulaId;
    private String letter;
}
