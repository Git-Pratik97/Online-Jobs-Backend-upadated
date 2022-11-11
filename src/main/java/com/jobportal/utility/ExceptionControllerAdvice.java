package com.jobportal.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFeedbackException;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.exception.InvalidRecruiterException;


@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		    errorInfo.setTimestamp(LocalDateTime.now());
			
			String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(", "));
			errorInfo.setErrorMessage(errorMsg);
		
			return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(ResponseStatusException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(InvalidAdminException.class)
	public ResponseEntity<ErrorInfo> JobPortalExceptionHandler(InvalidAdminException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND); 
	}
		
	@ExceptionHandler(InvalidJobApplicationException.class)
	public ResponseEntity<ErrorInfo> JobPortalExceptionHandler(InvalidJobApplicationException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}		

	@ExceptionHandler(InvalidBookmarkedFreelancerException.class)
	public ResponseEntity<ErrorInfo> JobPortalExceptionHandler(InvalidBookmarkedFreelancerException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRecruiterException.class)
	public ResponseEntity<ErrorInfo> RecruiterExceptionHandler(InvalidRecruiterException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFeedbackException.class)
	public ResponseEntity<ErrorInfo> FeedbackExceptionHandler(InvalidFeedbackException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidJobException.class)
	public ResponseEntity<ErrorInfo> JobPortalExceptionHandler(InvalidJobException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty(exception.getMessage()));
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
}
