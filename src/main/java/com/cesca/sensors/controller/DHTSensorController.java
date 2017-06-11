package com.cesca.sensors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cesca.sensors.entity.DHTSensor;
import com.cesca.sensors.service.DHTSensorService;

@RestController
@RequestMapping("/REST/DHTSensor")
public class DHTSensorController {
	
	@Autowired
	public DHTSensorService dhtSensorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public DHTSensor getLatestData(){
		return this.dhtSensorService.getLatestData();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DHTSensor addData(@RequestBody DHTSensor dht){
		return this.dhtSensorService.addDHTSensorData(dht);
	}

}
