package com.cesca.sensors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cesca.sensors.service.DHTSensorService;

@RestController
@RequestMapping("/GUI")
public class GUIController {
	
	@Autowired
	public DHTSensorService dhtSensorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLatestData(){
		return this.dhtSensorService.lastestAsHTML();
	}
	

}
