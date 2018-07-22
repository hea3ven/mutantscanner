package com.mribecky.mutantscanner.service.model;

public class MutantScanRequest {

    private final String[] dna;

    public MutantScanRequest() {
        dna = null;
    }

    public MutantScanRequest(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }
}
