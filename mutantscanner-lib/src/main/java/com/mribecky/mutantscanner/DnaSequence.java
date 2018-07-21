package com.mribecky.mutantscanner;

public class DnaSequence {

    private final String[] dna;

    public DnaSequence(String[] dna) {
        this.dna = dna;
    }

    public char getGene(GenePosition startPos) {
        return dna[startPos.getRow()].charAt(startPos.getColumn());
    }
}
