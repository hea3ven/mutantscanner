package com.mribecky.mutantscanner;

import java.util.Arrays;

public class DnaSequence {

    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;

    private final String[] dna;

    public DnaSequence(String[] dna) {
        validate(dna);
        this.dna = dna;
    }

    private void validate(String[] dna) {
        if (dna.length != HEIGHT) {
            throw new IllegalArgumentException("dna has the wrong number of rows");
        }
        if (Arrays.stream(dna).map(String::length).anyMatch(length -> length != WIDTH)) {
            throw new IllegalArgumentException("dna has the wrong number of columns");
        }
        if (Arrays.stream(dna)
                .flatMapToInt(String::chars)
                .anyMatch(gene -> gene != 'A' && gene != 'C' && gene != 'G' && gene != 'T')) {
            throw new IllegalArgumentException("dna has an invalid gene");
        }
    }

    public char getGene(GenePosition startPos) {
        return dna[startPos.getRow()].charAt(startPos.getColumn());
    }
}
