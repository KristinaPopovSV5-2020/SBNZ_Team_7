package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.shoppings.ShoppingUserDTO;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/shoppings")
public class ShoppingController {


    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }


    @PostMapping(value = "")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Shopping> buyProduct(@RequestParam String productId, @RequestParam(required = false) String discountId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }

        Shopping shopping = shoppingService.save(productId, user.getId(), discountId);
        return new ResponseEntity<>(shopping, HttpStatus.OK);
    }


    @DeleteMapping(value = "")
    public ResponseEntity<Boolean> deleteAll() {
        shoppingService.deleteAll();
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ShoppingUserDTO>> getPurchasesByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }
        List<ShoppingUserDTO> shoppingUserDTOS = shoppingService.findShoppingsByUser(user.getId());
        return new ResponseEntity<>(shoppingUserDTOS, HttpStatus.OK);

    }


}
