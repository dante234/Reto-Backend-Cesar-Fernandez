package com.sekk.reto.backend.adapter.out.repository;

import com.sekk.reto.backend.adapter.out.persintence.entity.CandidatesEntity;
import com.sekk.reto.backend.adapter.out.persintence.mapper.CandidateMapper;
import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateRepositoryImplTest {

    @Mock
    private JpacandidateRepository jpacandidateRepository;

    @InjectMocks
    private CandidateRepositoryImpl candidateRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Datos de prueba
        Candidates candidate = new Candidates();
        CandidatesEntity entity = CandidateMapper.toEntity(candidate);
        CandidatesEntity savedEntity = new CandidatesEntity();

        // Simulación de comportamiento con ArgumentMatchers.any() para evitar el problema de referencia exacta
        when(jpacandidateRepository.save(any(CandidatesEntity.class))).thenReturn(savedEntity);

        // Ejecución del método
        Candidates result = candidateRepository.save(candidate);

        // Verificaciones
        assertNotNull(result);
        verify(jpacandidateRepository).save(any(CandidatesEntity.class));
    }

    @Test
    void testFindAll() {
        // Datos de prueba
        List<CandidatesEntity> entities = List.of(new CandidatesEntity());
        when(jpacandidateRepository.findAll()).thenReturn(entities);

        // Ejecución del método
        List<Candidates> result = candidateRepository.findAll();

        // Verificaciones
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jpacandidateRepository).findAll();
    }

    @Test
    void testFindById() {
        // Datos de prueba
        Long id = 1L;
        CandidatesEntity entity = new CandidatesEntity();
        when(jpacandidateRepository.findById(id)).thenReturn(Optional.of(entity));

        // Ejecución del método
        Optional<Candidates> result = candidateRepository.findById(id);

        // Verificaciones
        assertTrue(result.isPresent());
        verify(jpacandidateRepository).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        // Datos de prueba
        Long id = 1L;
        when(jpacandidateRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución del método
        Optional<Candidates> result = candidateRepository.findById(id);

        // Verificaciones
        assertFalse(result.isPresent());
        verify(jpacandidateRepository).findById(id);
    }

    @Test
    void testDeleteById() {
        // Datos de prueba
        Long id = 1L;

        // Ejecución del método
        candidateRepository.deleteById(id);

        // Verificaciones
        verify(jpacandidateRepository).deleteById(id);
    }
}
