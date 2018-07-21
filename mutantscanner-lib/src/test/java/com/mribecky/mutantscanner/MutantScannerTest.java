package com.mribecky.mutantscanner;

import java.util.Optional;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class MutantScannerTest {

    @Test
    public void isMutant_noMutantSequence_returnsFalse() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCCA",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();

        assertThat(result, is(Optional.empty()));
    }

    @Test
    public void isMutant_horizontalMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "CCCCTA",
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAACG",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'C', 0, 0, 1, 0, 2, 0, 3, 0);
        assertThat(result2, is(Optional.empty()));
    }

    @Test
    public void isMutant_verticalMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "CCACTA",
                "ATGTGA",
                "CAGTGC",
                "TTCTGT",
                "AGATCG",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'T', 3, 1, 3, 2, 3, 3, 3, 4);
        assertThat(result2, is(Optional.empty()));
    }

    @Test
    public void isMutant_verticalMutantSequence2_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCGA",
                "CAGTGC",
                "TTCTGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'G', 4, 0, 4, 1, 4, 2, 4, 3);
        assertThat(result2, is(Optional.empty()));
    }

    @Test
    public void isMutant_diagonalMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCGA",
                "CCGTGC",
                "TTCTAT",
                "AGACGG",
                "CACCCA",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'C', 1, 1, 2, 2, 3, 3, 4, 4);
        assertThat(result2, is(Optional.empty()));
    }

    @Test
    public void isMutant_backwardsDiagonalMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCGA",
                "CACTGC",
                "TCCTAT",
                "CGACGG",
                "CACTCA",
                "TCACTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'C', 3, 0, 2, 1, 1, 2, 0, 3);
        assertThat(result2, is(Optional.empty()));
    }

    @Test
    public void isMutant_multipleMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCGA",
                "CGGGGC",
                "TTCTCT",
                "AGATGG",
                "CACTCA",
                "TCATTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();
        Optional<GeneChain> result3 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'G', 1, 1, 2, 1, 3, 1, 4, 1);
        assertFoundChain(result2.orElse(null), 'T', 3, 2, 3, 3, 3, 4, 3, 5);
        assertThat(result3, is(Optional.empty()));
    }

    @Test
    public void isMutant_intersectingMutantSequence_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCCA",
                "CGGGGC",
                "TTGTAT",
                "AGGAGG",
                "CACTCA",
                "TCATTG"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();
        Optional<GeneChain> result3 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'G', 1, 1, 2, 1, 3, 1, 4, 1);
        assertFoundChain(result2.orElse(null), 'G', 2, 0, 2, 1, 2, 2, 2, 3);
        assertThat(result3, is(Optional.empty()));
    }

    @Test
    public void isMutant_intersectingMutantSequence2_returnsTrue() {
        MutantScanner scanner = new MutantScanner(new DnaSequence(new String[] {
                "ATGCGA",
                "CGGAGC",
                "TTGTGT",
                "ACGGAG",
                "CACTGA",
                "TCATTA"}));

        Optional<GeneChain> result = scanner.findNextMutantChain();
        Optional<GeneChain> result2 = scanner.findNextMutantChain();
        Optional<GeneChain> result3 = scanner.findNextMutantChain();

        assertFoundChain(result.orElse(null), 'G', 2, 0, 2, 1, 2, 2, 2, 3);
        assertFoundChain(result2.orElse(null), 'G', 1, 1, 2, 2, 3, 3, 4, 4);
        assertThat(result3, is(Optional.empty()));
    }

    private void assertFoundChain(GeneChain result, char gene, int x1, int y1, int x2,
            int y2, int x3, int y3, int x4, int y4) {
        assertThat(result, notNullValue());
        assertThat(result.getGene(), is(gene));
        assertThat(result.getPositions(),
                contains(new GenePosition(x1, y1), new GenePosition(x2, y2),
                        new GenePosition(x3, y3), new GenePosition(x4, y4)));
    }
}
