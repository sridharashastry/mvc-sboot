package com.bring.web.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class PaymentInfo {

    @Id
    private String paymentId;
    private String paymentMethod;
    private Double amount;
    private String currency;
    private String customerId;
    private String transactionDate;
    private String status;
}