package com.sekk.reto.backend.adapter.out.repository;

import com.sekk.reto.backend.adapter.out.persintence.entity.CandidatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpacandidateRepository extends CrudRepository<CandidatesEntity, Long> {
}
