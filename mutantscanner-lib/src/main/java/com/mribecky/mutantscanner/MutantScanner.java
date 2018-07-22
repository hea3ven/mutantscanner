package com.mribecky.mutantscanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MutantScanner {

    private final DnaSequence dna;
    private final List<GeneChain> geneChains;
    private int column;
    private int row;

    public MutantScanner(DnaSequence dna) {
        this.dna = dna;
        geneChains = new ArrayList<>();
        column = 0;
        row = 0;
    }

    public Optional<GeneChain> findNextMutantChain() {
        while (column < DnaSequence.WIDTH && row < DnaSequence.HEIGHT) {
            GenePosition pos = new GenePosition(column, row);
            char gene = dna.getGene(pos);
            geneChains.addAll(geneChains.stream()
                    .filter(geneChain -> gene == geneChain.getGene() && !geneChain.hasDirection()
                            && geneChain.getPositions().get(0).isNextTo(pos))
                    .map(geneChain -> new GeneChain(geneChain, pos))
                    .collect(Collectors.toList()));
            geneChains.stream()
                    .filter(geneChain -> gene == geneChain.getGene() && geneChain.hasDirection()
                            && geneChain.getNextPosition().equals(pos))
                    .forEach(geneChain -> geneChain.add(pos));
            geneChains.add(new GeneChain(gene, pos));
            column++;
            if (column >= DnaSequence.WIDTH) {
                column = 0;
                row++;
            }
            Optional<GeneChain> completedChain =
                    geneChains.stream().filter(GeneChain::isComplete).findFirst();
            if (completedChain.isPresent()) {
                geneChains.remove(completedChain.get());
                return completedChain;
            }
        }
        return Optional.empty();
    }
}
