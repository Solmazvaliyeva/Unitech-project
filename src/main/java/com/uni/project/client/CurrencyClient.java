package com.uni.project.client;

import com.uni.project.dto.CurrencyDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "currency-api" ,url = "https://xecdapi.xe.com/v1/convert_from.json/")
public interface CurrencyClient {


    @GetMapping()
    @Headers({"Content-Type: application/json" })
    CurrencyDto getCurrency(@RequestParam String from , @RequestParam String to , @RequestParam BigDecimal amount);



}
