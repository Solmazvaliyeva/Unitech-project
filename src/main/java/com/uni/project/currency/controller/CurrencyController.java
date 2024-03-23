package com.uni.project.currency.controller;

import com.uni.project.currency.CurrencyClient;
import com.uni.project.currency.dto.CurrencyDto;
import com.uni.project.currency.dto.CurrencyTo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@RestController
@RequestMapping("/api/v0/")
public class CurrencyController {

    private final CurrencyClient client;

    public CurrencyController(CurrencyClient client) {
        this.client = client;
    }

    @GetMapping("currency-rate/")
    public ResponseEntity<String> returnString(@RequestParam String from , @RequestParam String to , @RequestParam(defaultValue = "1") BigDecimal amount){
        CurrencyDto currencyDto = client.getCurrency(from ,to ,amount);
        System.out.println(currencyDto.getTerms() );
        CurrencyTo[] currencyTo = currencyDto.getTo();
        DecimalFormat df = new DecimalFormat("#.######");
        String rateResult = "Rate from " + from + " to " + to + " " +  df.format(currencyTo[0].getMid());

        return ResponseEntity.ok(rateResult);
    }
}
