package ru.richieernest.knowledgeManagementSystem.entity;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("admin", "ROLE_ADMIN"),
    WRITER("writer", "ROLE_WRITER"),
    READER("reader", "ROLE_READER");

    private String id;
    private String roleName;
}