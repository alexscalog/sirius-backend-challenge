package com.emailservice.CrudEmailService.Services.Email;

import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Request.SendEmailDto;
import com.emailservice.CrudEmailService.Models.Email;
import com.emailservice.CrudEmailService.Repository.EmailRepository;
import com.emailservice.CrudEmailService.Services.Email.Sender.SlurpEmailSender;
import com.emailservice.CrudEmailService.Services.Email.Sender.TestEmailSender;
import com.emailservice.CrudEmailService.Services.User.UserService;
import com.mailslurp.clients.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    private final UserService userService;
    private final SlurpEmailSender slurpEmailSender;
    private final TestEmailSender testEmailSender;
    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(UserService userService, SlurpEmailSender slurpEmailSender, TestEmailSender testEmailSender, EmailRepository emailRepository) {
        this.userService = userService;

        this.slurpEmailSender = slurpEmailSender;
        this.testEmailSender = testEmailSender;
        this.emailRepository = emailRepository;
    }

    @Override
    public ResponseDto sendEmail(SendEmailDto sendEmailDto) throws ApiException {
        var sender = userService.findByUsername(sendEmailDto.getSenderUsername());
        if (sender.isEmpty())
            return new ResponseDto(false, "Invalid sender");

        var emailEntity = new Email();
        emailEntity.setUser(sender.get());
        emailEntity.setSendingDate(new Date());

        var response = slurpEmailSender.sendEmail(sendEmailDto);

        if (response.isSuccess()) {
            emailRepository.save(emailEntity);
            return response;
        }

        response = testEmailSender.sendEmail(sendEmailDto);

        if (response.isSuccess()) {
            emailRepository.save(emailEntity);
        }

        return response;
    }

}
