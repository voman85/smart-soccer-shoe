package com.avo.workoutlogger.partner.workoutmanager.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The WorkoutLog response class.
 * 
 * @author Andrew Vo
 *
 */
@JsonInclude(Include.NON_NULL)
public class WorkoutLog {
	@ApiModelProperty(hidden = true)
	@Getter@Setter@JsonProperty("id")
	private int id;
	
	@ApiModelProperty(required = true)
	@Getter@Setter@JsonProperty("reps")
	private int reps = 0;
	
	@ApiModelProperty(required = true)
	@Getter@Setter@JsonProperty("weight")
	private double weight = 0.00;
	
	@ApiModelProperty(value = "Use this date_format: yyyy-MM-dd", required = true)
	@Getter@Setter@JsonProperty("date")
	private String date = "";
	
	@ApiModelProperty(required = true)
	@Getter@Setter@JsonProperty("exercise")
	private int exercise = 1;
	
	@ApiModelProperty(hidden = true)
	@Getter@Setter@JsonProperty("workout")
	private int workout = 104029; //Default value
	
	@ApiModelProperty(hidden = true)
	@Getter@Setter@JsonProperty("repetition_unit")
	private int repetitionUnit = 1;
	
	@ApiModelProperty(hidden = true)
	@Getter@Setter@JsonProperty("weight_unit")
	private int weightUnit = 1;
	
	/**
	 * The post to log workout to the Workout Manager Api has an issue
	 * with the date formatting in the payload. So as a workaround, the
	 * data is converted to a url String to be sent in as MediaType 
	 * Application Form UrlEncoded.
	 * 
	 * @return The url version of the data to be logged.
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public String getFormUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append("reps=" + this.reps);
		sb.append("&weight=" + this.weight);
		sb.append("&date=" + this.date);
		sb.append("&exercise=" + this.exercise);
		sb.append("&workout=" + this.workout);
		sb.append("&repetition_unit=" + this.repetitionUnit);
		sb.append("&weight_unit=" + this.weightUnit);
		
		return sb.toString();
	}
}
