package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.service.calculators.SumCalculator;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SumCalculatorTest {

    private final SumCalculator sumCalculator;

    @Autowired
    public SumCalculatorTest(SumCalculator sumCalculator) {
        this.sumCalculator = sumCalculator;
    }

    @Test
    public void contextLoads() {
        assertNotNull(sumCalculator);
    }

    @Test
    public void twoNumbersAdditionTest() {
        // given
        BigDecimal arg1 = new BigDecimal("100.85");
        BigDecimal arg2 = new BigDecimal("8.42");
        BigDecimal expectedResult = new BigDecimal("109.27");

        // when
        BigDecimal result = sumCalculator.calculate(arg1, arg2);

        // then
        assertEquals(result, expectedResult);
    }
}
