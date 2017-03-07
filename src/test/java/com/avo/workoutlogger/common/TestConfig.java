package com.avo.workoutlogger.common;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

/**
 * A test configuration class to help set up mocks and
 * values from the configService and properties file.
 * 
 * @author Andrew Vo
 *
 */
public class TestConfig {

	@Mock
	protected ConfigService configServiceMock = mock(ConfigService.class);
	
	/**
	 * Perform set up for tests by initializing mock classes.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		// Setup ConfigService
		Whitebox.setInternalState(configServiceMock, "URI_SCHEME_UNSECURE", "http");
		Whitebox.setInternalState(configServiceMock, "WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT", "TESTENDPOINT");
		Whitebox.setInternalState(configServiceMock, "WORKOUT_MANAGER_PATH", "TESTPATH.com/");
		Whitebox.setInternalState(configServiceMock, "WORKOUT_MANAGER_TIMEOUT", 1000);
	}
}
