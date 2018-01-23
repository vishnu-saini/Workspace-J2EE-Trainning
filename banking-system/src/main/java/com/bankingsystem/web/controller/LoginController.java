package com.bankingsystem.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankingsystem.model.dto.Transaction;
import com.bankingsystem.model.service.TransactionRecordService;

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
