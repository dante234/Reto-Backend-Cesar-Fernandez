package com.sekk.reto.backend.application.services;

import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandidateRepository;
import com.sekk.reto.backend.domain.services.CandisateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServicesImpl implements CandisateService {

    CandidateRepository candidateRepository;

    public CandidateServicesImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidates createCandidate(Candidates candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidates> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidates> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
