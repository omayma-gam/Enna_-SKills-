package com.Enna.Competence.Controllers;


import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.Services.CompetenceService;
import org.springframework.web.bind.annotation.*;

import java.security.cert.CertPath;
import java.util.List;

@RestController
@RequestMapping("/Competence")
public class CompetenceController {

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @PostMapping("/add")
    public CompetenceDto AddCompetence(@RequestBody CompetenceDto competenceDto){
        return competenceService.AjouterCompetence(competenceDto);
    }

    @GetMapping("/List")
    public List<CompetenceDto> getAll(){
        return competenceService.ListCompetence();
    }

    @PutMapping("/updat/{id}")
    public CompetenceDto updatCompetence(@PathVariable Long id , @RequestBody CompetenceDto competenceDto){
        return competenceService.modifierCompetence(id,competenceDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        competenceService.supprimerCompetence(id);
    }
}
