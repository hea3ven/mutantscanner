package com.mribecky.mutantscanner.controller.api;

import com.mribecky.mutantscanner.service.DnaStats;
import com.mribecky.mutantscanner.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    @Autowired
    private MutantService mutantService;

    @GetMapping("/stats")
    public DnaStats stats() {
        return mutantService.getDnaStats();
    }
}
