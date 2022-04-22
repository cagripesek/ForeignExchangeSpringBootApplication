package com.example.foreignExchangeSpringBootApplication.repository;

import com.example.foreignExchangeSpringBootApplication.model.ConversionTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ConversionTransactionRepositorty extends JpaRepository<ConversionTransaction, Long> {
    Page<ConversionTransaction> findConversionTransactionsByTransactionDateIsGreaterThanEqual(Date transactionDate, Pageable pageable);

    Page<ConversionTransaction> findConversionTransactionsByTransactionId(Long transactionId, Pageable pageable);

    Page<ConversionTransaction> findConversionTransactionsByTransactionIdAndTransactionDateIsGreaterThanEqual(Long transactionId, Date transactionDate, Pageable pageable);

    Page<ConversionTransaction> findAll(Pageable pageable);
}
