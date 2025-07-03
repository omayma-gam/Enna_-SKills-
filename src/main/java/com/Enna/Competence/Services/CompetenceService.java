package com.Enna.Competence.Services;

import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.Mappers.CompetenceMapper;
import com.Enna.Competence.Models.Competence;
import com.Enna.Competence.Repositories.CompetenceRepo;
import com.Enna.Competence.Repositories.Sous_CompetenceRepo;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SpinnerUI;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompetenceService {

    private final CompetenceRepo competenceRepo;
    private final Sous_CompetenceRepo sous_CompetenceRepo;
    private CompetenceMapper competenceMapper;
    private Sous_CompetenceRepo sousCompetenceRepo;



    public CompetenceService(CompetenceRepo competenceRepo, CompetenceMapper competenceMapper, Sous_CompetenceRepo sous_CompetenceRepo) {
        this.competenceRepo = competenceRepo;
        this.competenceMapper = competenceMapper;
        this.sous_CompetenceRepo = sous_CompetenceRepo;
        this.sousCompetenceRepo=sous_CompetenceRepo;
    }

    public CompetenceDto AjouterCompetence(CompetenceDto competenceDto){
        Competence competence=competenceMapper.dtoToCompetence(competenceDto);
        Competence competence1=competenceRepo.save(competence);
        return competenceMapper.competenceToDto(competence1);
    }

    public List<CompetenceDto> ListCompetence(){
        return competenceRepo.findAll().stream()
                .map(competenceMapper::competenceToDto)
                .collect(Collectors.toList());
    }

    public CompetenceDto modifierCompetence(Long id ,CompetenceDto competenceDto){
        Competence competence=competenceRepo.findById(id).orElse(null);
        if (competence==null){
            throw new RuntimeException("Aucune Compétence");
        }
        competence.setNom(competenceDto.getNom());
        competence.setDescription(competenceDto.getDescription());
        return competenceMapper.competenceToDto(competence);
    }

    public void supprimerCompetence(Long id){
        competenceRepo.deleteById(id);
    }

//    public void verifierEtMettreAJourStatutCompetence(Long competenceId) {
//        Competence competence = competenceRepo.findById(competenceId).orElseThrow();
//
//        long total = sousCompetenceRepo.countByCompetenceId(competenceId);
//        long valides = sousCompetenceRepo.countByCompetenceIdAndEtatValidationTrue(competenceId);
//
//        boolean estAcquise = total > 0 && valides == total;
//        competence.setAcquise(estAcquise);
//
//        competenceRepo.save(competence);
//    }

}
