package com.example.memoryjoin.util;

import com.example.memoryjoin.model.car.TagDTO;
import com.example.memoryjoin.model.car.TagVO;

public class TagConverter {
    public static TagVO convert(TagDTO dto) {
        if(dto == null) {
            return null;
        }

        TagVO vo = new TagVO();
        vo.setTagId(dto.getTagId());
        vo.setTagDesc(dto.getTagDesc());
        vo.setCarId(dto.getCarId());

        return vo;
    }
}
