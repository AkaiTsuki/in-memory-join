package com.example.memoryjoin.service;

import com.example.memoryjoin.model.car.TagDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Override
    public List<TagDTO> findCarTags(Long carId) {
        long now = System.currentTimeMillis();

        List<TagDTO> list = new ArrayList<>();
        TagDTO t1 = new TagDTO();
        t1.setTagId("1-"+carId);
        t1.setTagDesc("tag 1 " + now);
        t1.setCarId(carId);

        TagDTO t2 = new TagDTO();
        t2.setTagId("2-"+carId);
        t2.setTagDesc("tag 2 " + now);
        t2.setCarId(carId);

        TagDTO t3 = new TagDTO();
        t3.setTagId("3-"+carId);
        t3.setTagDesc("tag 3 " + now);
        t3.setCarId(carId);

        list.add(t1);
        list.add(t2);
        list.add(t3);

        return list;
    }

    @Override
    public List<TagDTO> findCarsTags(List<Long> carIds) {
        List<TagDTO> list = new ArrayList<>();
        for(Long carId: carIds) {
            list.addAll(findCarTags(carId));
        }

        return list;
    }
}
