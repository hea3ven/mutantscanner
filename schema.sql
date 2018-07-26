CREATE TABLE dna (
    id bigint NOT NULL,
    is_mutant boolean,
    sequence character varying(36) UNIQUE,
	PRIMARY KEY (id)
);

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE UNIQUE INDEX idx_dna_sequence ON dna (sequence);

GRANT ALL ON dna TO mutantscanner;
GRANT ALL ON hibernate_sequence TO mutantscanner;
