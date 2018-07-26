package com.mribecky.mutantscanner.repository;

import com.mribecky.mutantscanner.model.Dna;
import org.springframework.data.repository.CrudRepository;

public interface DnaRepository extends CrudRepository<Dna, Long> {

    long countBySequence(String sequence);

    long countByMutant(boolean mutant);
}
