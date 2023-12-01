package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.ManufactureDTO;
import org.springframework.stereotype.Service;

@Service("manufactureService")
public class ManufactureServiceImpl implements ManufactureService {
    @Override
    public ManufactureDTO findById(Long id) {
        ManufactureDTO dto = new ManufactureDTO();
        dto.setId(id);
        dto.setFactoryInfo("factory " + id);
        dto.setName("manufacture " + id);
        return dto;
    }
}
