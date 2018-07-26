package com.mribecky.mutantscanner.controller.api;

import com.mribecky.mutantscanner.DnaSequence;
import com.mribecky.mutantscanner.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody MutantScanRequest scanRequest) {
        try {
            boolean isMutant = mutantService.isMutant(new DnaSequence(scanRequest.getDna()));
            if (isMutant) {
                return ResponseEntity.ok("MATCH");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NOT MATCH");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
