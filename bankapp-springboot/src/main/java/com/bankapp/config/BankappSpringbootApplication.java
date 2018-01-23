package com.bankapp.config;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.bankapp")
@EnableJpaRepositories("com.bankapp.model.dao")
@EntityScan("com.bankapp.model.dto")
public class BankappSpringbootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BankappSpringbootApplication.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

	    String[] beanNames = ctx.getBeanDefinitionNames();
	    Arrays.sort(beanNames);
	    for (String beanName : beanNames) {
	        System.out.println(beanName);
	    }
	}
}
