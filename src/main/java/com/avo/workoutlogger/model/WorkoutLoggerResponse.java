package com.avo.workoutlogger.model;

import java.util.ArrayList;
import java.util.List;

import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLog;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * A workout logger response object that gets returned to the consumer.
 * 
 * @author Andrew Vo
 */
public class WorkoutLoggerResponse {
	
	@Getter@Setter@JsonProperty("workout_logs")
	List<WorkoutLog> workoutLogs = new ArrayList<>();
	@Getter@Setter@JsonProperty("errors")
	List<String> errors = new ArrayList<>();
	
	/** Default constructor */
	public WorkoutLoggerResponse() { }
	
	/**
	 * Constructor
	 * 
	 * @param workoutLogs The list of workout logs.
	 */
	public WorkoutLoggerResponse(List<WorkoutLog> workoutLogs) {
		this.workoutLogs = workoutLogs;
	}
	
	/**
	 * Constructor
	 * 
	 * @param workoutLogs The list of workout logs.
	 * @param errors The list of errors.
	 */
	public WorkoutLoggerResponse(List<WorkoutLog> workoutLogs, List<String> errors) {
		this.workoutLogs = workoutLogs;
		this.errors = errors;
	}
}
