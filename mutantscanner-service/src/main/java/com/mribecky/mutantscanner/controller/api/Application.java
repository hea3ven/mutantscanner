package com.mribecky.mutantscanner.controller.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mribecky.mutantscanner"})
@EnableJpaRepositories(basePackages = {"com.mribecky.mutantscanner"})
@EntityScan(basePackages = {"com.mribecky.mutantscanner"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
