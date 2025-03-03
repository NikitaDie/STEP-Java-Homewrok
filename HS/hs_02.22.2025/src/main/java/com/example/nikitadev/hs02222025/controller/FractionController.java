package com.example.nikitadev.hs02222025.controller;

import com.example.nikitadev.hs02222025.dto.FractionPair;
import com.example.nikitadev.hs02222025.model.rest.Fraction;
import com.example.nikitadev.hs02222025.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FractionController {

    private final FractionService fractionService;

    @Autowired
    public FractionController(FractionService fractionService) {
        this.fractionService = fractionService;
    }

    @GetMapping("/is-proper")
    public ResponseEntity<Boolean> isProper(
        @RequestParam int numerator,
        @RequestParam int denominator
    ) {
        Fraction fraction = new Fraction(numerator, denominator);
        boolean result = fractionService.isProper(fraction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reduce")
    public ResponseEntity<Fraction> reduce(
        @RequestParam int numerator,
        @RequestParam int denominator
    ) {
        Fraction fraction = new Fraction(numerator, denominator);
        Fraction reduced = fractionService.reduce(fraction);
        return ResponseEntity.ok(reduced);
    }

    @PostMapping("/add")
    public ResponseEntity<Fraction> add(
        @RequestBody FractionPair fractionPair
    ) {
        Fraction result = fractionService.add(
            fractionPair.fraction1(),
            fractionPair.fraction2()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/subtract")
    public ResponseEntity<Fraction> subtract(
        @RequestBody FractionPair fractionPair
    ) {
        Fraction result = fractionService.subtract(
            fractionPair.fraction1(),
            fractionPair.fraction2()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/multiply")
    public ResponseEntity<Fraction> multiply(
        @RequestBody FractionPair fractionPair
    ) {
        Fraction result = fractionService.multiply(
            fractionPair.fraction1(),
            fractionPair.fraction2()
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping("/divide")
    public ResponseEntity<Fraction> divide(
        @RequestBody FractionPair fractionPair
    ) {
        Fraction result = fractionService.divide(
            fractionPair.fraction1(),
            fractionPair.fraction2()
        );
        return ResponseEntity.ok(result);
    }
}
