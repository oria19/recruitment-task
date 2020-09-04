package com.szymonz.recruitmenttask.config;

import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.repository.DbNumberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import java.security.SecureRandom;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Configuration
public class DbConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);
    private static final Random RANDOM = new SecureRandom();

    private static final int RANGE = 10_000;
    private static final int NUMBER_OF_ROWS = 100;
    private final DbNumberRepository dbNumberRepository;

    @Autowired
    public DbConfig(DbNumberRepository dbNumberRepository) {
        this.dbNumberRepository = dbNumberRepository;
    }

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