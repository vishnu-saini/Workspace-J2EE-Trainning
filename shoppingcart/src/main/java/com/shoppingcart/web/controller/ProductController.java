package com.shoppingcart.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.shoppingcart.global.constants.JSPPath;
import com.shoppingcart.global.constants.Messages;
import com.shoppingcart.model.dao.exception.DAOException;
import com.shoppingcart.model.dao.exception.ProductNotFoundException;
import com.shoppingcart.model.dto.Product;
import com.shoppingcart.model.service.ProductService;
import com.shoppingcart.model.serviceimpl.ProductServiceImpl;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	Logger logger = Logger.getLogger(ProductController.class);
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if (action == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_VIEW);
				List<Product> products = productService.getAllProducts();
				logger.debug(products);
				request.setAttribute("products", products);
				dispatcher.forward(request, response);
			} else if (action.equals("delete")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_VIEW);
				boolean deleteResult = productService.deleteProduct(Integer.parseInt(request.getParameter("id")));
				List<Product> products = productService.getAllProducts();
				logger.debug(products);
				request.setAttribute("products", products);
				if (deleteResult) {
					request.setAttribute("successMessage", Messages.PRODUCT_DELETED);
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("errorMessage", Messages.PRODUCT_NOT_FOUND);
					dispatcher.forward(request, response);
				}

			} else if (action.equals("edit")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_FORM);

				Product product = productService.findProductById(Integer.parseInt(request.getParameter("id")));

				logger.debug(product);
				request.setAttribute("product", product);
				dispatcher.forward(request, response);
			} else if (action.equals("insert")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_FORM);
				dispatcher.forward(request, response);
			}
		} catch (DAOException daoException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
			request.setAttribute("errorMessage", Messages.DATABASE_ERROR);
			dispatcher.forward(request, response);
		} catch (ProductNotFoundException productNotFoundException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_VIEW);
			request.setAttribute("errorMessage", Messages.PRODUCT_NOT_FOUND);
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
		String price = request.getParameter("price");
		String category = request.getParameter("category");

		Product product = new Product();
		product.setName(name);
		product.setPrice(Double.parseDouble(price));
		product.setCategory(category);
		try {
			if (idString != null && !idString.equals("")) {
				int id = Integer.parseInt(idString);
				product.setId(id);
				product = productService.updateProduct(product);

			} else {
				product = productService.addProduct(product);
			}
			response.sendRedirect("product.do?successMessage=successful!!");
		} catch (DAOException daoException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ERROR_PAGE);
			request.setAttribute("errorMessage", Messages.DATABASE_ERROR);
			dispatcher.forward(request, response);
		} catch (ProductNotFoundException productNotFoundException) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.PRODUCT_VIEW);
			request.setAttribute("errorMessage", Messages.PRODUCT_NOT_FOUND);
			dispatcher.forward(request, response);
		}

		logger.debug(product);

		

	}

}
