package com.cesca.sensors.dhtsensor;

import java.util.Collection;

import com.cesca.sensors.dao.DHTSensorDAO;
import com.cesca.sensors.entity.DHTSensor;

public class Mock_DHTSensorDAO implements DHTSensorDAO {

	public Collection<DHTSensor> dhtSensorList;

	@Override
	public DHTSensor getLatestData() {
		long higher = 0;
		DHTSensor latestDHT = null;
		for (DHTSensor dht : this.dhtSensorList){
			if (dht.getCollectionTimestamp() > higher){
				higher = dht.getCollectionTimestamp();
				latestDHT = dht;
			}
		}
		return latestDHT;
	}

	@Override
	public DHTSensor addDHTSensorData(DHTSensor dht) {
		this.dhtSensorList.add(dht);
		return dht;
	}
}
