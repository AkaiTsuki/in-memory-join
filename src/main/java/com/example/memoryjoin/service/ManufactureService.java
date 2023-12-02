package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.ManufactureDTO;

import java.util.List;

public interface ManufactureService {
    public ManufactureDTO findById(Long id);

    public List<ManufactureDTO> findByIds(List<Long> ids);
}
