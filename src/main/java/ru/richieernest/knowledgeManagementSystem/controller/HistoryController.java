package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.EditDto;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.TextDto;
import ru.richieernest.knowledgeManagementSystem.dto.historyDto.UpdateVersionDto;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;
import ru.richieernest.knowledgeManagementSystem.service.HistoryService;

@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/{id}")
    public ResponseEntity<EditDto> view(@PathVariable Long id){
        return new ResponseEntity<>(historyService.viewAll(id), HttpStatus.OK);
    }

    @GetMapping("/versions/{id_version}")
    public ResponseEntity<TextDto> oldVersion(@PathVariable Long id_version){
        return new ResponseEntity<>(historyService.findVersion(id_version), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@RequestBody UpdateVersionDto updateVersionDto){
        historyService.saveVersion(updateVersionDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/versions/{id_version}")
    public ResponseEntity<HistoryArticle> delete(@PathVariable Long id_version){
        historyService.delete(id_version);
        return ResponseEntity.ok().build();
    }
}
