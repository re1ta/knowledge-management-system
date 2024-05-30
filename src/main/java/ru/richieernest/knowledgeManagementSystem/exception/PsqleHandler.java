package ru.richieernest.knowledgeManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class PsqleHandler {
    @ExceptionHandler(value = SQLException.class)
    private ResponseEntity<String> handlePSQLException(SQLException ex) {
        if(ex.getSQLState().equals("23505")){
            return status(HttpStatus.BAD_REQUEST).body("Такой пользователь с таким именем уже создан!");}
        return status(HttpStatus.BAD_REQUEST).body("Ошибка на сервере:(");
    }
}
