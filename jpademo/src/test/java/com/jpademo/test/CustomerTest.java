package com.jpademo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpademo.model.dto.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		EntityManagerFactory fc = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = fc.createEntityManager();

		em.getTransaction().begin();

		Customer c = new Customer();
		c.setCustomerName("raja");
		c.setCustomerAddress("raja.raja.com");

		em.persist(c);
		System.out.println("inserted...");
		em.getTransaction().commit();
		em.close();
		fc.close();

	}

}
