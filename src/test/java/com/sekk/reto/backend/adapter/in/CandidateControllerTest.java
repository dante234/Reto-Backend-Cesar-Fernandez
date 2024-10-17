package com.sekk.reto.backend.adapter.in;


import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandisateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateControllerTest {

    @Mock
    private CandisateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @Test
    void testGetAllCandidates() {
        // Configuración
        List<Candidates> candidates = List.of(new Candidates(1L,"cesar fernandez", "cesarfdperu@gmail.com", "Male", 50000.0, "Developer", "Active"));
        when(candidateService.getAllCandidates()).thenReturn(candidates);

        // Ejecución
        ResponseEntity<List<Candidates>> response = candidateController.getAllCandidates();

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidates, response.getBody());
    }

    @Test
    void testGetCandidateById() {
        // Configuración
        Candidates candidate = new Candidates(1L, "cesar fernandez", "cesarfdperu@gmail.com", "Male", 50000.0, "Developer", "Active");
        when(candidateService.getCandidateById(1L)).thenReturn(Optional.of(candidate));

        // Ejecución
        ResponseEntity<Candidates> response = candidateController.getCandidateById(1L);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidate, response.getBody());
    }

    @Test
    void testCreateCandidate() {
        // Configuración
        Candidates candidate = new Candidates(null, "cesar fernandez", "cesarfdperu@gmail.com", "Male", 50000.0, "Developer", "Active");
        when(candidateService.createCandidate(any(Candidates.class))).thenReturn(candidate);

        // Ejecución
        ResponseEntity<Candidates> response = candidateController.createCandidate(candidate);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(candidate, response.getBody());
    }

    @Test
    void testDeleteCandidate() {
        // Configuración
        doNothing().when(candidateService).deleteCandidate(1L);
        // Ejecución
        ResponseEntity<Void> response = candidateController.deleteCandidate(1L);

        // Verificación
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
