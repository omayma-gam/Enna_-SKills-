package com.Enna.Competence.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sous_Competence {


    @Id
    private Long id;
    private String nom;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
}
