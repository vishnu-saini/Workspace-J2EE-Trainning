package com.bankapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bankapp.model.dto.Transaction;
import com.bankapp.model.service.TransactionService;

@Controller
@RequestMapping({"/user/transaction"})
public class UserTransactionController {

	@Autowired
	TransactionService transactionService;

	
	@RequestMapping(value = "showTransactions", method = RequestMethod.GET)
	public String showTransactions(ModelMap map) {
		List<Transaction> transactions=transactionService.getAllTransactions();
		
		map.addAttribute("transactions",transactions);
		System.out.println(transactions);
		return "ShowTransactions";
	}


}
