
package com.bring.web.services;

import com.bring.web.dto.PaymentInfoDTO;
import com.bring.web.interfaces.PaymentsInterface;
import com.bring.web.models.PaymentInfo;

import java.time.LocalDateTime;
import java.util.UUID;

public class QrcodePayService implements PaymentsInterface {
        @Override
        public void processPayment(PaymentInfoDTO paymentInfoDTO) {

            PaymentInfo paymentInfo = new PaymentInfo(
                    UUID.randomUUID().toString(), // paymentId
                    paymentInfoDTO.getPaymentMethod(),
                    paymentInfoDTO.getAmount(),
                    paymentInfoDTO.getCurrency(),
                    paymentInfoDTO.getCustomerId(),
                    LocalDateTime.now().toString(), // transactionDate
                    "PENDING" // status
            );

            System.out.println("Paying by USSD Code: " + paymentInfo.toString());
        }
}
