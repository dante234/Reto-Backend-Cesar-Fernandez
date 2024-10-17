package com.sekk.reto.backend.domain.services;

import java.util.List;
import java.util.Optional;

import com.sekk.reto.backend.domain.model.Candidates;

public interface CandisateService {
    Candidates createCandidate(Candidates candidate);
    List<Candidates> getAllCandidates();
    Optional<Candidates> getCandidateById(Long id);
    void deleteCandidate(Long id);
}
