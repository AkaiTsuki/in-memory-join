package com.example.memoryjoin.model.payer;

import lombok.Data;

@Data
public class ManufactureDTO {
    private Long id;
    private String name;
    private String factoryInfo;
    private Long carId;
}
