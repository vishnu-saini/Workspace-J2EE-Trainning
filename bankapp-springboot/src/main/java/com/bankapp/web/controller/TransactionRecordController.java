package com.bankapp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bankapp.model.dto.TransactionRecord;
import com.bankapp.model.service.BankFacade;
import com.bankapp.model.service.TransactionRecordService;
import com.bankingsystem.web.formbean.MoneyTransferForm;

@Controller
@RequestMapping({"/transaction"})
public class TransactionRecordController {

	@Autowired
	BankFacade bankFacade;
	
	@Autowired
	TransactionRecordService transactionService;
	
	@RequestMapping(value = "moneyTransfer", method = RequestMethod.GET)
	public String showMoneyTransferForm(ModelMap map) {
		MoneyTransferForm moneyTransferForm = new MoneyTransferForm();
		map.addAttribute("moneyTransferForm", moneyTransferForm);
		return "TransferForm";
	}

	@RequestMapping(value = "moneyTransfer", method = RequestMethod.POST)
	public String submitMoneyTransferFomr(MoneyTransferForm moneyTransferForm, HttpServletRequest request,RedirectAttributes redirectAttrs) {
		
		bankFacade.transfer(moneyTransferForm.getFromAccount(), moneyTransferForm.getToAccount(), moneyTransferForm.getAmount(),request.getRemoteAddr());
		redirectAttrs.addFlashAttribute("message", "Transfer from account "+moneyTransferForm.getFromAccount()+" to account "+ moneyTransferForm.getToAccount() +" of amount "+moneyTransferForm.getAmount()+" is completed");
		
		/*
		 * if (result.hasErrors()) { return "bookform"; } else{
		 * service.addBook(book);
		 */
		return "redirect:showTransactions";

	}
	
	
	@RequestMapping(value = "showTransactions", method = RequestMethod.GET)
	public String showTransactions(ModelMap map) {
		List<TransactionRecord> transactionRecords=transactionService.getAllTransactions();
		
		map.addAttribute("transactions",transactionRecords);
		System.out.println(transactionRecords);
		return "ShowTransactions";
	}


}
