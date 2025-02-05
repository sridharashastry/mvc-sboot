package com.bring.web.interfaces;

import com.bring.web.dto.PaymentInfoDTO;

public interface PaymentsInterface {
    void processPayment(PaymentInfoDTO paymentInfoDTO);
}