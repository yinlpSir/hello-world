package com.hngy.lms.service.impl;

import com.hngy.lms.dto.AddClothingDto;
import com.hngy.lms.entity.Clothing;
import com.hngy.lms.entity.LaundryRecord;
import com.hngy.lms.entity.Status;
import com.hngy.lms.entity.User;
import com.hngy.lms.repository.ClothingRepository;
import com.hngy.lms.service.ClothingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("clothingService")
public class ClothingServiceImpl implements ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;

    @Override
    public boolean add(AddClothingDto addClothingDto) throws Exception {
        Clothing clothing=new Clothing();
        BeanUtils.copyProperties(addClothingDto, clothing);
        clothing.setStatus(Status.未取);
        clothing.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LaundryRecord laundryRecord=new LaundryRecord();
        BeanUtils.copyProperties(addClothingDto, laundryRecord);
        clothing.setLaundryRecord(laundryRecord);

        Clothing clothed=clothingRepository.save(clothing);
        if(Objects.isNull(clothed)) return false;
        return true;
    }
}
