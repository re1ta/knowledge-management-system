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

    @GetMapping("/{id}")
    public FormulaDto getFormula(@PathVariable Long id){
        return formulaService.formulaFromDb(formulaRepo.searchFormulaById(id));
    }

    @GetMapping("/map")
    public Map<Long,String> getFormulaNameId(){
        return formulaService.formulaMap();
    }
    @PutMapping("/")
    public Double updateFormula(@RequestBody FormulaDto formulaDto){
        return formulaService.updateFormula(formulaDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        formulaService.deleteFormula(id);
    }

    @PostMapping("/")
    public void saveFormuls(@RequestBody FormulaDto formulaDto){
        formulaService.addFormula(formulaDto);
    }
}
