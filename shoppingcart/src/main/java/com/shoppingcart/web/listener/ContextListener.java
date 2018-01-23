package com.shoppingcart.web.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.log4j.PropertyConfigurator;

import com.shoppingcart.model.utility.ConnectionFactory;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		/* Configuring logging */
		String log4jConfigFile = context.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
		PropertyConfigurator.configure(fullPath);

		/* Configuring Database Connections */
		/*
		 * try { Properties prop = new Properties();
		 * 
		 * InputStream inputStream =
		 * ConnectionFactory.class.getClassLoader().getResourceAsStream(
		 * "db.properties"); prop.load(inputStream); String driver =
		 * prop.getProperty("driver"); String url = prop.getProperty("url");
		 * String user = prop.getProperty("user"); String password =
		 * prop.getProperty("password"); Class.forName(driver);
		 * ConnectionFactory.setMysqlConnection(DriverManager.getConnection(url,
		 * user, password)); } catch (ClassNotFoundException e) {
		 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace();
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		/* Configuring JNDI datasource */

		try {

			/*Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/ecom");
			ConnectionFactory.setJndiDataSource(ds);*/
			
			  InitialContext ctx = new InitialContext(); DataSource ds =
			  (DataSource)ctx.lookup("jdbc/ecom");
			  ConnectionFactory.setJndiDataSource(ds);
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
