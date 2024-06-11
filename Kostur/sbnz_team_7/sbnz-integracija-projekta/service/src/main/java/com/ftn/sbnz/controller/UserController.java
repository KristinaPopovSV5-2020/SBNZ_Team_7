package com.ftn.sbnz.controller;
import com.ftn.sbnz.dto.product.ProductFeedbackDTO;
import com.ftn.sbnz.dto.DiscountDTO;
import com.ftn.sbnz.dto.user.UserDTO;
import com.ftn.sbnz.dto.user.UserReportDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.DiscountService;
import com.ftn.sbnz.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DiscountService discountService;

    @PostMapping(consumes = "application/json", value = "/api/user/register")
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDTO userDTO) throws IOException {
        if (userService.saveUser(userDTO) == null) {
            throw new BadRequestException("User with username already exists!");
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/allergens")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<String>> getUserAllergens() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        List<String> allergens = userService.getUserAllergens(user);
        return new ResponseEntity<>(allergens, HttpStatus.OK);
    }


    @GetMapping(value = "/api/user/skinType")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getUserSkinType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        String skinType = userService.getUserSkinType(user);
        return new ResponseEntity<>(skinType, HttpStatus.OK);
    }


    @GetMapping(value = "api/users/report")
    public ResponseEntity<List<UserReportDTO>> getAllProducts() {
        List<UserReportDTO> userReportDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userReportDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/discounts")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DiscountDTO>> getUserDiscounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        List<DiscountDTO> discountDTOS = discountService.getUserDiscount(user);
        return new ResponseEntity<>(discountDTOS, HttpStatus.OK);

    }


    @GetMapping(value = "/api/user/discounts/unused")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DiscountDTO>> getUnusedUserDiscounts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        List<DiscountDTO> discountDTOS = discountService.getUnusedUserDiscount(user);
        return new ResponseEntity<>(discountDTOS, HttpStatus.OK);

    }


}
