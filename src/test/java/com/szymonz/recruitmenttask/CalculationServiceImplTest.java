package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.service.CalculationServiceImpl;
import com.szymonz.recruitmenttask.service.calculables.Calculable;
import com.szymonz.recruitmenttask.service.calculables.DbNumberService;
import com.szymonz.recruitmenttask.service.calculables.RandomOrgService;
import com.szymonz.recruitmenttask.service.calculators.SumCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculationServiceImplTest {

    private final List<Calculable<BigDecimal>> calculables = new ArrayList<>(2);
    private final SumCalculator calculator = new SumCalculator();
    private final CalculationServiceImpl calculationService = new CalculationServiceImpl(calculator, calculables);

    @BeforeTestMethod
    public void before() {
        BigDecimal returnByServiceRandomOrg = new BigDecimal("45.89");
        BigDecimal returnByServiceDbNumber = new BigDecimal("38.72");
        calculables.add(mock(RandomOrgService.class));
        calculables.add(mock(DbNumberService.class));
        when(calculables.get(0).getValue()).thenReturn(returnByServiceRandomOrg);
        when(calculables.get(1).getValue()).thenReturn(returnByServiceDbNumber);
    }

    @Test
    public void calculationServiceReturnsResultTest() {

        // given
        Number expectedResult = new BigDecimal("84.61");

        // when
        Number result = calculationService.getResult();

        // then
        assertThat(result.equals(expectedResult));
    }
}
