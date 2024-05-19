package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.ShoppingService;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> buyProduct(@RequestParam String productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null){
            throw new NotFoundException("User does not exist!");
        }



        Shopping shopping = shoppingService.save(productId, user.getId());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
