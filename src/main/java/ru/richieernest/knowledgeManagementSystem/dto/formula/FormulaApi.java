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
public class FormulaApi {
    private String formula;
    private Map<String, Long> dependencies;
}
