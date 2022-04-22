package com.example.foreignExchangeSpringBootApplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "conversion_transaction")
@Getter
@Setter
public class ConversionTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column
    @NotNull
    private Integer sourceAmount;

    @Column
    @NotNull
    private String sourceCurrency;

    @Column
    @NotNull
    private String targetCurrency;

    @Column
    @NotNull
    private Date transactionDate;
}
