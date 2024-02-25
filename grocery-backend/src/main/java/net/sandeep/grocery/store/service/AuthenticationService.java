package net.sandeep.grocery.store.service;

import net.sandeep.grocery.store.dto.LoginDto;
import net.sandeep.grocery.store.dto.RegisterDto;

public interface AuthenticationService {

    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
