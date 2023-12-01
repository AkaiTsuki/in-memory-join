package com.example.memoryjoin.model.car;

import com.example.memoryjoin.core.JoinInMemory;
import com.example.memoryjoin.model.payer.ManufactureDTO;
import com.example.memoryjoin.model.payer.PaymentDTO;
import lombok.Data;

@Data
public class CarDetailDTO {
    private CarDTO car;
    @JoinInMemory(
            loader = "@manufactureService.findById(car.manufactureId)"
    )
    private ManufactureDTO manufacture;
    @JoinInMemory(
            loader = "@paymentService.findById(car.paymentId)"
    )
    private PaymentDTO payment;
}
