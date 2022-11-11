package com.jobportal.controller;

import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.dto.JobDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.dto.SkillDTO;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.IJobService;

@RestController
@RequestMapping(value="/jobportal/job")
@CrossOrigin("http://localhost:3000")
public class JobAPI {
     @Autowired
	private IJobService ijobservice;
     
     @Autowired
 	Environment environment;
     
     @PostMapping(value ="/postjob/{freelancerId}/{recruiterId}/{skillId}")
	 public ResponseEntity<String> postJob(@PathVariable Integer freelancerId, @PathVariable Integer recruiterId, @PathVariable Integer skillId) throws InvalidJobException, InvalidBookmarkedFreelancerException {
		 String status = ijobservice.postjob(freelancerId, recruiterId, skillId);
		 String successMessage = environment.getProperty("API.BOOKMARKED_SUCCESSFULLY");
		 return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	 }
     
     @GetMapping(value="/findbyid/{id}")
     public ResponseEntity <JobDTO>findById(@PathVariable Integer id) throws JobPortalException{
    	 JobDTO job = ijobservice.findById(id);
    	 return new ResponseEntity<>(job, HttpStatus.OK);
    }
	@GetMapping(value="/findjobsbyskillName/{name}")
	public ResponseEntity<Object> findJobsBySkillName(@PathVariable String name)throws JobPortalException{
		return new ResponseEntity<>(ijobservice.findJobsBySkillName(name), HttpStatus.OK);
	}
     @GetMapping(value="/close/{id}")
    public ResponseEntity<Object> close(@PathVariable Integer id)throws JobPortalException{
    	 try {
 			ijobservice.close(id);
 		} catch (InvalidJobException exception) {
 			throw new InvalidJobException("Job with given id not found");
 		}
 		return new ResponseEntity<>("closed successfully", HttpStatus.OK);
 	}

	@PutMapping(value="/awardJob/{jobId}/{freelancerId}")
	public ResponseEntity<String> awardJob(@PathVariable int jobId, @PathVariable int freelancerId) throws Exception{
		try {
			ijobservice.awardJob(jobId, freelancerId);
		} catch (Exception exception) {
			throw new InvalidJobException("Failed to award job");
		}
		return new ResponseEntity<>("Job Awarded", HttpStatus.OK);
	}
}
    
     

