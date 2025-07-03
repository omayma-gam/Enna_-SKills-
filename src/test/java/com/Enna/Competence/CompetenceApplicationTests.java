package com.Enna.Competence;

import com.Enna.Competence.DTO.CompetenceDto;
import com.Enna.Competence.Mappers.CompetenceMapper;
import com.Enna.Competence.Models.Competence;
import com.Enna.Competence.Repositories.CompetenceRepo;
import com.Enna.Competence.Services.CompetenceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetenceApplicationTests {

	@Mock
	private CompetenceRepo competenceRepo;

	@Mock
	private CompetenceMapper competenceMapper;

	@InjectMocks
	private CompetenceService competenceService;

	private Competence competence;
	private CompetenceDto competenceDto;

	@BeforeEach
	void setUp() {
		competence = new Competence();
		competence.setId(1L);
		competence.setNom("Java");
		competence.setDescription("Programmation Java");

		competenceDto = new CompetenceDto();
		competenceDto.setId(1L);
		competenceDto.setNom("Java");
		competenceDto.setDescription("Programmation Java");
	}

	@Test
	void testAjouterCompetence() {
		when(competenceMapper.dtoToCompetence(competenceDto)).thenReturn(competence);
		when(competenceRepo.save(competence)).thenReturn(competence);
		when(competenceMapper.competenceToDto(competence)).thenReturn(competenceDto);

		CompetenceDto result = competenceService.AjouterCompetence(competenceDto);

		assertNotNull(result);
		assertEquals("Java", result.getNom());
		verify(competenceRepo).save(competence);
	}

	@Test
	void testListCompetence() {
		when(competenceRepo.findAll()).thenReturn(List.of(competence));
		when(competenceMapper.competenceToDto(competence)).thenReturn(competenceDto);

		List<CompetenceDto> result = competenceService.ListCompetence();

		assertEquals(1, result.size());
		assertEquals("Java", result.get(0).getNom());
		verify(competenceRepo).findAll();
	}

	@Test
	void testModifierCompetence() {
		when(competenceRepo.findById(1L)).thenReturn(Optional.of(competence));

		// Simuler la mise à jour dynamique selon les données de Competence
		when(competenceMapper.competenceToDto(any(Competence.class))).thenAnswer(invocation -> {
			Competence c = invocation.getArgument(0);
			CompetenceDto dto = new CompetenceDto();
			dto.setId(c.getId());
			dto.setNom(c.getNom());
			dto.setDescription(c.getDescription());
			return dto;
		});

		CompetenceDto updated = new CompetenceDto();
		updated.setNom("Spring Boot");
		updated.setDescription("Framework Java");

		CompetenceDto result = competenceService.modifierCompetence(1L, updated);

		assertEquals("Spring Boot", result.getNom());
		assertEquals("Framework Java", result.getDescription());
		verify(competenceRepo).findById(1L);
	}

	@Test
	void testModifierCompetence_NotFound() {
		when(competenceRepo.findById(99L)).thenReturn(Optional.empty());

		CompetenceDto updated = new CompetenceDto();
		updated.setNom("New");
		updated.setDescription("desc");

		Exception exception = assertThrows(RuntimeException.class, () -> {
			competenceService.modifierCompetence(99L, updated);
		});

		assertEquals("Aucune Compétence", exception.getMessage());
	}
}
