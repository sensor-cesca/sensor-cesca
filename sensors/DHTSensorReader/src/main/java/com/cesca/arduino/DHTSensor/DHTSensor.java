package com.cesca.arduino.DHTSensor;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DHTSensor {

	private Date collectionTimestamp;
	private double humidity;
	private double temperature;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public DHTSensor(Date collectionTimestamp, double humidity, double temperature){
		this.setCollectionTimestamp(collectionTimestamp);
		this.setHumidity(humidity);
		this.setTemperature(temperature);		
		log.debug("DHTSensor:");
		log.debug(toString());
	}
	
	public DHTSensor(double humidity, double temperature){
		this(Calendar.getInstance().getTime(), humidity, temperature);
	}
	
	public DHTSensor(){
		
	}
	
	public void setCollectionTimestamp(Date collectionTimestamp){
		this.collectionTimestamp = collectionTimestamp;
	}
	
	public void setHumidity(double humidity){
		this.humidity = humidity;
	}
	
	public void setTemperature(double temperature){
		this.temperature = temperature;
	}
	
	public Date getCollectionTimestamp(){
		return this.collectionTimestamp;
	}
	
	public String getFormattedCollectionTimestamp(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.collectionTimestamp);
	}
	
	public double getHumidity(){
		return this.humidity;
	}
	
	public String getFormattedHumidity(){
		return String.format("%.2f", this.humidity) + "%";
	}
	
	public double getTemperature(){
		return this.temperature;
	}
	
	public String getFormattedTemperature(){
		return String.format("%.2f", this.temperature) + " C\u00b0";
	}
	
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append("Collected Timestamp: " + this.getFormattedCollectionTimestamp() + "\n");
		st.append("Humidity: " + this.getFormattedHumidity() + "\n");
		st.append("Temperature: " + this.getFormattedTemperature() + "\n");
		return st.toString();
	}
}
