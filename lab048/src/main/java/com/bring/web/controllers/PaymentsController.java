package com.bring.web.controllers;

// PaymentController.java
import com.bring.web.dto.PaymentInfoDTO;
import com.bring.web.services.PaylinkPayService;
import com.bring.web.services.QrcodePayService;
import com.bring.web.services.UssdPayService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentsController {

    private final UssdPayService ussdPayService;
    private final QrcodePayService qrcodePayService;
    private final PaylinkPayService paylinkPayService;



    @PostMapping("/ussd")
    public String processUSSD(@RequestBody PaymentInfoDTO paymentInfoDTO) {
        ussdPayService.processPayment(paymentInfoDTO);
        return "USSD Payment Processed";
    }


    @PostMapping("/qrcode")
    public String processQRCode(@RequestBody PaymentInfoDTO paymentInfoDTO) {
        qrcodePayService.processPayment(paymentInfoDTO);
        return "QR Code Payment Processed";
    }

    @PostMapping("/paymentlink")
    public String processPaymentLink(@RequestBody PaymentInfoDTO paymentInfoDTO) {
        paylinkPayService.processPayment(paymentInfoDTO);
        return "Payment Link Processed";
    }



}