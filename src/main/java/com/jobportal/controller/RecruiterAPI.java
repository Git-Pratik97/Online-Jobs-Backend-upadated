package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.dto.AdminDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.service.IAdminService;
import com.jobportal.service.IRecruiterService;

@RestController
@RequestMapping(value="/jobportal/recruiter")
@CrossOrigin("http://localhost:3000")
public class RecruiterAPI {
	
	@Autowired
	private IRecruiterService iRecruiterService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/save")
	public ResponseEntity<String> save(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String userName,@RequestParam String password) throws Exception{
		String addedRecruiter = iRecruiterService.save(firstName,lastName,userName,password);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		if(addedRecruiter.equals("SUCCESS")){
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
		}
		else {
			throw new InvalidRecruiterException("API.INSERT_FAILED");
		}
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String userName,@RequestParam String password) throws Exception{
		String updateRecruiter = iRecruiterService.update(id,firstName,lastName,userName,password);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		if(updateRecruiter.equals("SUCCESS")){
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		}
		else {
			throw new InvalidRecruiterException("API.UPDATE_FAILED");
		}
	}
	
	@GetMapping(value="/findbyid/{id}")
	public ResponseEntity<RecruiterDTO> findById(@PathVariable Integer id) throws InvalidRecruiterException{
		RecruiterDTO recruiter = iRecruiterService.findById(id);
		return new ResponseEntity<>(recruiter, HttpStatus.OK);
	}
	

}
