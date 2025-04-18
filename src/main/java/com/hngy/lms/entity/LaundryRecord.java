package com.hngy.lms.entity;

import com.hngy.lms.entity.abstractEntity.IdentityEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import java.time.LocalDateTime;

@Data
@Entity
@Table
public class LaundryRecord extends IdentityEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime pickupTime;//取衣时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime washTime;//洗衣时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime deliveryTime;//送衣时间

    private String washMethod;//洗衣方式
}
