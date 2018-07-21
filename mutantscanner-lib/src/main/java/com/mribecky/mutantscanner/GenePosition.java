package com.mribecky.mutantscanner;

import java.text.MessageFormat;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class GenePosition {

    private final int column;
    private final int row;

    public GenePosition(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public GenePosition offset(int columnOffset, int rowOffset) {
        return new GenePosition(column + columnOffset, row + rowOffset);
    }

    public boolean isNextTo(GenePosition pos) {
        return Math.abs(column - pos.column) <= 1 && Math.abs(row - pos.row) <= 1;
    }

    @Override
    public String toString() {
        return MessageFormat.format("GenePosition({0}, {1})", column, row);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenePosition)) {
            return false;
        }
        GenePosition rhs = (GenePosition) obj;
        return new EqualsBuilder().append(column, rhs.column).append(row, rhs.row).build();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder().append(column).append(row).build();
    }
}
