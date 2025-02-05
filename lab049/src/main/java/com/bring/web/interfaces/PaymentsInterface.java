package com.bring.web.interfaces;

import com.bring.web.dto.PaymentInfoDTO;

public interface PaymentsInterface {
    void processPayment(PaymentInfoDTO paymentInfoDTO);
    String checkPaymentStatus(String id);
}