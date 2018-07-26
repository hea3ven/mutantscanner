package com.mribecky.mutantscanner.controller.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PingControllerTest {

    @Test
    public void ping_call_pongs() {
        PingController controller = new PingController();

        String result = controller.ping();

        assertThat(result, is("pong"));
    }
}