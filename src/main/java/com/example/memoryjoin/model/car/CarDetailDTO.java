package com.example.memoryjoin.model.car;

import com.example.memoryjoin.core.JoinInMemory;
import lombok.Data;

import java.util.List;

@Data
public class CarDetailDTO {
    private CarDTO car;
    @JoinInMemory(
            keyFromSourceData = "car.manufactureId",
            loader = "@manufactureService.findByIds(#root)",
            keyFromJoinData = "id",
            converter = "T(com.example.memoryjoin.util.ManufactureConverter).convert(#root)"
    )
    private ManufactureVO manufacture;
    @JoinInMemory(
            keyFromSourceData = "car.paymentId",
            loader = "@paymentService.findByIds(#root)",
            keyFromJoinData = "orderId",
            converter = "T(com.example.memoryjoin.util.PaymentConverter).convert(#root)"
    )
    private PaymentVO payment;

    @JoinInMemory(
            keyFromSourceData = "car.id",
            loader = "@tagService.findCarTags(#root)",
            keyFromJoinData = "carId",
            converter = "T(com.example.memoryjoin.util.TagConverter).convert(#root)"
    )
    private List<TagVO> tags;
}
