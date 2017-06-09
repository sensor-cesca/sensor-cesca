package com.cesca.arduino.DHTSensor;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DHTSensorTest {

	@Test
	public void should_be_able_to_create_instance_with_all_parameters() {
		Date currentDate = Calendar.getInstance().getTime();
		DHTSensor dht = new DHTSensor(currentDate,80, 18);
		assertEquals(dht.getHumidity(), 80.0, 0.001);
		assertEquals(dht.getTemperature(), 18, 0.001);
		assertEquals(dht.getCollectionTimestamp(), currentDate);		
	}
	
	@Test
	public void should_be_able_to_create_instance_without_any_parameter() {
		DHTSensor dht = new DHTSensor();
		assertEquals(dht.getHumidity(), 0.0, 0.001);
		assertEquals(dht.getTemperature(), 0.0, 0.001);
		assertEquals(dht.getCollectionTimestamp(), null);
	}
	
	@Test
	public void should_be_able_to_create_instance_with_only_humidity_and_temperature() {
		DHTSensor dht = new DHTSensor(80, 18);
		assertEquals(dht.getHumidity(), 80.0, 0.001);
		assertEquals(dht.getTemperature(), 18, 0.001);
		assertNotEquals(dht.getCollectionTimestamp(), null);				
	}
	
	@Test
	public void should_be_able_to_set_and_get_all_parameters() {
		Date currentDate = Calendar.getInstance().getTime();
		DHTSensor dht = new DHTSensor();
		dht.setCollectionTimestamp(currentDate);
		dht.setHumidity(80.5);
		dht.setTemperature(18.5);
		assertEquals(dht.getCollectionTimestamp(), currentDate);
		assertEquals(dht.getHumidity(), 80.5, 0.001);
		assertEquals(dht.getTemperature(), 18.5, 0.001);
	}

	@Test
	public void should_be_able_to_retrieve_all_parameters_formatted() {
		Date currentDate = Calendar.getInstance().getTime();
		String formattedCurrentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate);
		DHTSensor dht = new DHTSensor(currentDate, 80.5, 18.5);
		assertEquals(dht.getFormattedCollectionTimestamp(), formattedCurrentDate);
		assertEquals(dht.getFormattedHumidity(), "80.50%");
		assertEquals(dht.getFormattedTemperature(), "18.50 C\u00b0");
	}
	}
