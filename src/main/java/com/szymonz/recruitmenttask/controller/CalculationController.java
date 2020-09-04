package com.szymonz.recruitmenttask.controller;

import com.szymonz.recruitmenttask.service.calculables.Calculable;
import com.szymonz.recruitmenttask.service.calculators.Calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CalculationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationController.class);

    @Autowired
    private Calculator<BigDecimal> calculator;

    @Autowired
    private List<Calculable<BigDecimal>> calculables;

    @GetMapping(value = "/result")
    public Number getResult() throws Exception {
        BigDecimal result = BigDecimal.ZERO;
        for (Calculable<BigDecimal> calculable: calculables) {
            result = calculator.calculate(result, calculable.getValue());
        }
        LOGGER.info("Result of calculation is " + result);
        return result;
    }
}
