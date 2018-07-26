package com.mribecky.mutantscanner.service;

import com.mribecky.mutantscanner.DnaSequence;
import com.mribecky.mutantscanner.MutantScanner;
import com.mribecky.mutantscanner.model.Dna;
import com.mribecky.mutantscanner.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    @Autowired
    private DnaRepository dnaRepo;

    public boolean isMutant(DnaSequence dnaSequence) {
        boolean result = new MutantScanner(dnaSequence).findNextMutantChain().isPresent();
        Dna dna = new Dna(dnaSequence.getDna(), result);
        if (dnaRepo.countBySequence(dna.getSequence()) == 0) {
            dnaRepo.save(dna);
        }
        return result;
    }

    public DnaStats getDnaStats() {
        DnaStats stats = new DnaStats();
        stats.setCountMutantDna(dnaRepo.countByMutant(true));
        stats.setCountHumanDna(dnaRepo.countByMutant(false));
        stats.setRatio(((double) stats.getCountMutantDna()) / stats.getCountHumanDna());
        return stats;
    }
}
