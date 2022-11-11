package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.dto.FeedbackDTO;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.service.IFeedbackService;

@RestController
@RequestMapping(value="/jobportal/recruiter")
@CrossOrigin("http://localhost:3000")
public class FeedbackAPI {
	
	@Autowired
	private IFeedbackService iFeedbackService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/createfeedback/{recruiterId}/{freelancerId}")
	public ResponseEntity<String> creatingFeedback(@RequestParam int rating,@RequestBody String review,@PathVariable int recruiterId,@PathVariable int freelancerId) throws Exception{
		String feedback = iFeedbackService.createFeedback(rating,review,recruiterId,freelancerId);
		String successMessage=environment.getProperty("API.FEEDBACK_CREATED_SUCCESSFULLY");
		if(feedback.equals("SUCCESS")){
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		}
		else {
			throw new InvalidRecruiterException("API.INSERT_FAILED");
		}	
	}
	
	@GetMapping(value="/averagerating/{freelancerId}")
	public ResponseEntity<Integer> averageRating(@PathVariable int freelancerId) throws Exception{
		int average  = iFeedbackService.averageRating(freelancerId);
		return new ResponseEntity<>(average, HttpStatus.OK);
	}
	@GetMapping(value="/findfeedbacksbyfreelancer/{freelancerId}")
	public ResponseEntity<List<FeedbackDTO>> findFeedbacksByFreelancer(@PathVariable int freelancerId) throws Exception{
		List<FeedbackDTO> feedbackList = iFeedbackService.findFeedbacksByFreelancer(freelancerId);
		return new ResponseEntity<List<FeedbackDTO>>(feedbackList, HttpStatus.OK);
	}

}
