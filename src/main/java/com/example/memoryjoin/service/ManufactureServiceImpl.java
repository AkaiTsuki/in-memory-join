package com.example.memoryjoin.service;

import com.example.memoryjoin.model.payer.ManufactureDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ManufactureDTO> findByIds(List<Long> ids) {
        List<ManufactureDTO> result = new ArrayList<>();

        if(CollectionUtils.isEmpty(ids)) {
            return result;
        }

        return ids.stream().map(id -> {
            ManufactureDTO dto = new ManufactureDTO();
            dto.setId(id);
            dto.setFactoryInfo("factory " + id);
            dto.setName("manufacture " + id);
            return dto;
        }).collect(Collectors.toList());
    }
}
