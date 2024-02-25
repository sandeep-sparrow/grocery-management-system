package net.sandeep.grocery.store.service.impl;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.LoginDto;
import net.sandeep.grocery.store.dto.RegisterDto;
import net.sandeep.grocery.store.exception.GroceryAPIException;
import net.sandeep.grocery.store.model.Role;
import net.sandeep.grocery.store.model.User;
import net.sandeep.grocery.store.repository.RoleRepository;
import net.sandeep.grocery.store.repository.UserRepository;
import net.sandeep.grocery.store.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        // username already exists? Check
        if(userRepository.existsByUserName(registerDto.getUsername())){
            throw new GroceryAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // user with email already exists? Check
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new GroceryAPIException(HttpStatus.BAD_REQUEST, "User Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // assign a role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        if(!authentication.isAuthenticated()) {
            throw new GroceryAPIException(HttpStatus.BAD_REQUEST, "User Credentials invalid!");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged in successfully!";
    }
}
