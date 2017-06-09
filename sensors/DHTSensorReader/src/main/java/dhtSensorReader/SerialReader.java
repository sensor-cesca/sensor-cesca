package dhtSensorReader;

import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

import com.cesca.arduino.DHTSensor.*;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;


public class SerialReader implements SerialPortEventListener { 

	private SerialPort serialPort;
	private String output;

	private Logger log = Logger.getLogger(this.getClass());

	public SerialReader(SerialPort port) {
		this.serialPort = port;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		log.debug("Event received");
		if (event.isRXCHAR()) {
			log.debug("Event is RXCHAR");
			// TODO Auto-generated method stub
			try {
				String serialOutput = this.serialPort.readString(); 
				log.debug("Read from serial: " + serialOutput);
				if (serialOutput != null){
					if (!serialOutput.contains("null")){
						log.debug("Adding to be processed: " + serialOutput);
						this.output += serialOutput;
					}					
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else {
			log.debug("Event is not RXCHAR");
			log.debug("Event : " + event.toString());
		}
		this.checkOutput();
	}

	public void checkOutput() {
		log.debug("Checking for Line Breaks");
		if (this.output.contains("\n\r")) {
			log.debug("Found a line brake");
			log.debug(this.output);
			String[] lines = this.output.split("\n\r");
			log.debug("Length: " + lines.length);
			// System.out.println("Size: " + lines.length);
			if (lines.length > 1) {
				log.debug("There is a value to register");
				this.output = lines[lines.length - 1];
				log.debug("Value to register: " + this.output);
				if (!this.output.contains("null")) {
					log.debug("Create DHTSensor object");
					Date timestamp = Calendar.getInstance().getTime();
					String sensorOutput = lines[0];
					try {
						double humidity = Double.parseDouble(sensorOutput.split(" ")[0]);
						double temperature = Double.parseDouble(sensorOutput.split(" ")[1]);
						DHTSensor dht = new DHTSensor(timestamp, humidity, temperature);
						log.debug(dht.toString());
						DHTSensorDAO dao = new DHTSensorDAOImpl();
						dao.insert(dht);						
					} catch (Exception ex) {
						log.debug(ex.toString());
					}
				}
			}
		}
	}

}