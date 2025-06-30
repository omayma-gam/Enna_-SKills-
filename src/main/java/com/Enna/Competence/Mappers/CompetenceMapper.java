package com.Enna.Competence.Mappers;


import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.DTO.Sous_CompetenceDto;
import com.Enna.Competence.Models.Competence;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CompetenceMapper {

    CompetenceDto competenceToDto(Competence competence);
    Competence dtoToCompetence(CompetenceDto competenceDto);
}
