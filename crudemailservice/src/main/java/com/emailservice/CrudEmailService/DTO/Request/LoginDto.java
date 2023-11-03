package com.emailservice.CrudEmailService.DTO.Request;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
