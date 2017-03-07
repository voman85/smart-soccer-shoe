package com.avo.workoutlogger.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * A singleton config helper to aggregate default properties and requested profile properties.
 * 
 * @author Andrew Vo
 *
 */
@Service
public class ConfigService {
	
	@Autowired
	protected Environment env;
	
	public static String URI_SCHEME_UNSECURE;
	
	public static String WORKOUT_MANAGER_PATH;
	public static String WORKOUT_MANAGER_KEY;
	public static String WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT;
	public static int WORKOUT_MANAGER_TIMEOUT;
	public static String WORKOUT_MANAGER_ERROR = "WORKOUT MANAGER API Error: ";
	
	/**
	 * Initialize variables from the properties files.
	 */
	@PostConstruct
	public void init() {
		URI_SCHEME_UNSECURE = env.getProperty("uri.scheme.unsecure");
		
		WORKOUT_MANAGER_PATH = env.getProperty("workout.manager.path");
		WORKOUT_MANAGER_KEY = env.getProperty("workout.manager.key");
		WORKOUT_MANAGER_WORKOUT_LOG_ENDPOINT = env.getProperty("workout.manager.endpoint.workoutlog");
		WORKOUT_MANAGER_TIMEOUT = Integer.parseInt(env.getProperty("workout.manager.timeout"));
	}
}