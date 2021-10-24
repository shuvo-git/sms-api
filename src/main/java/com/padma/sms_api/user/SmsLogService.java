package com.padma.sms_api.user;

import com.padma.sms_api.user.SmsLog;
import com.padma.sms_api.user.SmsLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsLogService
{
    final SmsLogRepository logRepository;

    public SmsLog saveLog(SmsLog smsLog){
        try {
            log.info("Saving SMS log: "+smsLog.toString());
            return logRepository.save(smsLog);
        }
        catch (Exception e){
            log.error(e.getMessage().toString());
            return null;
        }

    }
}
