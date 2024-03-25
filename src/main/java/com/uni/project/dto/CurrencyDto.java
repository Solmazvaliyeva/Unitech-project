package com.uni.project.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CurrencyDto {

    private String from;
    private BigDecimal amount;

    private Timestamp timestamp;

    private String terms;

    private CurrencyTo[] to;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public CurrencyTo[] getTo() {
        return to;
    }

    public void setTo(CurrencyTo[] to) {
        this.to = to;
    }
}
