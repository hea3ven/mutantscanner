package com.mribecky.mutantscanner.controller.api;

import com.mribecky.mutantscanner.service.MutantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {

    @Mock
    private MutantService service;

    @InjectMocks
    private MutantController controller;

    @Test
    public void isMutant_hasMutantGenes_status200() {
        String[] dna = {
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA"};
        MutantScanRequest req = new MutantScanRequest(dna);
        when(service.isMutant(argThat(hasProperty("dna", is(dna))))).thenReturn(true);

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void isMutant_doesntHaveMutantGenes_status403() {
        String[] dna = {
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA"};
        MutantScanRequest req = new MutantScanRequest(dna);
        when(service.isMutant(argThat(hasProperty("dna", is(dna))))).thenReturn(false);

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    public void isMutant_invalidDna_status400() {
        MutantScanRequest req = new MutantScanRequest(new String[] {
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA"});

        ResponseEntity<String> result = controller.isMutant(req);

        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}