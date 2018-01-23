package com.customerapp.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.customerapp.global.constants.Messages;
import com.customerapp.global.constants.JSPPath;
import com.customerapp.persistence.dao.exception.UserNotFoundException;
import com.customerapp.persistence.dto.User;
import com.customerapp.persistence.service.UserService;
import com.customerapp.persistence.serviceimpl.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LogoutController.class);
	UserService userService=new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(JSPPath.LOGIN_JSP);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user = null;
		try {
			user = userService.findByUserName(username);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user != null && password.equals(user.getPassword())){
			HttpSession session=request.getSession(); 
			session.setAttribute("username",username); 
			response.sendRedirect("customer.do");
		}else{
			request.setAttribute("errormsg", Messages.CREDENTIAL_NOT_MATCHED);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(JSPPath.LOGIN_JSP);
			requestDispatcher.forward(request, response);
		}
	}

}
