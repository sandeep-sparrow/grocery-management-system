package net.sandeep.grocery.store.controller;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.LoginDto;
import net.sandeep.grocery.store.dto.RegisterDto;
import net.sandeep.grocery.store.service.AuthenticationService;
import net.sandeep.grocery.store.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private static final String REGISTRATION_EMAIL_SUBJECT = "Registration Successful! Welcome to Grocery Management System";
    private static final String REGISTRATION_EMAIL_BODY = " Hi User, This is a Grocery Management application developed " +
            "using Java for the backend and React for the frontend. " +
            "The application allows users to manage grocery items, categories, and shopping lists.";
    private static final String[] cc = {"sandeep.p4856@gmail.com"};

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  RegisterDto registerDto){
        String response = authenticationService.register(registerDto);
        if(response.equals("User Registered Successfully!")){
            emailService.sendMail(registerDto.getEmail(), cc,
                    REGISTRATION_EMAIL_SUBJECT, REGISTRATION_EMAIL_BODY);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authenticationService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
