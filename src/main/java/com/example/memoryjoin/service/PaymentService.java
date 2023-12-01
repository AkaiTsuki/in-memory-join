package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.PaymentDTO;

public interface PaymentService {
    PaymentDTO findById(String id);
}
