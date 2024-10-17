package com.sekk.reto.backend.adapter.out.persintence;


import com.sekk.reto.backend.adapter.out.persintence.entity.CandidatesEntity;
import com.sekk.reto.backend.adapter.out.persintence.mapper.CandidateMapper;
import com.sekk.reto.backend.domain.model.Candidates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateMapperTest {

    @Test
    void testToEntity() {
        // Datos de prueba
        Candidates candidate = new Candidates(1L, "John Doe", "john.doe@example.com", "Male", 50000.00, "Developer", "Active");

        // Ejecución del método
        CandidatesEntity entity = CandidateMapper.toEntity(candidate);

        // Verificaciones
        assertNotNull(entity);
        assertEquals(candidate.getId(), entity.getId());
        assertEquals(candidate.getName(), entity.getName());
        assertEquals(candidate.getEmail(), entity.getEmail());
        assertEquals(candidate.getGender(), entity.getGender());
        assertEquals(candidate.getSalaryExpected(), entity.getSalaryExpected());
        assertEquals(candidate.getPosition(), entity.getPosition());
        assertEquals(candidate.getStatus(), entity.getStatus());
    }

    @Test
    void testToDomain() {
        // Datos de prueba
        CandidatesEntity entity = new CandidatesEntity(1L, "Jane Doe", "jane.doe@example.com", "Female", 60000.00, "Manager", "Inactive");

        // Ejecución del método
        Candidates candidate = CandidateMapper.toDomain(entity);

        // Verificaciones
        assertNotNull(candidate);
        assertEquals(entity.getId(), candidate.getId());
        assertEquals(entity.getName(), candidate.getName());
        assertEquals(entity.getEmail(), candidate.getEmail());
        assertEquals(entity.getGender(), candidate.getGender());
        assertEquals(entity.getSalaryExpected(), candidate.getSalaryExpected());
        assertEquals(entity.getPosition(), candidate.getPosition());
        assertEquals(entity.getStatus(), candidate.getStatus());
    }
}

