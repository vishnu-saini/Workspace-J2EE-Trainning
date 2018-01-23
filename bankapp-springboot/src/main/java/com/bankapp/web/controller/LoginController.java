package com.bankapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankapp.model.service.TransactionRecordService;

@Controller
@RequestMapping({"/"})
public class LoginController {

	@Autowired
	TransactionRecordService transactionService;

	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showTransactions() {
		return "ShowTransactions";
	}


}
