package com.emailservice.CrudEmailService.Services.Authentication;

import com.emailservice.CrudEmailService.DTO.Request.LoginDto;
import com.emailservice.CrudEmailService.DTO.Response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(LoginDto request);

    JwtAuthenticationResponse signin(LoginDto request);
}