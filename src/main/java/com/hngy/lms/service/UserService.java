package com.hngy.lms.service;

import com.hngy.lms.dto.LoginDto;
import com.hngy.lms.dto.RegisterDto;
import com.hngy.lms.entity.ResponseResult;
import com.hngy.lms.entity.User;

/**
 * @Service("userService") 这个注解需要加在它的实现类上，否则会报
 * Field userService in com.adtec.vuespringboot.controller.UserController required a bean of type 'com.adtec.vuespringboot.service.impl.UserServiceImpl' that could not be found.
 * 因为 spring boot默认找的是service层的类，而service里面并没有UserServiceImpl这个类，这个类在 service.impl中
 */
public interface UserService {
    public ResponseResult login(LoginDto loginDto) throws Exception;
    public User register(RegisterDto registerDto) throws Exception;
}
