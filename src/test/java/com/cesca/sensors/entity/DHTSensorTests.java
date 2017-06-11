package com.cesca.sensors.entity;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DHTSensorTests {

	@Test
	public void should_set_and_get_collectionTimestamp() {
		DHTSensor dht = new DHTSensor();
		long currentTimestamp = System.currentTimeMillis();
		dht.setCollectionTimestamp(currentTimestamp);
		assertEquals(currentTimestamp, dht.getCollectionTimestamp());
	}
	
	@Test
	public void should_set_and_get_humidity() {
		DHTSensor dht = new DHTSensor();
		dht.setHumidity(72.8f);
		assertEquals(72.8f, dht.getHumidity(), 0.1f);		
	}

	@Test
	public void should_set_and_get_temperature() {
		DHTSensor dht = new DHTSensor();
		dht.setTemperature(18.3f);
		assertEquals(18.3f, dht.getTemperature(), 0.1f);		
	}
}
