package com.avo.workoutlogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.avo.workoutlogger.common.ConfigService;

/**
 * A simple REST service to help log and manage a user's workouts.
 * 
 * @author Andrew Vo
 *
 */
@SpringBootApplication
public class Application {
	
	// Provided to instantiate the ConfigService for the first time (singleton service).
	@SuppressWarnings("unused")
	@Autowired
	private ConfigService configService;

	// Provided to instantiate the Environment for the first time (singleton service).
	@SuppressWarnings("unused")
	@Autowired
	private Environment env;
	
	/**
	 * Run spring boot.
	 * 
	 * @param args Command line arguments, currently unused.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
