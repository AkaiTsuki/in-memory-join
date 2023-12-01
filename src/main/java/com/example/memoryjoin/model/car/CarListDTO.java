package com.example.memoryjoin.model.car;

import com.example.memoryjoin.core.JoinInMemory;
import com.example.memoryjoin.model.payer.ManufactureDTO;
import lombok.Data;

@Data
public class CarListDTO {
    private CarDTO car;

    @JoinInMemory(
            loader = "@manufactureService.findById(car.manufactureId)"
    )
    private ManufactureDTO manufacture;

}
