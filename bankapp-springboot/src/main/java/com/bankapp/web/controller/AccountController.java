package com.bankapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.dto.Account;
import com.bankapp.model.dto.AccountPOJO;
import com.bankapp.model.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountPOJO> getCustomer(@PathVariable Integer id) {
		Account account = accountService.getAccount(id);
		if (account == null) {
			return new ResponseEntity<AccountPOJO>(HttpStatus.NOT_FOUND);
		}
		AccountPOJO accountPOJO = new AccountPOJO(account.getId(), account.getCustomer().getName(),
				 account.getBranch().getName(), account.getBalance(), account.getType());

		return new ResponseEntity<AccountPOJO>(accountPOJO, HttpStatus.OK);
	}
}
