package com.hngy.lms;

import com.hngy.lms.entity.Role;
import com.hngy.lms.entity.User;
import com.hngy.lms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LaundryManagementSystemApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void myTest(){
//        User user=userRepository.findByUsername("zs");
//        System.out.println(user);
//        assertNotNull(user);
    }
}
