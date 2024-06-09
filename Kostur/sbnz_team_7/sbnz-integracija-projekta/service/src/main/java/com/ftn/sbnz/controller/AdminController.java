package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.user.AdminDTO;
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
public class AdminController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(consumes = "application/json", value = "/api/admin/register")
    public ResponseEntity<Boolean> saveAdmin(@RequestBody AdminDTO userDTO) throws IOException {
        if (userService.saveAdmin(userDTO) == null) {
            throw new BadRequestException("Error!");
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    
}
