package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.richieernest.knowledgeManagementSystem.dto.formula.FormulaApi;
import ru.richieernest.knowledgeManagementSystem.dto.formula.FormulaCal;
import ru.richieernest.knowledgeManagementSystem.dto.formula.FormulaDto;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
import ru.richieernest.knowledgeManagementSystem.entity.FormulaDependenciesMap;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaDependenciesMapRepo;
import ru.richieernest.knowledgeManagementSystem.repository.FormulaRepo;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FormulaService {
    private final FormulaRepo formulaRepo;
    private final FormulaDependenciesMapRepo formulaDependenciesMapRepo;

    //метод для получения всех формул
    public List<FormulaDto> getAll() {
        System.out.println("dfsdffds");
        List<Formula> formulas = formulaRepo.findAll();
        List<FormulaDto> formulaDtos = new ArrayList<>();
        formulas.forEach(value -> formulaDtos.add(formulaFromDb(value)));
        return formulaDtos;
    }

    public FormulaDto formulaFromDb(Formula formula){
        List<FormulaDependenciesMap> fdmArray = formulaDependenciesMapRepo.findByParentId(formula.getId());
        Map<String,Long> dependencies = new HashMap<>();
        fdmArray.forEach(fdm -> dependencies.put(fdm.getLetter(),fdm.getChildFormulaId()));
        return FormulaDto.builder()
                .id(formula.getId())
                .formula(formula.getFormula())
                .title(formula.getTitle())
                .result(formula.getResult())
                .dependencies(dependencies)
                .build();
    }

    public Map<Long,String> formulaMap(){
        Map<Long,String> formulaM = new HashMap<>();
        List<Formula> formulas = formulaRepo.findAll();
        for (Formula value : formulas){
            formulaM.put(value.getId(), value.getTitle());
        }
        return formulaM;
    }

    //добавление одного, логика расчета формулы
    public Formula addFormula(FormulaDto formulaDto) {
        Formula formula = Formula.builder()
                .title(formulaDto.getTitle())
                .formula(formulaDto.getFormula())
                .result(calculateFormula(formulaDto))
                .build();
        formulaRepo.save(formula);
        if (!formulaDto.getDependencies().isEmpty()) {
            formulaDto.getDependencies().forEach((letter, id) -> setDependencies(letter, id, formulaRepo.lastAddId()));
        }
        return formulaRepo.lastAddFormula();
    }

    private void setDependencies(String letter, Long id_child, Long id_parent){
        FormulaDependenciesMap fdm = FormulaDependenciesMap.builder().build();
        fdm.setParentFormulaId(id_parent);
        fdm.setChildFormulaId(id_child);
        fdm.setLetter(letter);
        formulaDependenciesMapRepo.save(fdm);
    }

    private double calculateFormula(FormulaDto formulaDto){
        String apiUrl = "https://functions.yandexcloud.net/d4e6tuni7n56makddo5q";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Double> map;
        if(formulaDto.getDependencies()!= null){
            map = idToResult(formulaDto.getDependencies());
        }
        else{
            map = null;
        }
        FormulaCal formulaCal = FormulaCal.builder()
                .formula(formulaDto.getFormula())
                .dependencies(map)
                .build();
        System.out.println(formulaCal);
        HttpEntity<Object> requestEntity = new HttpEntity<>(formulaCal, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        System.out.println(responseEntity.getBody() + " ответ");
        return Double.parseDouble(responseEntity.getBody());
    }

    @Transactional
    public boolean deleteFormula(Long id) {
        if(formulaDependenciesMapRepo.findByChildId(id).isEmpty()){
            formulaRepo.deleteById(id);
            if(!formulaDependenciesMapRepo.findByParentId(id).isEmpty()){
                formulaDependenciesMapRepo.deleteByParentId(id);
            }
        }
        else{
            System.out.println("Эта формула есть в других формулах!");
        }
        return true;
    }

    @Transactional
    //апдейт одного, логика обновления всех зависимых формул
    public double updateFormula(FormulaDto formulaDto) {
        var cal = calculateFormula(formulaDto);
        formulaRepo.updateFormula(formulaDto.getFormula(), cal, formulaDto.getTitle(), formulaDto.getId());
        System.out.println(formulaDto.getDependencies());
        if(formulaDto.getDependencies() != null){
            List<String> letters = new ArrayList<>();
            formulaDto.getDependencies().forEach((k, v) -> letters.add(k));
            System.out.println(letters);
            List<FormulaDependenciesMap> fdmFromDb = formulaDependenciesMapRepo.findLettersByParentId(formulaDto.getId());
            List<String> lettersFromDb = new ArrayList<>();
            for (FormulaDependenciesMap fmd : fdmFromDb){
                lettersFromDb.add(fmd.getLetter());
            }
            for (String l : lettersFromDb ){
                if(!letters.contains(l)) { formulaDependenciesMapRepo.deleteByLetter(l);}
            }

            formulaDto.getDependencies().forEach((k, v) -> checkContainsLetters(k,v,lettersFromDb,formulaDto.getId()));
        }

        return cal;
    }

    public void checkContainsLetters(String letter, Long id_child, List<String> lettersFromDb, Long id_parent){
        if(!lettersFromDb.contains(letter)){setDependencies(letter,id_child, id_parent);}
    }

    public Map<String,Double> idToResult(Map<String,Long> depenciences){
        Map<String,Double> result = new HashMap<>();
        depenciences.forEach((k,v) -> result.put(k,formulaRepo.findResultById(v)));
        System.out.println(result);
        return result;
    }


}
