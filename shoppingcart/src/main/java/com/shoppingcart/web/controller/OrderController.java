package com.shoppingcart.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shoppingcart.model.dto.Customer;
import com.shoppingcart.model.dto.Order;
import com.shoppingcart.model.dto.Product;
import com.shoppingcart.model.service.ProductService;
import com.shoppingcart.model.serviceimpl.ProductServiceImpl;


public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	
	@Resource(name="test/myJMSQueueFC")
	private ConnectionFactory connectionFactory;
	
	@Resource(name="test/myJMSQueue")
	private Destination destination;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String idString = request.getParameter("id");
		
	
		
		
		try
		{
			Product product = productService.findProductById(Integer.parseInt(request.getParameter("id")));
			Customer customer=new Customer(1, "Vishnu", "Jaipur", "123456");
			List<Product> products=new ArrayList<>();
			products.add(product);
			Order order=new Order(12, customer, products);
			
			
			Connection connection=connectionFactory.createConnection();
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer=session.createProducer(destination);
			ObjectMessage message=session.createObjectMessage(order);
			producer.send(message);
			
			
		
			
			
			
			
			System.out.println("Message send.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("product.do?successMessage=ordersuccessful!!");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
