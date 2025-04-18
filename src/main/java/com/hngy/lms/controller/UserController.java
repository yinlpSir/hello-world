package com.hngy.lms.controller;

import com.hngy.lms.dto.LoginDto;
import com.hngy.lms.dto.RegisterDto;
import com.hngy.lms.entity.ResponseResult;
import com.hngy.lms.entity.Role;
import com.hngy.lms.entity.User;
import com.hngy.lms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) throws Exception{
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).
                body(userService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) throws Exception {
        User user=userService.register(registerDto);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseResult<User>("注册成功!",user));
    }

    @PostMapping("/getAllUser")
    public ResponseEntity getAllUser() throws Exception {
        User user=new User();
        user.setId(1);
        user.setUsername("sdf");
        user.setRole(Role.USER);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
              .body(user);
    }
}
