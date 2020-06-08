package com.capgemini.librarymanagementsystemjdbc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;

public class JdbcUtility{

	private static Connection connection = null;

	public static Connection getConnection() throws LMSException {
		File file = null;
		FileInputStream inputStream = null;

		file = new File("database.properties");
		try {
			inputStream = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(inputStream);
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String userName = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, password);
		} catch (FileNotFoundException e) {
			throw new LMSException("File not exists");
		} catch (IOException e) {
			throw new LMSException("Unable to read data from the file");
		} catch (ClassNotFoundException e) {
			throw new LMSException("class not loaded");
		} catch (SQLException e) {
			throw new LMSException("connection issue");
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new LMSException("unable to close the file");
			}
		}
		return connection;
	} //end of getConnection() method
} //end of class
