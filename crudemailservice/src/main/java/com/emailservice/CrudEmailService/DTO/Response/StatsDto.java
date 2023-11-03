package com.emailservice.CrudEmailService.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class StatsDto implements Serializable {
    private String username;
    private long sentEmails;
}
