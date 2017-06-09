package com.cesca.arduino.DHTSensor;



import java.util.List;

public interface DHTSensorDAO {
	
	public List<DHTSensor> getAll();
	public boolean insert(DHTSensor dht);
	public DHTSensor getCurrent();

}
