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
@RequestMapping({"/user/transaction"})
public class CustomerController {

	@Autowired
	TransactionRecordService transactionService;

	
	@RequestMapping(value = "showTransactions", method = RequestMethod.GET)
	public String showTransactions(ModelMap map) {
		List<Transaction> transactions=transactionService.getAllTransactions();
		
		map.addAttribute("transactions",transactions);
		System.out.println(transactions);
		return "ShowTransactions";
	}


}
