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
        while (column < 6 && row < 6) {
            GenePosition pos = new GenePosition(column, row);
            char gene = dna.getGene(pos);
            geneChains.addAll(geneChains.stream()
                    .filter(geneChain -> gene == geneChain.getGene() && geneChain.size() == 1
                            && geneChain.getPositions().get(0).isNextTo(pos))
                    .map(geneChain -> new GeneChain(geneChain, pos))
                    .collect(Collectors.toList()));
            geneChains.stream()
                    .filter(geneChain -> gene == geneChain.getGene() && geneChain.size() > 1
                            && geneChain.getNextPosition().equals(pos))
                    .forEach(geneChain -> geneChain.add(pos));
            geneChains.add(new GeneChain(gene, pos));
            column++;
            if (column >= 6) {
                column = 0;
                row++;
            }
            Optional<GeneChain> completedChain =
                    geneChains.stream().filter(geneChain -> geneChain.size() == 4).findFirst();
            if (completedChain.isPresent()) {
                geneChains.remove(completedChain.get());
                return completedChain;
            }
        }
        return Optional.empty();
    }
}
