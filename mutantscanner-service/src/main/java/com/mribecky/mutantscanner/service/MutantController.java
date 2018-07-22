package com.mribecky.mutantscanner.service;

import com.mribecky.mutantscanner.DnaSequence;
import com.mribecky.mutantscanner.MutantScanner;
import com.mribecky.mutantscanner.service.model.MutantScanRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody MutantScanRequest scanRequest) {
        try {
            boolean isMutant =
                    new MutantScanner(new DnaSequence(scanRequest.getDna())).findNextMutantChain()
                            .isPresent();
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
