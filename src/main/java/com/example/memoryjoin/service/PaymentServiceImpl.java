package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.PaymentDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PaymentDTO> findByIds(List<String> ids) {
        List<PaymentDTO> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(ids)) {
            return result;
        }

        return ids.stream().map(id->{
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setOrderId(id);
            paymentDTO.setPayAmt(1000 * Long.valueOf(id));
            return paymentDTO;
        }).collect(Collectors.toList());
    }
}
