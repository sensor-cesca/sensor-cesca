package com.cesca.tools.DAOManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOManager {

	// Private
	private Connection con;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/arduino";
	static final String DB_USER_NAME = "postgres";
	static final String DB_USER_PASSWORD = "pgpass";

	public DAOManager() {
	}

	public Connection open() {
		Properties props = new Properties();
		props.setProperty("user", DAOManager.DB_USER_NAME);
		props.setProperty("password", DAOManager.DB_USER_PASSWORD);
		props.setProperty("ssl", "true");
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection(DAOManager.DB_URL, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return this.con;
	}

	public void close() throws SQLException {
		this.con.close();
	}

}


