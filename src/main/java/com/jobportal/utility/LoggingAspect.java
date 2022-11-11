package com.jobportal.utility;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

	@Autowired
	Environment environment;

	Logger logger = LogManager.getLogger(this.getClass());

	@AfterThrowing(pointcut = "execution(* com.jobportal.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception)
			throws Exception {
		logger.error(exception.getMessage(), exception);
		logger.error(environment.getProperty(exception.getMessage()));

}
}