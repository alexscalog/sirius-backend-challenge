package com.emailservice.CrudEmailService.Controllers;


import com.emailservice.CrudEmailService.DTO.Request.LoginDto;
import com.emailservice.CrudEmailService.DTO.Response.JwtAuthenticationResponse;
import com.emailservice.CrudEmailService.Services.Authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody LoginDto request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginDto request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}