package com.bring.web.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInfo {
    private String paymentId;
    private String paymentMethod;
    private Double amount;
    private String currency;
    private String customerId;
    private String transactionDate;
    private String status;
}