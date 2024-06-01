package ru.richieernest.knowledgeManagementSystem.entity;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("admin", "admin"),
    WRITER("writer", "writer"),
    READER("reader", "reader");

    private String id;
    private String roleName;
}