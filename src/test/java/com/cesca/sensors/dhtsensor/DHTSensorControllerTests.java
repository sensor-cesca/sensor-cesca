package com.cesca.sensors.dhtsensor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cesca.sensors.controller.DHTSensorController;
import com.cesca.sensors.entity.DHTSensor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DHTSensorControllerTests {

	@Autowired
	public DHTSensorController dhtSensorController;

	@Before
	public void Mock_DHTSensorDAO() {
		// Create Data
		Collection<DHTSensor> dhtList = new ArrayList<DHTSensor>();
		dhtList.add(new DHTSensor(1497013934978l, 68.2f, 22.8f));
		dhtList.add(new DHTSensor(1497013874930l, 67.9f, 22.8f));
		dhtList.add(new DHTSensor(1497013814880l, 68.9f, 22.9f));
		dhtList.add(new DHTSensor(1497013754831l, 67.1f, 22.8f));
		dhtList.add(new DHTSensor(1497013694782l, 68.2f, 22.8f));
		dhtList.add(new DHTSensor(1497013634734l, 68.4f, 22.9f));
		dhtList.add(new DHTSensor(1497013574685l, 67.6f, 22.9f));
		dhtList.add(new DHTSensor(1497013514632l, 67.6f, 22.9f));
		dhtList.add(new DHTSensor(1497013454582l, 69.3f, 22.9f));
		dhtList.add(new DHTSensor(1497013394534l, 70.3f, 23f));

		Mock_DHTSensorDAO daoMock = new Mock_DHTSensorDAO();
		daoMock.dhtSensorList = dhtList;
		this.dhtSensorController.dhtSensorService.dhtSensorDAO = daoMock;
	}

	@Test
	public void contextLoads() {
		DHTSensor dht = this.dhtSensorController.getLatestData();
		assertEquals(1497013934978l, dht.getCollectionTimestamp());
		assertEquals(68.2f, dht.getHumidity(), 0.1);
		assertEquals(22.8f, dht.getTemperature(), 0.1);
	}

}
