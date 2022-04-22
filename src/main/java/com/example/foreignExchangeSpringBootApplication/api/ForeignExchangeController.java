package com.example.foreignExchangeSpringBootApplication.api;

import com.example.foreignExchangeSpringBootApplication.dto.ConversionDTO;
import com.example.foreignExchangeSpringBootApplication.model.ConversionTransaction;
import com.example.foreignExchangeSpringBootApplication.service.ForeignExchangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ForeignExchangeController {

    @Autowired
    private ForeignExchangeService foreignExchangeService;

    @GetMapping(value = "/exchangeRate", consumes = MediaType.ALL_VALUE)
    @Tag(name = "getExchangeRate", description = "Get Exchange Rate According to Given Parameters")
    public Double getExchangeRate(@RequestParam(value = "sourceCurrency") String sourceCurrency, @RequestParam(value = "targetCurrency") String targetCurrency) throws Exception {
        return this.foreignExchangeService.getExchangeRate(sourceCurrency, targetCurrency);
    }

    @PostMapping(value = "/doConversion", consumes = MediaType.ALL_VALUE)
    @Tag(name = "doConversion", description = "Do Conversion According to Given Parameters")
    public ConversionDTO doConversion(@RequestParam(value = "sourceAmount") Integer sourceAmount, @RequestParam(value = "sourceCurrency") String sourceCurrency, @RequestParam(value = "targetCurrency") String targetCurrency) throws Exception {
        return this.foreignExchangeService.doConversion(sourceAmount, sourceCurrency, targetCurrency);
    }

    @GetMapping(value = "/getConversionList", consumes = MediaType.ALL_VALUE)
    @Tag(name = "getConversionList", description = "Get Conversion List According to Given Parameters (If no parameters is entered, the function gets all conversions.)")
    public ResponseEntity<List<ConversionTransaction>> getConversionList(@RequestParam(value = "transactionId", required = false) Long transactionId, @RequestParam(value = "transactionDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date transactionDate, Pageable pageable) throws Exception {
        Page<ConversionTransaction> page = this.foreignExchangeService.getConversionList(transactionId, transactionDate, pageable);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
    }
}
