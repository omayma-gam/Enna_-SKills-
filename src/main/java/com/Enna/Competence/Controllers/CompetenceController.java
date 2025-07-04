package com.Enna.Competence.Controllers;


import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.Models.Competence;
import com.Enna.Competence.Services.CompetenceService;
import com.Enna.Competence.Services.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.cert.CertPath;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Competence")
public class CompetenceController {

    private final CompetenceService competenceService;
    @Autowired
    private ExcelService excelService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @PostMapping("/add")
    public CompetenceDto AddCompetence(@RequestBody CompetenceDto competenceDto){
        return competenceService.AjouterCompetence(competenceDto);
    }

    @GetMapping("/List")
    public List<Competence> getAll(){
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
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // 1. Définir le type de contenu de la réponse
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 2. Créer un nom de fichier dynamique
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rapportcompetences" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // 3. Récupérer les données à exporter
        List<Competence> competences = competenceService.ListCompetence();

        // 4. Appeler le service d'export
        excelService.exportCompetencesToExcel(competences, response.getOutputStream());
    }

}
