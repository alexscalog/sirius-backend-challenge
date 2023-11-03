package com.emailservice.CrudEmailService.DTO.Request;

import lombok.Data;

@Data
public class SendEmailDto {
    private String receiver;
    private String body;
    private String subject;
    private String senderUsername;
}
