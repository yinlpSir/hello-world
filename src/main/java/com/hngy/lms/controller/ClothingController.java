package com.hngy.lms.controller;

import com.hngy.lms.dto.AddClothingDto;
import com.hngy.lms.exception.LmsException;
import com.hngy.lms.service.ClothingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clothing")
@Slf4j
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid AddClothingDto addClothingDto) throws Exception {
        log.info("添加衣物");
        if(!clothingService.add(addClothingDto)) throw new LmsException("添加失败");
        return new ResponseEntity("添加成功!",HttpStatus.OK);
    }

    @PostMapping("/query")
    public ResponseEntity get(){

        return null;
    }
}
