package com.example.memoryjoin.service;

import com.example.memoryjoin.model.car.CarDTO;
import com.example.memoryjoin.model.car.CarDetailDTO;
import com.example.memoryjoin.model.car.CarListDTO;

import java.util.List;

public interface CarService {
    public CarDTO findCar(Long id);
    public CarDetailDTO findCarDetail(Long id);
    public CarDetailDTO findCarDetailV2(Long id);
    public List<CarListDTO> listCars();
    public List<CarListDTO> listCarsV2();
}
