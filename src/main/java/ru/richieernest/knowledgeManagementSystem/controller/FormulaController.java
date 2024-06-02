package ru.richieernest.knowledgeManagementSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.formula.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;
import ru.richieernest.knowledgeManagementSystem.service.FormulaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/formulas")
public class FormulaController {

    private final FormulaService formulaService;
    private final FormulaRepo formulaRepo;

    @GetMapping("/")
    public List<FormulaDto> getAllFormulas(){
        return formulaService.getAll();
    }

    @PostMapping("/")
    public void saveFormuls(@RequestBody FormulaDto formulaDto){
        formulaService.addFormula(formulaDto);
    }

    @PutMapping("/")
    public Double updateFormula(@RequestBody FormulaDto formulaDto){
        return formulaService.updateFormula(formulaDto);
    }

    @GetMapping("/{id}")
    public FormulaDto getFormula(@PathVariable Long id){
        return formulaService.formulaFromDb(formulaRepo.searchFormulaById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        formulaService.deleteFormula(id);
    }

    @GetMapping("/map")
    public Map<Long,String> getFormulaNameId(){
        return formulaService.formulaMap();
    }
}
