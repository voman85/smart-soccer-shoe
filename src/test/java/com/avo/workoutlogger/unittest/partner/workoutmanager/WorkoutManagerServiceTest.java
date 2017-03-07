package com.avo.workoutlogger.unittest.partner.workoutmanager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.avo.workoutlogger.common.TestConfig;
import com.avo.workoutlogger.model.WorkoutLoggerResponse;
import com.avo.workoutlogger.partner.workoutmanager.WorkoutManagerService;
import com.avo.workoutlogger.partner.workoutmanager.exception.WorkoutManagerException;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLog;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLogsResponse;


/**
 * The unit test class for the Workout Manager service.
 * 
 * @author Andrew Vo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkoutManagerServiceTest extends TestConfig {
	@Mock
	RestTemplate restTemplateMock = mock(RestTemplate.class);
	
	WorkoutManagerService workoutManagerService;
	
	/**
	 * Initialize the fields used by the tests.
	 */
	@Before
	public void setUp() {
		super.setUp();
		
		//Set up workoutLog and workoutLogs responses.
		WorkoutLog workoutLogResponse = new WorkoutLog();
		workoutLogResponse.setId(123456);
		workoutLogResponse.setDate("2017-01-01");
		workoutLogResponse.setExercise(454);
		workoutLogResponse.setWorkout(104029);
		workoutLogResponse.setReps(1);
		ResponseEntity<WorkoutLog> workoutLogResponseEntity = 
				new ResponseEntity<WorkoutLog>(workoutLogResponse, HttpStatus.ACCEPTED);
		
		WorkoutLogsResponse workoutLogsResponse = new WorkoutLogsResponse();
		workoutLogsResponse.setCount(1);
		workoutLogsResponse.setResults(new ArrayList<WorkoutLog>());
		workoutLogsResponse.getResults().add(workoutLogResponse);
		ResponseEntity<WorkoutLogsResponse> workoutLogsResponseEntity = 
				new ResponseEntity<WorkoutLogsResponse>(workoutLogsResponse, HttpStatus.ACCEPTED);
		
		when(restTemplateMock.exchange(Matchers.anyString(), 
				Matchers.any(HttpMethod.class), 
				Matchers.any(HttpEntity.class), 
				Matchers.eq(WorkoutLog.class))).thenReturn(workoutLogResponseEntity);
		
		when(restTemplateMock.exchange(Matchers.anyString(), 
				Matchers.any(HttpMethod.class), 
				Matchers.any(HttpEntity.class), 
				Matchers.eq(WorkoutLogsResponse.class))).thenReturn(workoutLogsResponseEntity);
		
		workoutManagerService = new WorkoutManagerService();
	}
	
	@Test
	public void testGetWorkoutLog() throws WorkoutManagerException {
		String workoutLogId = "123456";
		
		WorkoutLoggerResponse response = 
				workoutManagerService.getWorkoutLog(workoutLogId, restTemplateMock);
		assertEquals("Failed to get workout log by ID.",
				String.valueOf(response.getWorkoutLogs().get(0).getId()),
				workoutLogId);
	}
	
	@Test
	public void testLogWorkout() throws WorkoutManagerException {
		WorkoutLog workoutLog = new WorkoutLog();
		workoutLog.setDate("2017-01-01");
		workoutLog.setExercise(1);
		workoutLog.setReps(1);
		workoutLog.setWeight(150.00);
		workoutLog.setWorkout(104872);;
		WorkoutLoggerResponse response =
				workoutManagerService.logWorkout(workoutLog, restTemplateMock);
		
		Assert.assertNotNull("Log workout returned null response", response);
	}
	
	@Test
	public void testUpdateWorkoutLog() throws WorkoutManagerException {
		String workoutLogId = "123456";
		WorkoutLog workoutLog = new WorkoutLog();
		workoutLog.setId(Integer.parseInt(workoutLogId));
		workoutLog.setDate("2017-01-01");
		workoutLog.setExercise(1);
		workoutLog.setReps(1);
		workoutLog.setWeight(150.00);
		workoutLog.setWorkout(104872);;
		WorkoutLoggerResponse response =
				workoutManagerService.updateWorkoutLog(workoutLogId, workoutLog, restTemplateMock);
		
		Assert.assertNotNull("Update workout log returned null response", response);
	}
}
