package com.customerapp.persistence.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.customerapp.global.constants.DataSources;

public class ConnectionFactory {

	private static Connection mysqlConnection = null;
	private static DataSource jndiDataSource = null;

	public static synchronized void setMysqlConnection(Connection connection) {
		mysqlConnection = connection;
	}

	public static void setJndiDataSource(DataSource jndiDataSource) {
		ConnectionFactory.jndiDataSource = jndiDataSource;
	}

	public static Connection getConnection(DataSources dbname) throws SQLException {

		if (dbname.equals(DataSources.MYSQL)) {
			return mysqlConnection;
		} else if (dbname.equals(DataSources.JNDI)) {
			return jndiDataSource.getConnection();
		} else {

			System.out.println("DataSource not available");
			return null;

		}
	}

}