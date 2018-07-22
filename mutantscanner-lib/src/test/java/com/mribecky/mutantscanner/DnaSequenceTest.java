package com.mribecky.mutantscanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DnaSequenceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_dnaWithInvalidHeight_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        new DnaSequence(new String[] {"AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA"});
    }

    @Test
    public void constructor_dnaWithInvalidWidth_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        new DnaSequence(new String[] {"AAAAAA", "AAAAAAA", "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA"});
    }

    @Test
    public void constructor_dnaWithInvalidGene_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        new DnaSequence(new String[] {"BAAAAA", "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA"});
    }
}