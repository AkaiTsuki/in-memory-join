package com.example.memoryjoin.util;

import com.example.memoryjoin.model.car.PaymentVO;
import com.example.memoryjoin.model.payer.PaymentDTO;

public class PaymentConverter {
    public static PaymentVO convert(PaymentDTO dto) {
        if(dto == null) {
            return null;
        }

        PaymentVO vo = new PaymentVO();
        vo.setPayAmt(dto.getPayAmt());
        vo.setOrderId(dto.getOrderId());
        return vo;
    }
}
