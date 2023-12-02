package com.example.memoryjoin.model.car;

import com.example.memoryjoin.core.JoinInMemory;
import com.example.memoryjoin.model.payer.ManufactureDTO;
import com.example.memoryjoin.model.payer.PaymentDTO;
import lombok.Data;

@Data
public class CarDetailDTO {
    private CarDTO car;
    @JoinInMemory(
            loader = "@manufactureService.findById(car.manufactureId)",
            converter = "T(com.example.memoryjoin.util.ManufactureConverter).convert(#root)"
    )
    private ManufactureVO manufacture;
    @JoinInMemory(
            loader = "@paymentService.findById(car.paymentId)",
            converter = "T(com.example.memoryjoin.util.PaymentConverter).convert(#root)"
    )
    private PaymentVO payment;
}
