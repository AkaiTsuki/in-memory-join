package com.example.memoryjoin.controller;

import com.example.memoryjoin.model.car.CarDTO;
import com.example.memoryjoin.model.car.CarDetailDTO;
import com.example.memoryjoin.model.car.CarListDTO;
import com.example.memoryjoin.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CarService carService;

    @GetMapping("/car/{id}")
    public CarDTO findCar(@PathVariable("id") Long id) {
        return carService.findCar(id);
    }

    @GetMapping("/cardetail/{id}")
    public CarDetailDTO findCarDetail(@PathVariable("id") Long id) {
        return carService.findCarDetail(id);
    }

    @GetMapping("/cardetail/v2/{id}")
    public CarDetailDTO findCarDetailV2(@PathVariable("id") Long id) {
        return carService.findCarDetailV2(id);
    }

    @GetMapping("/carlist/")
    public List<CarListDTO> listCars() {
        return carService.listCars();
    }

    @GetMapping("/carlist/v2")
    public List<CarListDTO> listCarsV2() {
        return carService.listCarsV2();
    }
}
