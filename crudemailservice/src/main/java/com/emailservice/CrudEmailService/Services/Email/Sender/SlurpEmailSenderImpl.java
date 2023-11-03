package com.emailservice.CrudEmailService.Services.Email.Sender;

import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Request.SendEmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mailslurp.apis.*;
import com.mailslurp.clients.*;
import com.mailslurp.models.*;

import static java.util.Collections.singletonList;

@Service
public class SlurpEmailSenderImpl implements SlurpEmailSender {

    @Value("${CrudEmailService.mailslurp.apikey}")
    private String mailSlurpKey;


    @Override
    public ResponseDto sendEmail(SendEmailDto sendEmailDto) throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setApiKey(mailSlurpKey);

        InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);
        defaultClient.setConnectTimeout(10000);
        defaultClient.setWriteTimeout(10000);
        defaultClient.setReadTimeout(10000);

        InboxDto inbox = inboxControllerApi.createInboxWithDefaults();

        SendEmailOptions sendEmailOptions = new SendEmailOptions()
                .to(singletonList(inbox.getEmailAddress()))
                .subject(sendEmailDto.getSubject())
                .body(sendEmailDto.getBody());
        inboxControllerApi.sendEmail(inbox.getId(), sendEmailOptions);

        return new ResponseDto(true, "mensaje enviado con exito");
    }
}
