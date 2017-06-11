package com.cesca.sensors.entity;

public class DHTSensor {

	private long collectionTimestamp;
	private float humidity;
	private float temperature;

	public DHTSensor(){

	};
	
	public DHTSensor(long collectionTimeStamp, float humidity, float temperature){
		this.collectionTimestamp = collectionTimeStamp;
		this.humidity = humidity;
		this.temperature = temperature;
	}
	
	public long getCollectionTimestamp() {
		return collectionTimestamp;
	}

	public void setCollectionTimestamp(long collectionTimestamp) {
		this.collectionTimestamp = collectionTimestamp;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

}
