package com.bankapp.model.service.impl;

import org.springframework.stereotype.Service;

import com.bankapp.model.service.EmailService;

@Service
public class EmailServiceImp implements EmailService {

	@Override
	public void sendEmail(String fromAccontMail, String toAccountMail) {
		System.out.println("emil is send...");
	}

}
