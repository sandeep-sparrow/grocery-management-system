package net.sandeep.grocery.store.controller;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.LoginDto;
import net.sandeep.grocery.store.dto.RegisterDto;
import net.sandeep.grocery.store.service.AuthenticationService;
import net.sandeep.grocery.store.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sandeep R P
 * @version 1.0
 * @license sandeep-sparrow, GITHUB
 * @since 01/01/0001 (MM/DD/YYYY)
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  RegisterDto registerDto){
        String response = authenticationService.register(registerDto);
        if(response.equals("User Registered Successfully!")){
            emailService.sendSimpleMail(registerDto.getUsername(), registerDto.getEmail());
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authenticationService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
