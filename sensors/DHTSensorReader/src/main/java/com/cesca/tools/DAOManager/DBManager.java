package com.cesca.tools.DAOManager;


import java.sql.Connection;

public interface DBManager {
	public void open();
	public Connection getConnection();
	public void close();
}
