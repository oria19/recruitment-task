package com.szymonz.recruitmenttask.config;

import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.model.DbNumberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Component
public class DbInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbInit.class);
    private static final Random RANDOM = new Random();

    private static final int RANGE = 10_000;
    private static final int NUMBER_OF_ROWS = 100;

    @Autowired
    private DbNumberRepository dbNumberRepository;

    @PostConstruct
    private void postConstruct() {
        LOGGER.info("H2 DB initialization");
        List<DbNumber> randomNumbers =
                RANDOM.doubles(NUMBER_OF_ROWS)
                        .mapToObj(doubleValue -> new DbNumber(valueOf(doubleValue * RANGE)))
                        .collect(Collectors.toList());
        dbNumberRepository.saveAll(randomNumbers);
    }
}