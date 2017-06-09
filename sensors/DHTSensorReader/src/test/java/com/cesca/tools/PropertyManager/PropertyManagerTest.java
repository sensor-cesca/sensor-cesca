package com.cesca.tools.PropertyManager;

import static org.junit.Assert.*;

import java.util.Properties;
import org.junit.Test;

public class PropertyManagerTest {

	@Test
	public void should_be_possible_to_retrieve_a_property() {
		Properties props = new Properties();
		props.setProperty("user", "John");
		PropertyManager.setProperties(props);

		String actualUser = PropertyManager.getInstance().getProperty("user");
		assertEquals("John", actualUser);
	}

	@Test
	public void should_retrieve_property_from_default_when_not_found() {
		Properties props = new Properties();
		props.setProperty("usr", "John");
		PropertyManager.setProperties(props);

		Properties dProps = new Properties();
		dProps.setProperty("user", "John");
		PropertyManager.setDefaultProperties(dProps);
		
		String actualUser = PropertyManager.getInstance().getProperty("user");
		assertEquals("John", actualUser);		
	}

}
