package com.hngy.lms.repository;

import com.hngy.lms.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClothingRepository extends JpaRepository<Clothing,Long> {

}
