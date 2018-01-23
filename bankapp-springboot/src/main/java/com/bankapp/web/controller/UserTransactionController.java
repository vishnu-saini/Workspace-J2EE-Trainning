package com.bankapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bankapp.model.dto.TransactionRecord;
import com.bankapp.model.service.TransactionService;

@Controller
@RequestMapping({"/user/transaction"})
public class UserTransactionController {

	@Autowired
	TransactionService transactionService;

	
	/*@RequestMapping(value = "showTransactions", method = RequestMethod.GET)
	public ModelAndView showTransactions() {
		List<TransactionRecord> transactions=transactionService.getAllTransactions();
		ModelAndView modelAndView=new ModelAndView("ShowTransactions");
		modelAndView.addObject("transactions",transactions);
		return modelAndView;
	}*/

	
	@RequestMapping(value = "showTransactions", method = RequestMethod.GET)
	public String showTransactions(ModelMap map) {
		List<TransactionRecord> transactionRecords=transactionService.getAllTransactions();
		map.addAttribute("transactions",transactionRecords);
		return "ShowTransactions";
	}

}
