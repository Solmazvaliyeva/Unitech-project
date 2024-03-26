package com.uni.project.controller;

import com.uni.project.client.CurrencyClient;
import com.uni.project.dto.CurrencyDto;
import com.uni.project.dto.CurrencyTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CurrencyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CurrencyRateTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyClient client;

    @Test
    public void CurrencyController_GetCurrencyRate_ReturnCurrencyDto() throws Exception {
        CurrencyTo currencyTo = CurrencyTo.builder().quoteCurrency("EUR").mid(new BigDecimal("1.234567")).build();

        CurrencyDto currencyDto = CurrencyDto.builder().from("USD").to(new CurrencyTo[]{currencyTo})
                .amount(currencyTo.getMid()).build();

        when(client.getCurrency("USD" ,"EUR" ,new BigDecimal("1"))).thenReturn(currencyDto);
        BigDecimal amount = currencyDto.getAmount();
        mockMvc.perform(get("/api/v0/currency-rate/").param("from" ,"USD")
                .param("to" ,"EUR").param("amount" ,"1")
                .content(MediaType.ALL_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                ;

    }
}
