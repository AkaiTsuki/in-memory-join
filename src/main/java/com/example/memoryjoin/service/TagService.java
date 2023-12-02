package com.example.memoryjoin.service;

import com.example.memoryjoin.model.car.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> findCarTags(Long carId);

    List<TagDTO> findCarsTags(List<Long> carIds);
}
