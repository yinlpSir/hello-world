package com.hngy.lms.entity;

import com.hngy.lms.entity.abstractEntity.IdentityEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table
public class Address extends IdentityEntity {
    @NotBlank
    private String district;//区
    @NotBlank
    private String addressDetail;
    @NotBlank
    private String zipCode;
}
