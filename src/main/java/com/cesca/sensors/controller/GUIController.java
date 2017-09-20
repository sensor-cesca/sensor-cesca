package com.cesca.sensors.controller;

import java.sql.Timestamp;

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
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>Sensors</title>");
		
		sb.append("<script>");
		sb.append("window.setInterval(\"reloadIFrame();\", 3000);");
		sb.append("function reloadIFrame() {");
		sb.append("window.frames[\"fr_content\"].location.reload();");
		sb.append("}");
		sb.append("</script>");

		sb.append("</head>");
		
		sb.append("<frameset rows = \"100%\">");
		sb.append("<frame name=\"fr_content\" src=\"/GUI/frame\">");
		sb.append("</frameset>");
		

		
		sb.append("</html>");
		
		return sb.toString();
	}
	
	@RequestMapping(value = "/frame",method = RequestMethod.GET)
	public String getFrame(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return this.dhtSensorService.lastestAsHTML();
	}
	
	

}
