package com.padma.sms_api;

import com.padma.sms_api.user.User;
import com.padma.sms_api.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsApiApplication.class, args);
	}

	/*@Bean
	CommandLineRunner run(UserRepo userRepo){
		return args->{
			userRepo.save(new User(null,"jobayed","1234"));
			userRepo.save(new User(null,"jobayer","1234"));
		};
	}*/

}
