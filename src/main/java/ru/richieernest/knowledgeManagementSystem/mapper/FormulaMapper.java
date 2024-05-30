package ru.richieernest.knowledgeManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;

@RequiredArgsConstructor
@Component
public class FormulaMapper {

    private final FormulaRepo formulaRepository;
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepository;

}
