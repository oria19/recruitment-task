package com.szymonz.recruitmenttask.controller;

import com.szymonz.recruitmenttask.service.CalculationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    private final CalculationService calculator;

    @Autowired
    public CalculationController(CalculationService calculator) {
        this.calculator = calculator;
    }

    @GetMapping(value = "/result")
    public Number getResult() throws Exception {
        return calculator.getResult();
    }
}
