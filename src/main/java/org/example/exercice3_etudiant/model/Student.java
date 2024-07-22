package org.example.exercice3_etudiant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {
    private UUID id;
    private String firstname;
    private String lastname;
    private int age;
    private String email;
}
