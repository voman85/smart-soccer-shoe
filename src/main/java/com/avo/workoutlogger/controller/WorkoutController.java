package com.avo.workoutlogger.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avo.workoutlogger.model.WorkoutLoggerResponse;
import com.avo.workoutlogger.partner.workoutmanager.WorkoutManagerService;
import com.avo.workoutlogger.partner.workoutmanager.exception.WorkoutManagerException;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLog;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * The Workout Controller to handle all workout-related requests.
 * 
 * @author Andrew Vo
 *
 */
@RestController
public class WorkoutController {
	private static final Logger log = Logger.getLogger(WorkoutController.class);
	
	@Autowired
	private WorkoutManagerService workoutManagerService;
	
	/**
	 * The rest controller for retrieving the workout log(s).
	 * 
	 * @param workoutLogId The workout log ID.
	 * @return The WorkoutLoggerResponse.
	 */
	@ApiOperation(value = "Get the workout log by id. Will get all the logs if id is empty.")
	@RequestMapping(value = "/v1/workoutlog/{id}", method = RequestMethod.GET)
	public WorkoutLoggerResponse getWorkoutLog(
			@ApiParam(value = "The workout log ID.", required = false)
			@PathVariable("id") String workoutLogId) {
		
		WorkoutLoggerResponse response = null;
		try {
			response = workoutLogId.equals("{id}") 
						? workoutManagerService.getWorkoutLog(null)
						: workoutManagerService.getWorkoutLog(workoutLogId);
		}
		catch (WorkoutManagerException e) {
			response = new WorkoutLoggerResponse();
			response.getErrors().add(e.getMessage());
		}
					
		if (response == null) {
			response = new WorkoutLoggerResponse();
			String errorMsg = "Unsuccessful getting workout log.";
			log.error(errorMsg);
			response.getErrors().add(errorMsg);
		}
		
		return response;
	}
	
	/**
	 * Endpoint for the user to log their workout to the workout manager.
	 * 
	 * @param workoutLog The workout log data.
	 * @return The WorkoutLoggerResponse.
	 */
	@ApiOperation(value = "Log workout.")
	@RequestMapping(value = "/v1/workoutlog/logWorkout", method = RequestMethod.POST)
	public WorkoutLoggerResponse logWorkout(
			@ApiParam(value = "The workout log fields.", required = true)
			@RequestBody WorkoutLog workoutLog
			) {
		
		WorkoutLoggerResponse response = null;
		
		try {
			response = workoutManagerService.logWorkout(workoutLog);
		}
		catch (WorkoutManagerException e) {
			response = new WorkoutLoggerResponse();
			response.getErrors().add(e.getMessage());
		}
		
		if (response == null) {
			response = new WorkoutLoggerResponse();
			String errorMsg = "Log workout was unsuccessful.";
			log.error(errorMsg);
			response.getErrors().add(errorMsg);
		}
		
		return response;
	}
	
	/**
	 * Endpoint for the user to update an existing log on the workout manager.
	 * 
	 * @param workoutLogId The workout log ID to update.
	 * @param workoutLog The workout log data to be updated with.
	 * @return The WorkoutLoggerResponse.
	 */
	@ApiOperation(value = "Update an existing workout log.")
	@RequestMapping(value = "/v1/workoutlog/{id}", method = RequestMethod.PUT)
	public WorkoutLoggerResponse updateWorkoutLog(
			@ApiParam(value = "The workout log ID.", required = true)
			@PathVariable("id") String workoutLogId,
			
			@ApiParam(value = "The workout log fields.", required = true)
			@RequestBody WorkoutLog workoutLog
			) {
		
		WorkoutLoggerResponse response = null;
		
		if (workoutLogId == null || workoutLogId.equals("{id}")) {
			response = new WorkoutLoggerResponse();
			String errorMsg = "The workout log ID cannot be empty.";
			log.error(errorMsg);
			response.getErrors().add(errorMsg);
			return response;
		}
		
		try {
			response = workoutManagerService.updateWorkoutLog(workoutLogId, workoutLog);
		}
		catch (WorkoutManagerException e) {
			response = new WorkoutLoggerResponse();
			response.getErrors().add(e.getMessage());
		}
		
		if (response == null) {
			response = new WorkoutLoggerResponse();
			String errorMsg = "Log workout was unsuccessful.";
			log.error(errorMsg);
			response.getErrors().add(errorMsg);
		}
		
		return response;
	}
}
