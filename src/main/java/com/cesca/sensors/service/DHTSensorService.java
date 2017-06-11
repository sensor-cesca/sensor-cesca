package com.cesca.sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesca.sensors.dao.DHTSensorDAO;
import com.cesca.sensors.entity.DHTSensor;

@Service
public class DHTSensorService {
	
	@Autowired
	public DHTSensorDAO dhtSensorDAO;
	
	public DHTSensor getLatestData() {
		return this.dhtSensorDAO.getLatestData();
	}
	
	public DHTSensor addDHTSensorData(DHTSensor dht){
		return this.dhtSensorDAO.addDHTSensorData(dht);
	}
	
	public String lastestAsHTML(){
		
		DHTSensor dht = this.getLatestData();
		
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("<head>");
		
		html.append("<meta http-equiv=\"refresh\" content=\"5\"/>");
		
		html.append("<title>Sensors</title>");
		
		
		html.append("</head>");
		
		html.append("<body style=\"color: #FFFFFF; background-color: #000000;\">");
		html.append("<div align=\"center\" style=\"font-size:96pt\">" + dht.getTemperature() + "&nbsp;&#8451</div>");
		html.append("<div align=\"center\" style=\"font-size:96pt\">" + dht.getHumidity() + "&nbsp;%</div>");
		
		
		
		html.append("</body></html>");
		return html.toString();
	}

}
