package com.Enna.Competence.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDto implements Serializable {
    Long id;
    String nom;
    String description;
    boolean etatValidation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(boolean etatValidation) {
        this.etatValidation = etatValidation;
    }
}