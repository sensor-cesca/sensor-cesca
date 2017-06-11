package com.cesca.sensors.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

}
