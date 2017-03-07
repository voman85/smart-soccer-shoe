package com.avo.workoutlogger.partner.workoutmanager.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The WorkoutLogs response class.
 * 
 * @author Andrew Vo
 *
 */
@JsonInclude(Include.NON_NULL)
public class WorkoutLogsResponse {
	
	@Getter@Setter@JsonProperty("count")
	private int count;
	
	@Getter@Setter@JsonProperty("next")
	private String next;
	
	@Getter@Setter@JsonProperty("previous")
	private String previous;
	
	@Getter@Setter@JsonProperty("results")
	private List<WorkoutLog> results;
}
