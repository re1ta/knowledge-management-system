package ru.richieernest.knowledgeManagementSystem.dto.formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormulaDto {
    private Long id;

    private String title;

    private String formula;

    private Double result;

    private Map<String, Long> dependencies;
}
