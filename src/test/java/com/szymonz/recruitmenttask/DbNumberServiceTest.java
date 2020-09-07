package com.szymonz.recruitmenttask;

import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.repository.DbNumberRepository;
import com.szymonz.recruitmenttask.service.calculables.DbNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DbNumberServiceTest {

    private final DbNumberService dbNumberService;
    private final DbNumberRepository numberRepository = mock(DbNumberRepository.class);

    @Autowired
    public DbNumberServiceTest(DbNumberService dbNumberService) {
        this.dbNumberService = dbNumberService;
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
