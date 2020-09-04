package com.szymonz.recruitmenttask.service.calculators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SumCalculator implements Calculator<BigDecimal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SumCalculator.class);

    @Override
    public BigDecimal calculate(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }
}
