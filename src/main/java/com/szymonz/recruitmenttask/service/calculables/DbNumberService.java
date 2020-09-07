package com.szymonz.recruitmenttask.service.calculables;

import com.szymonz.recruitmenttask.exceptions.NumberNotFoundException;
import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.repository.DbNumberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DbNumberService implements Calculable<BigDecimal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbNumberService.class);
    private static final int MIN = 1;
    private final DbNumberRepository numberRepository;

    @Autowired
    public DbNumberService(DbNumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @Override
    public BigDecimal getValue() {
        Optional<DbNumber> fetchedNumberOptional = numberRepository.findById(getRandomIndex());
        DbNumber numberObject = fetchedNumberOptional.orElseThrow(() -> new NumberNotFoundException("Number not found in DB"));
        LOGGER.info("Get " + numberObject.getNumber() + " from DB");
        return numberObject.getNumber();
    }

    private int getRandomIndex() {
        long maxIndex = numberRepository.count();
        return ((int) (Math.random() * (maxIndex - MIN))) + MIN;
    }
}
