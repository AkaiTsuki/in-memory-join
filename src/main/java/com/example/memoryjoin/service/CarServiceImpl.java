package com.example.memoryjoin.service;

import com.example.memoryjoin.core.JoinInMemoryExecutor;
import com.example.memoryjoin.model.car.CarDTO;
import com.example.memoryjoin.model.car.CarDetailDTO;
import com.example.memoryjoin.model.car.CarListDTO;
import com.example.memoryjoin.model.payer.ManufactureDTO;
import com.example.memoryjoin.model.payer.PaymentDTO;
import com.example.memoryjoin.util.ManufactureConverter;
import com.example.memoryjoin.util.PaymentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private ManufactureService manufactureService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private JoinInMemoryExecutor joinInMemoryExecutor;

    @Override
    public CarDTO findCar(Long id) {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("model Y Car Detail");
        carDTO.setReservePrice(10000);
        carDTO.setId(id);
        carDTO.setManufactureId(100L);
        carDTO.setPaymentId("5050");
        return carDTO;
    }

    public CarDetailDTO findCarDetail(Long id) {
        CarDTO carDTO = findCar(id);

        ManufactureDTO manufactureDTO = manufactureService.findById(carDTO.getManufactureId());
        PaymentDTO paymentDTO = paymentService.findById(carDTO.getPaymentId());

        CarDetailDTO carDetailDTO = new CarDetailDTO();
        carDetailDTO.setCar(carDTO);
        carDetailDTO.setManufacture(ManufactureConverter.convert(manufactureDTO));
        carDetailDTO.setPayment(PaymentConverter.convert(paymentDTO));

        return carDetailDTO;
    }

    public CarDetailDTO findCarDetailV2(Long id) {
        CarDTO carDTO = findCar(id);

        CarDetailDTO carDetailDTO = new CarDetailDTO();
        carDetailDTO.setCar(carDTO);

        joinInMemoryExecutor.fetch(carDetailDTO, CarDetailDTO.class);

        return carDetailDTO;
    }


    @Override
    public List<CarListDTO> listCars() {

        List<CarDTO> carDTOList = queryCarsFromDb();
        if (CollectionUtils.isEmpty(carDTOList)) {
            return new ArrayList<>();
        }

        return carDTOList.stream().map(carDTO -> {
            ManufactureDTO manufactureDTO = manufactureService.findById(carDTO.getManufactureId());

            CarListDTO carListDTO = new CarListDTO();
            carListDTO.setCar(carDTO);
            carListDTO.setManufacture(ManufactureConverter.convert(manufactureDTO));

            return carListDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CarListDTO> listCarsV2() {
        List<CarDTO> carDTOList = queryCarsFromDb();
        if (CollectionUtils.isEmpty(carDTOList)) {
            return new ArrayList<>();
        }

        return carDTOList.stream().map(carDTO -> {
            CarListDTO carListDTO = new CarListDTO();
            carListDTO.setCar(carDTO);
            joinInMemoryExecutor.fetch(carListDTO, CarListDTO.class);
            return carListDTO;
        }).collect(Collectors.toList());
    }


    private List<CarDTO> queryCarsFromDb() {
        CarDTO carDTO = new CarDTO();
        carDTO.setModel("model Y Car Detail");
        carDTO.setReservePrice(10000);
        carDTO.setId(1L);
        carDTO.setManufactureId(100L);

        CarDTO carDTO2 = new CarDTO();
        carDTO2.setModel("model X Car Detail");
        carDTO2.setReservePrice(5000);
        carDTO2.setId(2L);
        carDTO2.setManufactureId(101L);

        List<CarDTO> cars = new ArrayList<>();
        cars.add(carDTO);
        cars.add(carDTO2);

        return cars;
    }
}
