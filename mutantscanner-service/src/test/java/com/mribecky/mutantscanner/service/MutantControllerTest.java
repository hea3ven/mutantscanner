package com.mribecky.mutantscanner.service;

import com.mribecky.mutantscanner.service.model.MutantScanRequest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MutantControllerTest {

    @Test
    public void isMutant_hasMutantGenes_status200() {
        MutantController controller = new MutantController();
        MutantScanRequest req = new MutantScanRequest(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG",
                "TCACTG"});

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void isMutant_doesntHaveMutantGenes_status403() {
        MutantController controller = new MutantController();
        MutantScanRequest req = new MutantScanRequest(new String[] {
                "ATGCCA",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"});

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void isMutant_invalidDna_status400() {
        MutantController controller = new MutantController();
        MutantScanRequest req = new MutantScanRequest(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG"});

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}