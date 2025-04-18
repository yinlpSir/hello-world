package com.hngy.lms.entity.abstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public abstract class IdentityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//设置主键生成策略。IDENTITY表示数据库自动生成
    private long id;

}
