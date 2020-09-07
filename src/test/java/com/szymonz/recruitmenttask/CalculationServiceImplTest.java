package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.service.CalculationService;
import com.szymonz.recruitmenttask.service.CalculationServiceImpl;
import com.szymonz.recruitmenttask.service.calculables.Calculable;
import com.szymonz.recruitmenttask.service.calculables.DbNumberService;
import com.szymonz.recruitmenttask.service.calculables.RandomOrgService;

import com.szymonz.recruitmenttask.service.calculators.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculationServiceImplTest {

    @Mock
    private final DbNumberService numberService;
    @Mock
    private final RandomOrgService randomOrgService;

    private final Calculator<BigDecimal> calculator;

    private CalculationService calculationService;

    @Autowired
    public CalculationServiceImplTest(DbNumberService numberService, RandomOrgService randomOrgService, Calculator<BigDecimal> calculator) {
        this.calculator = calculator;
        this.numberService = numberService;
        this.randomOrgService = randomOrgService;
    }

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.calculationService = new CalculationServiceImpl(calculator, Arrays.asList(numberService, randomOrgService));
        BigDecimal returnByServiceRandomOrg = new BigDecimal("45.89");
        BigDecimal returnByServiceDbNumber = new BigDecimal("38.72");
        when(numberService.getValue()).thenReturn(returnByServiceRandomOrg);
        when(randomOrgService.getValue()).thenReturn(returnByServiceDbNumber);
    }

    @Test
    public void calculationServiceReturnsResultTest() {

        // given
        Number expectedResult = new BigDecimal("84.61");

        // when
        Number result = calculationService.getResult();

        // then
        assertEquals(result, expectedResult);
    }
}
