package com.mribecky.mutantscanner.controller.api;

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
