package com.hngy.lms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AddClothingDto {

    @NotBlank(message = "name.not.null")
    private String name;

    @NotBlank(message = "衣服描述不能为empty")
    private String description;

    @NotBlank(message = "尺寸不能为empty")
    private String size;

    @NotBlank(message = "洗衣方式不能为empty")
    private String washMethod;//洗衣方式

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns = {"yyyy-MM-dd HH:mm"})
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss")//,timezone ="GMT+8"
    private LocalDateTime pickupTime;//取衣时间
}
