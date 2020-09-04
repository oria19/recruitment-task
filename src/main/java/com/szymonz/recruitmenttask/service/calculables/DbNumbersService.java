package com.szymonz.recruitmenttask.service.calculables;

import com.szymonz.recruitmenttask.model.DbNumber;
import com.szymonz.recruitmenttask.repository.DbNumberRepository;

import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DbNumbersService implements Calculable<BigDecimal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbNumbersService.class);
    private static final int MIN = 1;
    private final DbNumberRepository numberRepository;

    @Autowired
    public DbNumbersService(DbNumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    @Override
    public BigDecimal getValue() throws Exception {
        try {
            BigDecimal fetchedNumber = getRandomRecordIndex().getNumber();
            LOGGER.info("Get " + fetchedNumber + " from DB");
            return fetchedNumber;
        } catch (NotFoundException exception) {
            LOGGER.error("An exception occured during fetch data.", exception);
            throw exception;
        }
    }

    private DbNumber getRandomRecordIndex() throws NotFoundException {
        long maxIndex = numberRepository.count();
        int index = ((int) (Math.random() * (maxIndex - MIN))) + MIN;
        Optional<DbNumber> randomNumber = numberRepository.findById(index);
        return randomNumber.orElseThrow(() -> new NotFoundException("Number not found in DB"));
    }
}
