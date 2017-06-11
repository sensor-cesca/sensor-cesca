package com.cesca.sensors.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cesca.sensors.entity.DHTSensor;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@Controller
public class DHTSensorSerialMonitorService implements SerialPortEventListener {

	private SerialPort serialPort;
	private String output = "";

	private Logger log = Logger.getLogger(this.getClass());

	public DHTSensorSerialMonitorService() {

		this.serialPort = new SerialPort("COM5");
		try {
			serialPort.openPort();
			serialPort.setParams(9600, 8, 1, 0);// Set params.
			serialPort.addEventListener(this);// Add
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	public DHTSensorService dhtSensorService;

	@Override
	public void serialEvent(SerialPortEvent event) {
		log.debug("Event received");

		if (event.isRXCHAR()) {

			try {
				String serialOutput = this.serialPort.readString();
				serialOutput.replaceAll("null", "");
				this.output += serialOutput;

				String[] lines = output.split("\n\r");
				String lineToBeProcessed = null;

				for (String line : lines) {
					String[] values = line.split(" ");
					if ( (line.length() == 11) && (values.length == 2)) {
						if (values[0].length() == values[1].length()) {
							lineToBeProcessed = line;
						}
					}						
				}

				if (lineToBeProcessed != null) {
					this.processSerialLine(lineToBeProcessed);
				}

			} catch (SerialPortException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			log.debug("Event is not RXCHAR");
			log.debug("Event : " + event.toString());
		}
	}

	private void processSerialLine(String lineToBeProcessed) {
		log.info("Processing: " + lineToBeProcessed);

		String[] values = lineToBeProcessed.split(" ");

		long collectedTimeStamp = System.currentTimeMillis();
		float humidity = Float.parseFloat(values[0] + "f");
		float temperature = Float.parseFloat(values[1] + "f");
		
		if ( (humidity < 100) && (temperature < 100) ){
			DHTSensor dht = new DHTSensor(collectedTimeStamp, humidity, temperature);

			this.dhtSensorService.addDHTSensorData(dht);			
		}

		this.output = "";
	}

}