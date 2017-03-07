package com.avo.workoutlogger.partner.workoutmanager.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * A basic exception with the Workout Manager response.
 * 
 * @author Andrew vo
 *
 */
public class WorkoutManagerException extends Exception {

	/** Default value */
	private static final long serialVersionUID = 1L;

	@Getter@Setter
	private String response;

	/**
	 * Default constructor with setting the workout manager response message.
	 * 
	 * @param response Workout Manager response message.
	 */
	public WorkoutManagerException(String response) {
		super("WORKOUT MANAGER ERROR: " + response);
		this.response = response;
	}
}
