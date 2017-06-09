package com.cesca.tools.DAOManager;


import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.cesca.tools.PropertyManager.*;


public class DBManagerPostgreSQL implements DBManager{

	// DB Credentials
	private String DB_HOST = "localhost";
	private int DB_PORT = 5432;
	private String DB_USER = "postgres";
	private String DB_PASS = "pgpass";
	private String DB_DRIVER_CLASS = "org.postgresql.Driver";
	private String DB_NAME = "arduino";
	
	// DB Connection
	private Connection connection = null;
	
	private Logger log = Logger.getLogger(this.getClass());

	public DBManagerPostgreSQL(){
		loadDBProperties();
	}
	
	private void loadDBProperties(){
		PropertyManager prop = PropertyManager.getInstance();
		this.DB_HOST=prop.getProperty("db.host");
		this.DB_PORT=Integer.parseInt(prop.getProperty("db.port"));
		this.DB_USER=prop.getProperty("db.user");
		this.DB_PASS=prop.getProperty("db.password");
		this.DB_NAME=prop.getProperty("db.databasename");		
		log.debug("DB Properties loaded");
	}
	
	public void open(){
		try {
			Class.forName(DB_DRIVER_CLASS);
			this.connection = DriverManager.getConnection(
					   "jdbc:postgresql://"+this.DB_HOST+":"+this.DB_PORT+"/"+this.DB_NAME, this.DB_USER, this.DB_PASS);
			log.debug("DB connection opened");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){		
		return this.connection;
	}
	
	public void close(){
		try {
			this.connection.close();
			log.debug("DB Connection closed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
