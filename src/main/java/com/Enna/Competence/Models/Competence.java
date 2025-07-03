package com.Enna.Competence.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String nom;
    private String description;
    private boolean etatValidation;


    @OneToMany(mappedBy = "competence")
    private List<Sous_Competence> sousCompetences;



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

    public List<Sous_Competence> getSousCompetences() {
        return sousCompetences;
    }

    public void setSousCompetences(List<Sous_Competence> sousCompetences) {
        this.sousCompetences = sousCompetences;
    }

    public boolean isEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(boolean etatValidation) {
        this.etatValidation = etatValidation;
    }
}
