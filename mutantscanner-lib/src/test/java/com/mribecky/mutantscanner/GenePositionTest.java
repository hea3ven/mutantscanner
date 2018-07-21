package com.mribecky.mutantscanner;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

public class GenePositionTest {

    @Test
    public void equals() {
        EqualsVerifier.forClass(GenePosition.class).verify();
    }
}