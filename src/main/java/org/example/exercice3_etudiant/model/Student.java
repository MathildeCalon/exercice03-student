package org.example.exercice3_etudiant.model;

import jakarta.validation.constraints.*;
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
    @NotNull(message="Champ obligatoire")
    @NotBlank
    private String firstname;
    @NotBlank
    @NotNull(message="Champ obligatoire")
    @Size(min=3, max = 35, message = "Entre 3 et 35 caractères")
    private String lastname;
    @Min(18)
    @Max(77)
    private int age;
    private String email;
}
