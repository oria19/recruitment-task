package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.repository.DbNumberRepository;
import com.szymonz.recruitmenttask.service.calculables.DbNumberService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.util.Optional.of;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@SpringBootTest
public class DbNumberServiceTest {

    private final DbNumberService dbNumberService;
    @Mock
    private final DbNumberRepository numberRepository;

    @Autowired
    public DbNumberServiceTest(DbNumberService dbNumberService, DbNumberRepository numberRepository) {
        this.dbNumberService = dbNumberService;
        this.numberRepository = numberRepository;
    }

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(dbNumberService).isNotNull();
    }

    @Test
    public void getNumberFromDbTest() {

        // given
        final Long recordIndex = 1L;
        when(numberRepository.count()).thenReturn(recordIndex);
        when(numberRepository.findById(recordIndex.intValue())).thenReturn(of(new DbNumber(BigDecimal.TEN)));

        // when
        BigDecimal valueGetFromDB = dbNumberService.getValue();

        // then
        assertThat(valueGetFromDB.equals(BigDecimal.TEN));
    }
}
