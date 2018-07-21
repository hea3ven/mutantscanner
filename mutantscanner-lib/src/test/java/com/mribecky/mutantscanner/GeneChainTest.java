package com.mribecky.mutantscanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GeneChainTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void add_nonConsecutive_throwsException() {
        GeneChain geneChain = new GeneChain('A', new GenePosition(1, 1));
        geneChain.add(new GenePosition(2, 1));

        thrown.expect(IllegalArgumentException.class);
        geneChain.add(new GenePosition(2, 2));
    }

    @Test
    public void getNextPosition_horizontalChain_returnsNextXPosition() {
        GeneChain geneChain = new GeneChain('A', new GenePosition(1, 1));
        geneChain.add(new GenePosition(2, 1));

        GenePosition result = geneChain.getNextPosition();

        assertThat(result, is(new GenePosition(3, 1)));
    }

    @Test
    public void getNextPosition_verticalChain_returnsNextYPosition() {
        GeneChain geneChain = new GeneChain('A', new GenePosition(1, 1));
        geneChain.add(new GenePosition(1, 2));

        GenePosition result = geneChain.getNextPosition();

        assertThat(result, is(new GenePosition(1, 3)));
    }

    @Test
    public void getNextPosition_diagonalChain_returnsNextXyPosition() {
        GeneChain geneChain = new GeneChain('A', new GenePosition(1, 1));
        geneChain.add(new GenePosition(2, 2));

        GenePosition result = geneChain.getNextPosition();

        assertThat(result, is(new GenePosition(3, 3)));
    }

    @Test
    public void getNextPosition_onePosition_throwsException() {
        GeneChain geneChain = new GeneChain('A', new GenePosition(1, 1));

        thrown.expect(NoDefinedDirectionException.class);
        geneChain.getNextPosition();
    }
}