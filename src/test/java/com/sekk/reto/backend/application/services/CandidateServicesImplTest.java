package com.sekk.reto.backend.application.services;


import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateServicesImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServicesImpl candidateServicesImpl;

    @Test
    void testCreateCandidate() {
        // Configuración
        Candidates candidate = new Candidates(null, "John Doe", "john.doe@example.com", "Male", 50000.0, "Developer", "Active");
        when(candidateRepository.save(any(Candidates.class))).thenReturn(candidate);

        // Ejecución
        Candidates createdCandidate = candidateServicesImpl.createCandidate(candidate);

        // Verificación
        assertEquals(candidate, createdCandidate);
    }

    @Test
    void testGetAllCandidates() {
        // Configuración
        List<Candidates> candidates = List.of(new Candidates(1L, "John Doe", "john.doe@example.com", "Male", 50000.0, "Developer", "Active"));
        when(candidateRepository.findAll()).thenReturn(candidates);

        // Ejecución
        List<Candidates> allCandidates = candidateServicesImpl.getAllCandidates();

        // Verificación
        assertEquals(candidates, allCandidates);
    }

    @Test
    void testGetCandidateById() {
        // Configuración
        Candidates candidate = new Candidates(1L, "John Doe", "john.doe@example.com", "Male", 50000.0, "Developer", "Active");
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));

        // Ejecución
        Optional<Candidates> candidateById = candidateServicesImpl.getCandidateById(1L);

        // Verificación
        assertEquals(Optional.of(candidate), candidateById);
    }

    @Test
    void testDeleteCandidate() {
        // Configuración
        doNothing().when(candidateRepository).deleteById(1L);

        // Ejecución
        candidateServicesImpl.deleteCandidate(1L);

        // Verificación
        verify(candidateRepository).deleteById(1L);
    }
}