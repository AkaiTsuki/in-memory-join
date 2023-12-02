package com.example.memoryjoin.model.car;

import com.example.memoryjoin.core.JoinInMemory;
import com.example.memoryjoin.model.payer.ManufactureDTO;
import lombok.Data;

import java.util.List;

@Data
public class CarListDTO {
    private CarDTO car;

    @JoinInMemory(
            keyFromSourceData = "car.manufactureId",
            loader = "@manufactureService.findByIds(#root)",
            keyFromJoinData = "id",
            converter = "T(com.example.memoryjoin.util.ManufactureConverter).convert(#root)"
    )
    private ManufactureVO manufacture;

    @JoinInMemory(
            keyFromSourceData = "car.id",
            loader = "@tagService.findCarsTags(#root)",
            keyFromJoinData = "carId",
            converter = "T(com.example.memoryjoin.util.TagConverter).convert(#root)"
    )
    private List<TagVO> tags;

}
