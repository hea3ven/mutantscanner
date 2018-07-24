package com.mribecky.mutantscanner.service;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class PingControllerIntegrationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl");
    }

    @Test
    public void testPing() {
        get("/ping").then().body(is("pong"));
    }
}
