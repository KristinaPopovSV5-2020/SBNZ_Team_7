package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.user.UserDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping(consumes = "application/json", value = "/api/user/register")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDTO userDTO) throws IOException {
        if (userService.saveUser(userDTO) == null) {
            throw new BadRequestException("User with email already exists!");
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}
