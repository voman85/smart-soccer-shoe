package com.avo.workoutlogger.partner.workoutmanager;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.avo.workoutlogger.common.ConfigService;
import com.avo.workoutlogger.partner.workoutmanager.exception.WorkoutManagerException;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLog;
import com.avo.workoutlogger.partner.workoutmanager.model.response.WorkoutLogsResponse;

/**
 * This class handles all requests made to the Workout Manager API.
 * 
 * @author Andrew Vo
 *
 */
public class WorkoutManagerCommand {
	private static final Logger log = Logger.getLogger(Logger.class);
	
	/**
	 * The Workout Manager command to get the user's workout log from ID.
	 * 
	 * @param restTemplate The RestTemplate.
	 * @return The WorkoutLogResponse.
	 * @throws WorkoutManagerException 
	 */
	public static WorkoutLog getWorkoutLog(RestTemplate restTemplate,  String workoutLogId) throws WorkoutManagerException {
		if (restTemplate == null) {
			restTemplate = getRestTemplate();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + ConfigService.WORKOUT_MANAGER_KEY);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme(ConfigService.URI_SCHEME_UNSECURE)
				.host(ConfigService.WORKOUT_MANAGER_PATH)
				.path(ConfigService.WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT)
				.path(workoutLogId).build();
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<WorkoutLog> response = null;
		try {
			response = restTemplate.exchange(
					uriComponents.toUriString(),
					HttpMethod.GET,
					entity,
					WorkoutLog.class);
		}
		catch (Exception e) {
			log.error(ConfigService.WORKOUT_MANAGER_ERROR + e.getMessage(), e);
			throw new WorkoutManagerException(e.getMessage());
		}

		if (response.getBody() == null) {
			log.error("Response is null getting workout logs.");
			return null;
		}

		return response.getBody();
	}
	
	/**
	 * The Workout Manager command to get the user's workout logs.
	 * 
	 * @param restTemplate The RestTemplate.
	 * @return The WorkoutLogsResponse.
	 * @throws WorkoutManagerException 
	 */
	public static WorkoutLogsResponse getWorkoutLogs(RestTemplate restTemplate) throws WorkoutManagerException {
		if (restTemplate == null) {
			restTemplate = getRestTemplate();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + ConfigService.WORKOUT_MANAGER_KEY);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme(ConfigService.URI_SCHEME_UNSECURE)
				.host(ConfigService.WORKOUT_MANAGER_PATH)
				.path(ConfigService.WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT).build();
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<WorkoutLogsResponse> response = null;
		try {
			response = restTemplate.exchange(
					uriComponents.toUriString(),
					HttpMethod.GET,
					entity,
					WorkoutLogsResponse.class);
		}
		catch (Exception e) {
			log.error(ConfigService.WORKOUT_MANAGER_ERROR + e.getMessage(), e);
			throw new WorkoutManagerException(e.getMessage());
		}

		if (response.getBody() == null) {
			log.error("Response is null getting workout logs.");
			return null;
		}

		return response.getBody();
	}
	
	/**
	 * The Workout Manager command to log a workout to the manager.
	 * 
	 * @param restTemplate The RestTemplate.
	 * @return The WorkoutLog.
	 * @throws WorkoutManagerException 
	 */
	public static WorkoutLog logWorkout(RestTemplate restTemplate, WorkoutLog workoutLog) throws WorkoutManagerException {
		if (restTemplate == null) {
			restTemplate = getRestTemplate();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + ConfigService.WORKOUT_MANAGER_KEY);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme(ConfigService.URI_SCHEME_UNSECURE)
				.host(ConfigService.WORKOUT_MANAGER_PATH)
				.path(ConfigService.WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT).build();
		
		String logData = workoutLog.getFormUrl();
		HttpEntity<?> entity = new HttpEntity<>(logData, headers);
		ResponseEntity<WorkoutLog> response = null;
		try {
			response = restTemplate.exchange(
					uriComponents.toUriString(),
					HttpMethod.POST,
					entity,
					WorkoutLog.class);
		}
		catch (Exception e) {
			log.error(ConfigService.WORKOUT_MANAGER_ERROR + e.getMessage(), e);
			throw new WorkoutManagerException(e.getMessage());
		}

		if (response.getBody() == null) {
			log.error("Response is null getting workout logs.");
			return null;
		}

		return response.getBody();
	}
	
	/**
	 * The Workout Manager command to update the user's workout log from ID.
	 * 
	 * @param restTemplate The RestTemplate.
	 * @param workoutLog The workoutLog with updated data.
	 * @param workoutLogId The workout log ID.
	 * @return The WorkoutLogResponse.
	 * @throws WorkoutManagerException 
	 */
	public static WorkoutLog updateWorkoutLog(RestTemplate restTemplate,
												WorkoutLog workoutLog,								
												String workoutLogId) throws WorkoutManagerException {
		if (restTemplate == null) {
			restTemplate = getRestTemplate();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + ConfigService.WORKOUT_MANAGER_KEY);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme(ConfigService.URI_SCHEME_UNSECURE)
				.host(ConfigService.WORKOUT_MANAGER_PATH)
				.path(ConfigService.WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT)
				.path(workoutLogId + "/").build();
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<WorkoutLog> response = null;
		try {
			response = restTemplate.exchange(
					uriComponents.toUriString(),
					HttpMethod.GET,
					entity,
					WorkoutLog.class);
		}
		catch (Exception e) {
			log.error(ConfigService.WORKOUT_MANAGER_ERROR + e.getMessage(), e);
			throw new WorkoutManagerException(e.getMessage());
		}

		if (response.getBody() == null) {
			log.error("Response is null getting workout logs.");
			return null;
		}

		return response.getBody();
	}
	
	/**
	 * Return a new WorkoutManager rest template.
	 * 
	 * @return A new WorkoutManager rest template.
	 */
	public static RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		httpRequestFactory.setConnectionRequestTimeout(ConfigService.WORKOUT_MANAGER_TIMEOUT);
		httpRequestFactory.setConnectTimeout(ConfigService.WORKOUT_MANAGER_TIMEOUT);
		httpRequestFactory.setReadTimeout(ConfigService.WORKOUT_MANAGER_TIMEOUT);
		
		return new RestTemplate(httpRequestFactory);
	}
}
