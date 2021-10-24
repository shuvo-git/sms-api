package com.padma.sms_api.controllers;

import com.padma.sms_api.sms.*;
import com.padma.sms_api.user.SmsLog;
import com.padma.sms_api.user.SmsLogService;
import com.padma.sms_api.user.User;
import com.padma.sms_api.user.UserRepo;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsApiController
{
    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SmsLogService smsLogService;

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @PostMapping("/sendOtp")
    public ResponseEntity<SmsResponse> sendOtp(@RequestBody SmsRequestBody smsRequest)
    {
        User user = null;
        try {
            user = userRepo.findByUsername(smsRequest.getUsername());
        }catch (NullValueInNestedPathException e){
            logSmsApi(
                    smsRequest.getPhoneNumber(),
                    smsRequest.getMsgBody(),
                    smsRequest.getUsername(),
                    SmsResponse.M_NULL_FIELD
            );
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new SmsResponse("Null Field Value given.",
                            SmsResponse.NULL_FIELD,
                            SmsResponse.M_NULL_FIELD
                    )
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(user != null && user.getPassword().equals(smsRequest.getPassword())){
            try {

                    smsRepository.save(new SmsRequest(
                            smsRequest.getPhoneNumber(),
                            smsRequest.getMsgBody(),
                            0,
                            user.getUsername()
                    ));
                    smsRepository.flush();

                    logSmsApi(
                            smsRequest.getPhoneNumber(),
                            smsRequest.getMsgBody(),
                            smsRequest.getUsername(),
                            SmsResponse.M_SENT
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new SmsResponse("SMS has been sent successfully",
                                    SmsResponse.SENT,
                                    SmsResponse.M_SENT
                            )
                    );


            } catch (Exception e) {
                logSmsApi(
                        smsRequest.getPhoneNumber(),
                        smsRequest.getMsgBody(),
                        smsRequest.getUsername(),
                        SmsResponse.M_NOT_SENT
                );
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                        new SmsResponse("SMS did not Sent due to some error",
                                SmsResponse.NOT_SENT,
                                SmsResponse.M_NOT_SENT
                        )
                );
            }

        } else {
            logSmsApi(
                    smsRequest.getPhoneNumber(),
                    smsRequest.getMsgBody(),
                    smsRequest.getUsername(),
                    SmsResponse.M_UNAUTHORIZED
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new SmsResponse("Credentials Did no Match",
                            SmsResponse.UNAUTHORIZED,
                            SmsResponse.M_UNAUTHORIZED
                    )
            );
        }

    }


    private void logSmsApi(String phone,String msg,String createdBy,String status)
    {
        smsLogService.saveLog(new SmsLog(
                null,
                phone,
                msg,
                createdBy,
                status
        ));
    }
}
