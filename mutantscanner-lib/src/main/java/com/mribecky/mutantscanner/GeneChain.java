package com.mribecky.mutantscanner;

import java.util.ArrayList;
import java.util.List;

public class GeneChain {

    private static final int COMPLETE_LENGTH = 4;

    private final char gene;
    private final ArrayList<GenePosition> positions;

    public GeneChain(char gene, GenePosition pos) {
        this.gene = gene;
        positions = new ArrayList<>();
        positions.add(pos);
    }

    public GeneChain(GeneChain other, GenePosition secondPos) {
        this(other.getGene(), other.positions.get(0));
        add(secondPos);
    }

    public char getGene() {
        return gene;
    }

    public GenePosition getLastPosition() {
        return positions.get(positions.size() - 1);
    }

    public void add(GenePosition pos) {
        if (hasDirection() && !getNextPosition().equals(pos)) {
            throw new IllegalArgumentException("Position " + pos + " does not follow the chain");
        }
        positions.add(pos);
    }

    public GenePosition getNextPosition() {
        if (!hasDirection()) {
            throw new NoDefinedDirectionException();
        }

        GenePosition lastPosition = getLastPosition();
        return lastPosition.offset(positions.get(1).getColumn() - positions.get(0).getColumn(),
                positions.get(1).getRow() - positions.get(0).getRow());
    }

    public List<GenePosition> getPositions() {
        return positions;
    }

    public boolean isComplete() {
        return positions.size() >= COMPLETE_LENGTH;
    }

    public boolean hasDirection() {
        return positions.size() > 1;
    }
}
