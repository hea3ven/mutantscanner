package com.mribecky.mutantscanner.model;

import com.mribecky.mutantscanner.DnaSequence;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(indexes = {@Index(name = "idx_dna_sequence", columnList = "sequence", unique = true)})
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = DnaSequence.WIDTH * DnaSequence.HEIGHT, unique = true)
    @Size(min = DnaSequence.WIDTH * DnaSequence.HEIGHT,
            max = DnaSequence.WIDTH * DnaSequence.HEIGHT)
    private String sequence;

    @Column(name = "is_mutant")
    private boolean mutant;

    public Dna() {
    }

    public Dna(String[] dna, boolean mutant) {
        sequence = Arrays.stream(dna).collect(Collectors.joining());
        this.mutant = mutant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
