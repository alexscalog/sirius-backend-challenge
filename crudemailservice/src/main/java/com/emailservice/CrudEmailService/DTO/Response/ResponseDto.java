package com.emailservice.CrudEmailService.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

   private boolean isSuccess;
   private String message;

}
