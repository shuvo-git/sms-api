package com.padma.sms_api.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="push_sms_ib_otp")
public class SmsRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String msisdn;

    @Column
    private String sms_body;

    @Column
    private int status;

    @Column
    private String api_user;

    public SmsRequest(String msisdn,String sms_body,int status,String api_user){
        this.msisdn = msisdn;
        this.sms_body = sms_body;
        this.status = status;
        this.api_user = api_user;
    }
}
