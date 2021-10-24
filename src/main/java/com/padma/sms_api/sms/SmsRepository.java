package com.padma.sms_api.sms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<SmsRequest,Long>{
}
