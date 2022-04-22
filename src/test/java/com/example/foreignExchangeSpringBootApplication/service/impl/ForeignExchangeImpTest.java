package com.example.foreignExchangeSpringBootApplication.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ForeignExchangeImpTest {

    @Autowired
    private ForeignExchangeImp foreignExchangeImp;

    @Test
    public void whenGetExchangeRateCalledWithValidRequest_itShouldReturnValidResult() throws Exception {
        String sourceCurrency = "USD";
        String targetCurrency = "TRY";

        assertNotNull(foreignExchangeImp.getExchangeRate(sourceCurrency, targetCurrency));
    }

    @Test
    public void whenGetExchangeRateCalledWithNotValidRequest_itShouldReturnException() {
        String sourceCurrency = "asd";
        String targetCurrency = "dsa";

        Exception exception = assertThrows(Exception.class, () -> {
            foreignExchangeImp.getExchangeRate(sourceCurrency, targetCurrency);
        });
        String errorMessage = "error occurred";
        assertTrue(exception.getMessage().contains(errorMessage));
    }

    @Test
    public void whenDoConversionCalledWithValidRequest_itShouldReturnValidResult() throws Exception {
        Integer sourceAmount = 10;
        String sourceCurrency = "USD";
        String targetCurrency = "TRY";

        assertNotNull(foreignExchangeImp.doConversion(sourceAmount, sourceCurrency, targetCurrency));
    }

    @Test
    public void whenDoConversionCalledWithNotValidRequest_itShouldReturnException() throws Exception {
        Integer sourceAmount = 10;
        String sourceCurrency = "qwe";
        String targetCurrency = "rty";

        Exception exception = assertThrows(Exception.class, () -> {
            foreignExchangeImp.doConversion(sourceAmount, sourceCurrency, targetCurrency);
        });
        assertTrue(exception.getClass().equals(Exception.class));

        Exception exception2 = assertThrows(Exception.class, () -> {
            foreignExchangeImp.saveConversion(10.0, null, null, null);
        });
        assertTrue(exception2.getClass().equals(Exception.class));

        Exception exception3 = assertThrows(Exception.class, () -> {
            foreignExchangeImp.saveConversion(10.0, 5, null, null);
        });
        assertTrue(exception3.getClass().equals(TransactionSystemException.class));
    }

    @Test
    public void whenGetConversionListCalledWithValidRequest_itShouldReturnValidResult() throws Exception {
        Long transactionId = 10L;
        Date transactionDate = new Date();
        Pageable pageable = null;

        assertNotNull(foreignExchangeImp.getConversionList(transactionId, transactionDate, pageable));

        transactionId = null;
        transactionDate = new Date();
        pageable = null;

        assertNotNull(foreignExchangeImp.getConversionList(transactionId, transactionDate, pageable));

        transactionId = 10L;
        transactionDate = null;
        pageable = null;

        assertNotNull(foreignExchangeImp.getConversionList(transactionId, transactionDate, pageable));
    }

    @Test
    public void whenGetConversionListCalledWithNotValidRequest_itShouldReturnException() {
        Exception exception = assertThrows(Exception.class, () -> {
            foreignExchangeImp.getConversionList(null, null, null);
        });
        assertTrue(exception.getClass().equals(UnexpectedRollbackException.class));
    }
}