package com.emailservice.CrudEmailService.Services.Email.Sender;


import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Request.SendEmailDto;
import com.mailslurp.clients.ApiException;


public interface EmailSender {
    ResponseDto sendEmail(SendEmailDto sendEmailDto) throws ApiException;

}




