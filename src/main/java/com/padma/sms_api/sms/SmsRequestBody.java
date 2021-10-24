package com.padma.sms_api.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SmsRequestBody
{
    @NotNull(message = "Username must not be empty")
    private String username;

    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "Phone Number must not be empty")
    @Size(min = 11, max = 11, message= "Phone Number must be 11 Digits")
    private String phoneNumber;

    @NotNull(message = "Message Body can not be empty")
    @Size(min = 4, max = 20, message= "Message Body must be four to twenty characters long")
    private String msgBody;
}
