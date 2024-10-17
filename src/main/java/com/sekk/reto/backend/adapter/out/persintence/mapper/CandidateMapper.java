package com.sekk.reto.backend.adapter.out.persintence.mapper;

import com.sekk.reto.backend.adapter.out.persintence.entity.CandidatesEntity;
import com.sekk.reto.backend.domain.model.Candidates;

public class CandidateMapper {

    // De Dominio a Persistencia
    public static CandidatesEntity toEntity(Candidates candidate) {
        return new CandidatesEntity(
                candidate.getId(),
                candidate.getName(),
                candidate.getEmail(),
                candidate.getGender(),
                candidate.getSalaryExpected(),
                candidate.getPosition(),
                candidate.getStatus()
        );
    }

    // De Persistencia a Dominio
    public static Candidates toDomain(CandidatesEntity entity) {
        return new Candidates(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getGender(),
                entity.getSalaryExpected(),
                entity.getPosition(),
                entity.getStatus()
        );
    }
}
