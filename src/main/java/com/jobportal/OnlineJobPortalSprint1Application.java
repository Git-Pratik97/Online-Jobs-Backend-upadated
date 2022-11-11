package com.jobportal;



//import org.springframework.beans.factory.annotation.Autowired;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.env.Environment;
//
//
//
//import com.jobportal.service.IFreelancerService;


@SpringBootApplication
public class OnlineJobPortalSprint1Application implements CommandLineRunner {
	
	public static final Log LOGGER = LogFactory.getLog(OnlineJobPortalSprint1Application.class);

	
//	@Autowired
//	private IFreelancerService iFreelancerService;
//
//	@Autowired
//	Environment environment;

	
	public static void main(String[] args) {
		SpringApplication.run(OnlineJobPortalSprint1Application.class, args);
	}
	

	public void run(String...args) {
		
	}

}
