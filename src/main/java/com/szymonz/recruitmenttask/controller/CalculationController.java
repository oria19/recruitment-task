package com.szymonz.recruitmenttask.controller;

import com.szymonz.recruitmenttask.service.CalculationService;
import com.szymonz.recruitmenttask.service.CalculationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    private final CalculationService calculationServiceImpl;

    @Autowired
    public CalculationController(CalculationServiceImpl calculator) {
        this.calculationServiceImpl = calculator;
    }

    @GetMapping(value = "/result")
    public Number getResult() {
        return calculationServiceImpl.getResult();
    }
}
