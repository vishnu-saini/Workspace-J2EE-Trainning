package com.bankingsystem.model.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankingsystem.model.service.EmailService;

@Service
@Transactional
public class EmailServiceImp implements EmailService {

	@Override
	public void sendEmail(String fromAccontMail, String toAccountMail) {
		System.out.println("emil is send...");
	}

}
