package com.customerapp.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.customerapp.global.constants.Messages;
import com.customerapp.global.constants.JSPPath;
import com.customerapp.persistence.dao.exception.CustomerNotFoundException;
import com.customerapp.persistence.dao.exception.DAOException;
import com.customerapp.persistence.dto.Customer;
import com.customerapp.persistence.service.CustomerService;
import com.customerapp.persistence.serviceimpl.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerServiceImpl();
	Logger logger = Logger.getLogger(CustomerController.class);
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if (action == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_VIEW);
				List<Customer> customers = customerService.getAllCustomers();
				logger.debug(customers);
				request.setAttribute("customers", customers);
				dispatcher.forward(request, response);
			} else if (action.equals("delete")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_VIEW);
				boolean deleteResult = customerService.deleteCustomer(Integer.parseInt(request.getParameter("id")));
				List<Customer> customers = customerService.getAllCustomers();
				logger.debug(customers);
				request.setAttribute("customers", customers);
				if (deleteResult) {
					request.setAttribute("successMessage", Messages.CUSTOMER_DELETED);
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", Messages.CUSTOMER_NOT_FOUND);
					dispatcher.forward(request, response);
				}

			} else if (action.equals("edit")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_FORM);

				Customer customer = customerService.findCustomerById(Integer.parseInt(request.getParameter("id")));

				logger.debug(customer);
				request.setAttribute("customer", customer);
				dispatcher.forward(request, response);
			} else if (action.equals("insert")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_FORM);
				dispatcher.forward(request, response);
			}
		} catch (DAOException daoException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
			request.setAttribute("errorMessage", Messages.DATABASE_ERROR);
			dispatcher.forward(request, response);
		} catch (CustomerNotFoundException customerNotFoundException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_VIEW);
			request.setAttribute("errorMessage", Messages.CUSTOMER_NOT_FOUND);
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setPhone(phone);
		try {
			if (idString != null) {
				int id = Integer.parseInt(idString);
				customer.setId(id);
				customer = customerService.updateCustomer(customer);

			} else {
				customer = customerService.addCustomer(customer);
			}
		} catch (DAOException daoException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
			request.setAttribute("errorMessage", Messages.DATABASE_ERROR);
			dispatcher.forward(request, response);
		} catch (CustomerNotFoundException customerNotFoundException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.CUSTOMER_VIEW);
			request.setAttribute("errorMessage", Messages.CUSTOMER_NOT_FOUND);
			dispatcher.forward(request, response);
		}

		logger.debug(customer);

		response.sendRedirect("customer.do?successMessage=successful!!");

	}

}
