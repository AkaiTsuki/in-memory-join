package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO findById(String id);
    List<PaymentDTO> findByIds(List<String> ids);
}
