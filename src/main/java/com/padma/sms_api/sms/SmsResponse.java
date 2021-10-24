package com.padma.sms_api.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SmsResponse
{
    public static final int NULL_FIELD = 0;
    public static final String M_NULL_FIELD = "NULL_FIELD";

    public static final int SENT = 1;
    public static final String M_SENT = "SENT";

    public static final int NOT_SENT = 2;
    public static final String M_NOT_SENT = "NOT_SENT";

    public static final int UNAUTHORIZED = 3;
    public static final String M_UNAUTHORIZED = "UNAUTHORIZED";

    private String message;
    private int responseFlag;
    private String flagDetails;
}
