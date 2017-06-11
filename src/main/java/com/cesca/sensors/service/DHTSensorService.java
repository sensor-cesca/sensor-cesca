package com.cesca.sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesca.sensors.dao.DHTSensorDAO;
import com.cesca.sensors.entity.DHTSensor;

@Service
public class DHTSensorService {
	
	@Autowired
	public DHTSensorDAO dhtSensorDAO;
	
	public DHTSensor getLatestData() {
		return this.dhtSensorDAO.getLatestData();
	}
	
	public DHTSensor addDHTSensorData(DHTSensor dht){
		return this.dhtSensorDAO.addDHTSensorData(dht);
	}

}
