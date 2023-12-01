package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.PaymentDTO;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentDTO findById(String id) {
        if(id == null) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setOrderId(id);
        paymentDTO.setPayAmt(1000 * Long.valueOf(id));
        return paymentDTO;
    }
}
