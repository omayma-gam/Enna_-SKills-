package com.Enna.Competence.Services;


import com.Enna.Competence.DTO.Sous_CompetenceDto;
import com.Enna.Competence.Mappers.Sous_CompetenceMapper;
import com.Enna.Competence.Models.Competence;
import com.Enna.Competence.Models.Sous_Competence;
import com.Enna.Competence.Repositories.CompetenceRepo;
import com.Enna.Competence.Repositories.Sous_CompetenceRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Sous_CompetenceService {


    private final Sous_CompetenceRepo sousCompetenceRepo;
    private Sous_CompetenceMapper sousCompetenceMapper;
    private CompetenceRepo competenceRepo;
    private CompetenceService competenceService;




    public Sous_CompetenceService(Sous_CompetenceRepo sousCompetenceRepo, Sous_CompetenceMapper sousCompetenceMapper, CompetenceRepo competenceRepo, CompetenceService competenceService) {
        this.sousCompetenceRepo = sousCompetenceRepo;
        this.sousCompetenceMapper = sousCompetenceMapper;
        this.competenceRepo = competenceRepo;
        this.competenceService = competenceService;
    }
    public Sous_CompetenceDto AjouterSousCompetence(Sous_CompetenceDto sousCompetenceDto) {
        Sous_Competence sousCompetence = sousCompetenceMapper.dtoToSousCompetence(sousCompetenceDto);
        if (sousCompetenceDto.getCompetenceId()!=null){
            Competence competence=competenceRepo.findById(sousCompetenceDto.getCompetenceId())
                    .orElse(null);
            sousCompetence.setCompetence(competence);
        }
        return sousCompetenceMapper.souscompeteneToDto(sousCompetenceRepo.save(sousCompetence));
    }
    public List<Sous_CompetenceDto> ListSousCompetence(){
      return sousCompetenceRepo.findAll().stream()
                .map(sousCompetenceMapper::souscompeteneToDto)
                .collect(Collectors.toList());
    }
    public Sous_CompetenceDto modifier(Long id , Sous_CompetenceDto sous_competenceDto){
        Sous_Competence sousCompetence= sousCompetenceRepo.findById(id)
                .orElse(null);
        if (sous_competenceDto==null){
            throw new RuntimeException("Aucune sous_competence");
        }
        sousCompetence.setNom(sous_competenceDto.getNom());
        sousCompetence.setDescription(sous_competenceDto.getDescription());
        return sousCompetenceMapper.souscompeteneToDto(sousCompetence);
    }

    public void supprimerSousCompetence (Long id ){
        sousCompetenceRepo.deleteById(id);
    }

    public Sous_CompetenceDto updateEtatValidation(Long id, boolean etatValidation) {
        Sous_Competence sousCompetence = sousCompetenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sous-compétence non trouvée avec id: " + id));
        sousCompetence.setEtatValidation(etatValidation);
        Sous_Competence updated = sousCompetenceRepo.save(sousCompetence);
        if (updated.getCompetence() != null && updated.getCompetence().getId() != null) {
            updateCompetenceValidation(updated.getCompetence().getId());
        }
        return sousCompetenceMapper.souscompeteneToDto(updated);
    }


    private void updateCompetenceValidation(Long competenceId) {
        Competence competence = competenceRepo.findByIdWithSousCompetences(competenceId)
                .orElseThrow(() -> new RuntimeException("Competence not found"));

        long totalSousCompetences = competence.getSousCompetences().size();
        long validatedSousCompetences = competence.getSousCompetences().stream()
                .filter(Sous_Competence::isEtatValidation)
                .count();
        boolean isCompetenceValidated = totalSousCompetences > 0 && validatedSousCompetences == totalSousCompetences;
        competence.setEtatValidation(isCompetenceValidated);
        competenceRepo.save(competence);
    }

}
