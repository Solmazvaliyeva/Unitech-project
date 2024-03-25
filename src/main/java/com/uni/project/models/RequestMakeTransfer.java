package com.uni.project.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
@Builder
public class RequestMakeTransfer {
    @JsonProperty(value = "sender_iban")

    private String senderIban;

    @JsonProperty(value = "receiver_iban")

    private String receiverIban;
    private Double amount;


    public String getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String senderIban) {
        this.senderIban = senderIban;
    }


    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiverIban) {
        this.receiverIban = receiverIban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
