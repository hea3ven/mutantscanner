package com.mribecky.mutantscanner.controller.api;

import com.mribecky.mutantscanner.service.DnaStats;
import com.mribecky.mutantscanner.service.MutantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTest {

    @Mock
    private MutantService service;

    @InjectMocks
    private StatsController controller;

    @Test
    public void isMutant_hasMutantGenes_status200() {
        DnaStats dnaStats = new DnaStats();
        when(service.getDnaStats()).thenReturn(dnaStats);

        DnaStats result = controller.stats();

        assertThat(result, is(dnaStats));
    }
}