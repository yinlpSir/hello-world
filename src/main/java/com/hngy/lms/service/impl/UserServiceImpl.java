package com.hngy.lms.service.impl;

import com.hngy.lms.dto.LoginDto;
import com.hngy.lms.dto.RegisterDto;
import com.hngy.lms.entity.ResponseResult;
import com.hngy.lms.entity.Role;
import com.hngy.lms.entity.User;
import com.hngy.lms.exception.LmsException;
import com.hngy.lms.repository.UserRepository;
import com.hngy.lms.service.UserService;
import com.hngy.lms.utils.JWTUtil;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public ResponseResult login(LoginDto loginDto) throws Exception{
        Optional<User> user= userRepository.findByUsername(loginDto.getUsername());
        User realUser=user.get();
        if(!passwordEncoder.matches(loginDto.getPassword(),realUser.getPassword())) throw new BadCredentialsException("用户名或密码不正确");
        String token = JWTUtil.generateToken(realUser);

        Map<String,String> data=new HashMap<>();
        data.put("username",realUser.getUsername());
        data.put("role",realUser.getAuthorities().toString());
        data.put("token",token);
        return new ResponseResult<>("登录成功!",data);
    }

    @Override
    public User register(RegisterDto registerDto) throws Exception {
        User user=new User();
        BeanUtils.copyProperties(registerDto,user);//将一个Java Bean对象的属性值复制到另一个Java Bean对象中,注意:属性名要一致
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        //save 的返回值为 插入成功的对象
        return userRepository.save(user);
    }

}
