package com.mribecky.mutantscanner.service;

import com.mribecky.mutantscanner.DnaSequence;
import com.mribecky.mutantscanner.model.Dna;
import com.mribecky.mutantscanner.repository.DnaRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private DnaRepository dnaRepo;

    @InjectMocks
    private MutantService service;

    @Test
    public void isMutant_hasMutantGenes_returnsTrue() {
        DnaSequence req = new DnaSequence(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG",
                "TCACTG"});

        boolean result = service.isMutant(req);

        assertThat(result, is(true));
    }

    @Test
    public void isMutant_hasMutantGenes_storesDna() {
        DnaSequence req = new DnaSequence(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG",
                "TCACTG"});
        when(dnaRepo.countBySequence("CCCCTAATGCGACAGTGCTTCTGTAGAACGTCACTG")).thenReturn(0L);

        service.isMutant(req);

        ArgumentCaptor<Dna> argCaptor = ArgumentCaptor.forClass(Dna.class);
        verify(dnaRepo, times(1)).save(argCaptor.capture());
        Dna result = argCaptor.getValue();
        assertThat(result.getId(), nullValue());
        assertThat(result.getSequence(), is("CCCCTAATGCGACAGTGCTTCTGTAGAACGTCACTG"));
        assertThat(result.isMutant(), is(true));
    }

    @Test
    public void isMutant_doesntHaveMutantGenes_returnsFalse() {
        DnaSequence req = new DnaSequence(new String[] {
                "ATGCCA",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"});

        boolean result = service.isMutant(req);

        assertThat(result, is(false));
    }

    @Test
    public void isMutant_doesntHaveMutantGenes_storesDna() {
        DnaSequence req = new DnaSequence(new String[] {
                "ATGCCA",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"});
        //noinspection SpellCheckingInspection
        when(dnaRepo.countBySequence("ATGCCACAGTGCTTCTGTAGAAGGCACCTATCACTG")).thenReturn(0L);

        service.isMutant(req);

        ArgumentCaptor<Dna> argCaptor = ArgumentCaptor.forClass(Dna.class);
        verify(dnaRepo, times(1)).save(argCaptor.capture());
        Dna result = argCaptor.getValue();
        assertThat(result.getId(), nullValue());
        assertThat(result.getSequence(), is("ATGCCACAGTGCTTCTGTAGAAGGCACCTATCACTG"));
        assertThat(result.isMutant(), is(false));
    }

    @Test
    public void isMutant_dnaExistsInDatabase_doesNotStoreDna() {
        DnaSequence req = new DnaSequence(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG",
                "TCACTG"});
        when(dnaRepo.countBySequence("CCCCTAATGCGACAGTGCTTCTGTAGAACGTCACTG")).thenReturn(1L);

        service.isMutant(req);

        verify(dnaRepo, times(0)).save(any());
    }

    @Test
    public void getDnaStats_gettingStats_getsInformationFromRepository() {
        when(dnaRepo.countByMutant(true)).thenReturn(2L);
        when(dnaRepo.countByMutant(false)).thenReturn(8L);

        DnaStats result = service.getDnaStats();

        assertThat(result.getCountMutantDna(), is(2L));
        assertThat(result.getCountHumanDna(), is(8L));
        assertThat(result.getRatio(), is(0.25D));
    }
}