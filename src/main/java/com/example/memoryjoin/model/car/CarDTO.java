package com.example.memoryjoin.model.car;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String model;
    private Integer reservePrice;
    private Long manufactureId;
    private String paymentId;
}
