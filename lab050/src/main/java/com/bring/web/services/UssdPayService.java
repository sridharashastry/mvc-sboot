package com.bring.web.services;

import com.bring.web.dto.PaymentInfoDTO;
import com.bring.web.interfaces.PaymentsInterface;
import com.bring.web.models.PaymentInfo;
import com.bring.web.repo.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UssdPayService implements PaymentsInterface {

    private final PaymentsRepository paymentsRepository;

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

            PaymentInfo saved = paymentsRepository.save(paymentInfo);

            System.out.println("Paying by USSD Code: " + paymentInfo.toString());
        }

    @Override
    public String checkPaymentStatus(String id) {


        paymentsRepository.findById(id);

        return "";
    }

    public List<PaymentInfo> getPayments(){

            return paymentsRepository.findAll();
    }

}
