package com.ftn.sbnz.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ftn.sbnz.dto.user.LoginDTO;
import com.ftn.sbnz.dto.user.LoginTokenDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.user.Admin;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.util.TokenUtils;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping(value = "/api/login")
    public ResponseEntity<LoginTokenDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        String jwt;
        try {
            User user = (User) authentication.getPrincipal();
            jwt = tokenUtils.generateToken(user.getId(),user.getUsername(), user.getRoles());
        } catch (ClassCastException exception) {
            Admin user = (Admin) authentication.getPrincipal();
            jwt = tokenUtils.generateToken(user.getId(),user.getUsername(), user.getRoles());
        }
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new LoginTokenDTO(jwt, ""));
    }

    @GetMapping(
            value = "api/logout",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> logoutUser() {

        System.out.println("logout");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();

            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new BadRequestException("User is not authenticated!");
        }

    }
}
