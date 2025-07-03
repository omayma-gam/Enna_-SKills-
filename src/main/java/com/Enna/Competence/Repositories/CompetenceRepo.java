package com.Enna.Competence.Repositories;

import com.Enna.Competence.Models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompetenceRepo extends JpaRepository<Competence,Long> {

    @Query("SELECT c FROM Competence c LEFT JOIN FETCH c.sousCompetences WHERE c.id = :id")
    Optional<Competence> findByIdWithSousCompetences(@Param("id") Long id);
}
