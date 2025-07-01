package com.Enna.Competence.Services;

import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.Mappers.CompetenceMapper;
import com.Enna.Competence.Models.Competence;
import com.Enna.Competence.Repositories.CompetenceRepo;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SpinnerUI;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompetenceService {

    private final CompetenceRepo competenceRepo;
    private CompetenceMapper competenceMapper;



    public CompetenceService(CompetenceRepo competenceRepo, CompetenceMapper competenceMapper) {
        this.competenceRepo = competenceRepo;
        this.competenceMapper = competenceMapper;
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

}
