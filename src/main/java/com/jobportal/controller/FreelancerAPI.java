package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.service.IFreelancerService;

@RestController
//@RequestMapping("value=/jobportal/freelancer")
@RequestMapping(value="/jobportal/freelancer")
@CrossOrigin("http://localhost:3000")
public class FreelancerAPI 
{
	@Autowired
	private IFreelancerService ifreelancerService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/save")
	//public ResponseEntity<String> update(@RequestBody FreelancerDTO freelancerDTO) throws Exception
	public ResponseEntity<String> save(@RequestBody FreelancerDTO freelancerDTO) throws Exception
	{
		FreelancerDTO updatesFreelancer = ifreelancerService.save(freelancerDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<String> update(@RequestBody FreelancerDTO freelancerDTO) throws Exception
	{
		FreelancerDTO addedFreelancer = ifreelancerService.update(freelancerDTO);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/getallfreelancersbyid/{id}")
	public ResponseEntity<FreelancerDTO> findById(@PathVariable Integer id) throws Exception
	{
		FreelancerDTO freelancer = ifreelancerService.findById(id);
		return new ResponseEntity<>(freelancer, HttpStatus.OK);
	}
}
