package com.szymonz.recruitmenttask.service;

import com.szymonz.recruitmenttask.service.calculables.Calculable;
import com.szymonz.recruitmenttask.service.calculators.Calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationService.class);
    private final Calculator<BigDecimal> calculator;
    private final List<Calculable<BigDecimal>> calculables;

    @Autowired
    public CalculationService(Calculator<BigDecimal> calculator, List<Calculable<BigDecimal>> calculables) {
        this.calculator = calculator;
        this.calculables = calculables;
    }

    public Number getResult() throws Exception {
        BigDecimal result = BigDecimal.ZERO;
        for (Calculable<BigDecimal> calculable: calculables) {
            result = calculator.calculate(result, calculable.getValue());
        }
        LOGGER.info("Result of calculation is " + result);
        return result;
    }
}
