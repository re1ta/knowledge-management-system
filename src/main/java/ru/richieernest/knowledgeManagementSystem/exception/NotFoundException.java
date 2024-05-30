package ru.richieernest.knowledgeManagementSystem.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
