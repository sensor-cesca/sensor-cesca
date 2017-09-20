package com.cesca.sensors.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import com.cesca.sensors.entity.DHTSensor;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@Controller
public class DHTSensorSerialMonitorService implements SerialPortEventListener {

	@Autowired
	public DHTSensorService dhtSensorService;

	private SerialPort serialPort;
	private String output = "";

	private Logger log = Logger.getLogger(this.getClass());

	public DHTSensorSerialMonitorService(@Value("${app.dhtsensor.reader.portName}") String portName,
			@Value("${app.dhtsensor.reader.enabled}") boolean isEnabled) {

		if (isEnabled) {
			this.serialPort = new SerialPort(portName);

			boolean connected = false;

			while (!connected) {
				try {
					serialPort.openPort();
					serialPort.setParams(9600, 8, 1, 0);// Set params.
					serialPort.addEventListener(this);// Add
					connected = true;
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		log.debug("Event received");

		if (event.isRXCHAR()) {

			try {
				String serialOutput = this.serialPort.readString();
				if (serialOutput != null) {
					serialOutput.replaceAll("null", "");
					this.output += serialOutput;

					String[] lines = output.split("\n\r");
					String lineToBeProcessed = null;

					for (String line : lines) {
						String[] values = line.split(" ");
						if ((line.length() == 11) && (values.length == 2)) {
							if (values[0].length() == values[1].length()) {
								lineToBeProcessed = line;
							}
						}
					}

					if (lineToBeProcessed != null) {
						this.processSerialLine(lineToBeProcessed);
					}
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

		if ((humidity < 100) && (temperature < 100)) {
			DHTSensor dht = new DHTSensor(collectedTimeStamp, humidity, temperature);

			this.dhtSensorService.addDHTSensorData(dht);
		}

		this.output = "";
	}

}