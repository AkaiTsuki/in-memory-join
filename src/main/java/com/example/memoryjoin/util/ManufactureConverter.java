package com.example.memoryjoin.util;

import com.example.memoryjoin.model.car.ManufactureVO;
import com.example.memoryjoin.model.payer.ManufactureDTO;

public class ManufactureConverter {
    public static ManufactureVO convert(ManufactureDTO dto) {
        if(dto == null) {
            return null;
        }

        ManufactureVO vo = new ManufactureVO();
        vo.setId(dto.getId());
        vo.setFactoryInfo(dto.getFactoryInfo());
        vo.setName(dto.getName());
        return vo;
    }
}
