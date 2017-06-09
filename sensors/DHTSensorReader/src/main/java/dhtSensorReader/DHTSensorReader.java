package dhtSensorReader;

import jssc.SerialPort;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.cesca.arduino.DHTSensor.*;
import com.cesca.tools.PropertyManager.*;

public class DHTSensorReader {

	static Logger log = Logger.getLogger(DHTSensor.class.getName());

	public static void main(String[] args) {
		setLogLevel();
		log.info("Sarting DHTSensorReader");

		String portName = PropertyManager.getInstance().getProperty("dhtsensor.port");

		SerialPort serialPort = new SerialPort(portName);
		try {
			serialPort.openPort();// Open serial port
			serialPort.setParams(9600, 8, 1, 0);// Set params.
			// byte[] buffer = serialPort.readBytes(10);// Read 10 bytes from
			// serial port
			log.info("Adding Event Listener on Serial Port: " + serialPort);
			serialPort.addEventListener(new SerialReader(serialPort));// Add
																		// SerialPortEventListener

			// serialPort.closePort();// Close serial port

		} catch (Exception ex) {
			log.error(ex.toString());
		}

	}

	private static void setLogLevel() {
		BasicConfigurator.configure();
		String logLevel = PropertyManager.getInstance().getProperty("log.level");
		switch (logLevel) {
		case "ALL":
			LogManager.getRootLogger().setLevel(Level.ALL);
			break;
		case "DEBUG":
			LogManager.getRootLogger().setLevel(Level.DEBUG);
			break;
		case "ERROR":
			LogManager.getRootLogger().setLevel(Level.ERROR);
			break;
		case "FATAL":
			LogManager.getRootLogger().setLevel(Level.FATAL);
			break;
		case "INFO":
			LogManager.getRootLogger().setLevel(Level.INFO);
			break;
		case "TRACE":
			LogManager.getRootLogger().setLevel(Level.TRACE);
			break;
		case "WARN":
			LogManager.getRootLogger().setLevel(Level.WARN);
			break;
		}

	}
}
