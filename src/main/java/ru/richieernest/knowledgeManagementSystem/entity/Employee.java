package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique=true)
    private String username;

    private String password;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_username"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
