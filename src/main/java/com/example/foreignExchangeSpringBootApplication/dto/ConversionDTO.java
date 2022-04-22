package com.example.foreignExchangeSpringBootApplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionDTO {
    private Long transactionId;
    private Double amountInTargetCurrency;
}
