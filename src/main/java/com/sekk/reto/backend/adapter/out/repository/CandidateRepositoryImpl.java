package com.sekk.reto.backend.adapter.out.repository;

import com.sekk.reto.backend.adapter.out.persintence.entity.CandidatesEntity;
import com.sekk.reto.backend.adapter.out.persintence.mapper.CandidateMapper;
import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandidateRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {

    private JpacandidateRepository jpacandidateRepository;

    public CandidateRepositoryImpl(JpacandidateRepository jpacandidateRepository) {
        this.jpacandidateRepository = jpacandidateRepository;
    }


    @Override
    public Candidates save(Candidates candidate) {
        CandidatesEntity entity = CandidateMapper.toEntity(candidate);
        CandidatesEntity savedEntity = jpacandidateRepository.save(entity);
        return CandidateMapper.toDomain(savedEntity);
    }

    @Override
    public List<Candidates> findAll() {

        List<CandidatesEntity> entities = (List<CandidatesEntity>) jpacandidateRepository.findAll();

        return entities
                .stream().map(CandidateMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Candidates> findById(Long id) {
        return jpacandidateRepository.findById(id)
                .map(CandidateMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpacandidateRepository.deleteById(id);
    }
}
