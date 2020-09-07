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
public class CalculationServiceImpl implements CalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationServiceImpl.class);
    private final Calculator<BigDecimal> calculator;
    private final List<Calculable<BigDecimal>> calculables;

    @Autowired
    public CalculationServiceImpl(Calculator<BigDecimal> calculator, List<Calculable<BigDecimal>> calculables) {
        this.calculator = calculator;
        this.calculables = calculables;
    }

    @Override
    public Number getResult() {
        BigDecimal result = BigDecimal.ZERO;
        for (Calculable<BigDecimal> calculable: calculables) {
            result = calculator.calculate(result, calculable.getValue());
        }
        LOGGER.info("Result of calculation is " + result);
        return result;
    }
}
