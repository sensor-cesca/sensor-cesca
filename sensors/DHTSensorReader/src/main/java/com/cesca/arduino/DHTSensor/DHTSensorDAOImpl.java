package com.cesca.arduino.DHTSensor;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cesca.tools.DAOManager.*;

public class DHTSensorDAOImpl implements DHTSensorDAO {

	private DBManager dbManager = new DBManagerPostgreSQL();
	static Logger log = Logger.getLogger(DHTSensor.class.getName());

	public List<DHTSensor> getAll() {
		this.dbManager.open();
		Connection conn = this.dbManager.getConnection();
		String query = "SELECT collection_timestamp, humidity, temperature FROM dht_sensor";
		List<DHTSensor> dhts = new ArrayList<DHTSensor>();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			while (rst.next()) {
				DHTSensor currentDHT = new DHTSensor();
				currentDHT.setCollectionTimestamp(rst.getTimestamp("collection_timestamp"));
				currentDHT.setHumidity(rst.getDouble("humidity"));
				currentDHT.setTemperature(rst.getDouble("temperature"));
				dhts.add(currentDHT);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dbManager.close();
		return dhts;
	}

	public boolean insert(DHTSensor dht) {
		boolean rst = false;
		this.dbManager.open();
		Connection conn = this.dbManager.getConnection();
		String sql = "INSERT INTO dht_sensor (collection_timestamp, humidity, temperature) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, new java.sql.Timestamp(dht.getCollectionTimestamp().getTime()));
			stmt.setDouble(2, dht.getHumidity());
			stmt.setDouble(3, dht.getTemperature());
			rst = stmt.execute();
			log.debug("DHTSensor insert result: "+ rst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dbManager.close();
		return rst;
	}

	public DHTSensor getCurrent() {
		this.dbManager.open();
		Connection conn = this.dbManager.getConnection();
		DHTSensor rstDHT = new DHTSensor();
		String query = "SELECT collection_timestamp, humidity, temperature FROM dht_sensor ORDER BY collection_timestamp DESC LIMIT 1;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rst = stmt.executeQuery();
			rst.next();
			rstDHT.setCollectionTimestamp(rst.getTimestamp("collection_timestamp"));
			rstDHT.setHumidity(rst.getDouble("humidity"));
			rstDHT.setTemperature(rst.getDouble("temperature"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dbManager.close();
		return rstDHT;
	}

}
