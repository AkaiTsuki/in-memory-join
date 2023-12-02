package com.example.memoryjoin.model.payer;

import lombok.Data;

@Data
public class PaymentDTO {
    private String orderId;
    private Long payAmt;
    private Long carId;
}
