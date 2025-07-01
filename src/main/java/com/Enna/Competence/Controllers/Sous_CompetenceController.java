package com.Enna.Competence.Controllers;


import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.DTO.Sous_CompetenceDto;
import com.Enna.Competence.Services.Sous_CompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soucompetence")
public class Sous_CompetenceController {

    private final Sous_CompetenceService sousCompetenceService;

    public Sous_CompetenceController(Sous_CompetenceService sousCompetenceService) {
        this.sousCompetenceService = sousCompetenceService;
    }

    @PostMapping("/add")
    public Sous_CompetenceDto AddSousCompetence(@RequestBody Sous_CompetenceDto sous_competenceDto) {
        return sousCompetenceService.AjouterSousCompetence(sous_competenceDto);
    }

    @GetMapping("/List")
    public List<Sous_CompetenceDto> getAll(){
        return sousCompetenceService.ListSousCompetence();
    }

    @PutMapping("/updat{id}")
    public Sous_CompetenceDto updatSousCompetence(@PathVariable Long id , @RequestBody Sous_CompetenceDto sousCompetenceDto){
        return sousCompetenceService.modifier(id,sousCompetenceDto);
    }

    @DeleteMapping("{id}")
    public void delet(@PathVariable Long id){
         sousCompetenceService.supprimerSousCompetence(id);
    }

}

