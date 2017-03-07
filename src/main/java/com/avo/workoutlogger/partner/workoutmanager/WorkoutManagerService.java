package com.avo.workoutlogger.partner.workoutmanager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.avo.workoutlogger.model.WorkoutLoggerResponse;
import com.avo.workoutlogger.partner.workoutmanager.exception.WorkoutManagerException;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLog;

/**
 * The Workout Manager Service class to interact with their API.
 * 
 * @author Andrew Vo
 *
 */
@Service
public class WorkoutManagerService {
	private static final Logger log = Logger.getLogger(WorkoutManagerService.class);
	
	/**
	 * Get the user's workout logs.
	 * 
	 * @param workoutLogId
	 * @return The WorkoutLoggerResponse.
	 * @throws WorkoutManagerException
	 */
	public WorkoutLoggerResponse getWorkoutLog(String workoutLogId) throws WorkoutManagerException {
		return getWorkoutLog(workoutLogId, null);
	}
	
	/**
	 * Get the user's workout logs. If workoutLogId is specified, it will
	 * return back the matching log with that ID.
	 * 
	 * @param workoutLogId The workout log ID.
	 * @param restTemplate The restTemplate.
	 * @return The WorkoutLoggerResponse.
	 * @throws WorkoutManagerException 
	 */
	public WorkoutLoggerResponse getWorkoutLog(String workoutLogId, RestTemplate restTemplate) throws WorkoutManagerException {
		WorkoutLoggerResponse workoutLoggerResponse = new WorkoutLoggerResponse();
		if (workoutLogId != null) {
			WorkoutLog workoutLog = WorkoutManagerCommand.getWorkoutLog(restTemplate, workoutLogId);
			if (workoutLog != null) {
				workoutLoggerResponse.getWorkoutLogs().add(workoutLog);
			}
		}
		else {
			List<WorkoutLog> workoutLogs = WorkoutManagerCommand.getWorkoutLogs(restTemplate).getResults();
			if (workoutLogs != null && !workoutLogs.isEmpty()) {
				workoutLoggerResponse.getWorkoutLogs().addAll(workoutLogs);
			}
		}
		
		return workoutLoggerResponse;
	}
	
	/**
	 * Log the user's workout.
	 * 
	 * @param workoutLog The workout log.
	 * @returnThe WorkoutLoggerResponse.
	 * @throws WorkoutManagerException
	 */
	public WorkoutLoggerResponse logWorkout(WorkoutLog workoutLog) throws WorkoutManagerException {
		return logWorkout(workoutLog, null);
	}
	
	/**
	 * Log the user's workout.
	 * 
	 * @param workoutLog The workout log.
	 * @param restTemplate The restTemplate.
	 * @return The WorkoutLoggerResponse.
	 * @throws WorkoutManagerException 
	 */
	public WorkoutLoggerResponse logWorkout(WorkoutLog workoutLog, RestTemplate restTemplate) throws WorkoutManagerException {
		WorkoutLoggerResponse response = new WorkoutLoggerResponse();
		response.getWorkoutLogs().add(WorkoutManagerCommand.logWorkout(restTemplate, workoutLog));
		return response;
	}
	
	/**
	 * Update an existing workout log using the workout log ID.
	 * 
	 * @param workoutLogId The ID of the workout log to update.
	 * @param workoutLog The updated workoutLog data.
	 * @return The WorkoutLoggerResponse.
	 * @throws WorkoutManagerException
	 */
	public WorkoutLoggerResponse updateWorkoutLog(String workoutLogId, 
											WorkoutLog workoutLog) throws WorkoutManagerException {
		return updateWorkoutLog(workoutLogId, workoutLog, null);
	}
	
	/**
	 * Update an existing workout log using the workout log ID.
	 * 
	 * @param workoutLogId The ID of the workout log to update.
	 * @param workoutLog The updated workoutLog data.
	 * @param restTemplate The restTemplate.
	 * @return The WorkoutLoggerResponse.
	 * @throws WorkoutManagerException 
	 */
	public WorkoutLoggerResponse updateWorkoutLog(String workoutLogId, 
											WorkoutLog workoutLog, 
											RestTemplate restTemplate) throws WorkoutManagerException {
		WorkoutLoggerResponse response = new WorkoutLoggerResponse();
		response.getWorkoutLogs().add(WorkoutManagerCommand.updateWorkoutLog(
				restTemplate, workoutLog, workoutLogId));
		return response;
	}
	
}
