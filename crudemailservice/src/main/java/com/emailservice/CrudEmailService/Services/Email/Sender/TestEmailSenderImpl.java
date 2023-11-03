package com.emailservice.CrudEmailService.Services.Email.Sender;

import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Request.SendEmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TestEmailSenderImpl implements TestEmailSender {

    @Value("${CrudEmailService.testmail.urlBase}")
    private String urlBase;


    @Override
    public ResponseDto sendEmail(SendEmailDto sendEmailDto) {
        return new ResponseDto(false, urlBase);
    }
}
