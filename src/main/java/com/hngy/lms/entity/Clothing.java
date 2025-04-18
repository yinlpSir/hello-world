package com.hngy.lms.entity;

import com.hngy.lms.entity.abstractEntity.IdentityEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Proxy;

import java.math.BigDecimal;

@Data
@Entity
@Table
public class Clothing extends IdentityEntity {

    private String name;
    private String description;
    private String size;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault(value = "'未取'")//注意这里还嵌了个单引号，否则建表会出现错误
    private Status status;//衣物状态

//    @DecimalMin(value = "0.00",message = "金额不能小于等于0")
//    @DecimalMax(value= "9999",message = "金额不能大于9999")
    @Column(columnDefinition = "decimal(10,2) default 0.0")
    private BigDecimal price;

    //Clothing and User 为 多对一
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Clothing and LaundryRecord 一对一
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "laundry_record_id")
    private LaundryRecord laundryRecord;
}
