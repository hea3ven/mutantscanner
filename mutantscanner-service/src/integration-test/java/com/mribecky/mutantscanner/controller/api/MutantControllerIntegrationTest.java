package com.mribecky.mutantscanner.controller.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.with;

public class MutantControllerIntegrationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl");
    }

    @Test
    public void mutant_dnaWithMutantGene_status200() {
        with()
                .contentType(ContentType.JSON)
                .body("{\"dna\": [\"CCCCTA\", \"ATGCGA\", \"CAGTGC\", \"TTCTGT\", \"AGAACG\", "
                        + "\"TCACTG\"]}")
                .post("/mutant")
                .then()
                .statusCode(200);
    }

    @Test
    public void mutant_dnaWithNoMutantGene_status403() {
        with()
                .contentType(ContentType.JSON)
                .body("{\"dna\": [\"ATGCCA\", \"CAGTGC\", \"TTCTGT\", \"AGAAGG\", \"CACCTA\", "
                        + "\"TCACTG\"]}")
                .post("/mutant")
                .then()
                .statusCode(403);
    }

    @Test
    public void mutant_invalidDna_status400() {
        with()
                .contentType(ContentType.JSON)
                .body("{\"dna\": [\"ATGCCA\", \"CAGTGC\", \"TTCTGT\", \"AGAAGG\", \"CACCTA\"]}")
                .post("/mutant")
                .then()
                .statusCode(400);
    }
}
