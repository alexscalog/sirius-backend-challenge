package com.emailservice.CrudEmailService.Controllers;

import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Request.SendEmailDto;
import com.emailservice.CrudEmailService.Services.Email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseDto sendEmail(@RequestBody SendEmailDto sendEmailDto) {
        try {
            var result = emailService.sendEmail(sendEmailDto);


            if (!result.isSuccess())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, result.getMessage());

            return result;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "no se pudo enviar el mail. " + e.getMessage());
        }
    }
}
