package com.cesca.sensors.dao;

import com.cesca.sensors.entity.DHTSensor;

public interface DHTSensorDAO {

	DHTSensor getLatestData();

	DHTSensor addDHTSensorData(DHTSensor dht);

}