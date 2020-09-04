package com.szymonz.recruitmenttask.service.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SumCalculator implements Calculator<BigDecimal> {

    @Override
    public BigDecimal calculate(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }
}
