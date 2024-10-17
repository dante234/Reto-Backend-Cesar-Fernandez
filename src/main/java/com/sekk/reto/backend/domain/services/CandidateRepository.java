package com.sekk.reto.backend.domain.services;

import com.sekk.reto.backend.domain.model.Candidates;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {
    Candidates save(Candidates candidate);             // Guardar un candidato
    List<Candidates> findAll();                       // Obtener todos los candidatos
    Optional<Candidates> findById(Long id);           // Buscar candidato por ID

    void deleteById(Long id);                           // Eliminar candidato por ID
}
