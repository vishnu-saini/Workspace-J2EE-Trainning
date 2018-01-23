package com.bankingsystem.testcontroller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bankingsystem.model.service.BankFacade;

public class Tester {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		/*
		 * AccountService as=ctx.getBean("accountServiceImp",
		 * AccountService.class); as.transfer(1, 2, 10);
		 */

		BankFacade bankFacade = ctx.getBean("bankFacadeImp", BankFacade.class);


		bankFacade.transfer(1, 2, 50,"127.0.0.1");
		 

	}

}
