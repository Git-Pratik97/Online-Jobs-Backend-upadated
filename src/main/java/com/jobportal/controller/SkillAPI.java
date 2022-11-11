package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.SkillDTO;
import com.jobportal.service.ISkillService;


@RestController
@RequestMapping(value="/skill")
@CrossOrigin("http://localhost:3000")
public class SkillAPI {
	
	@Autowired
	private ISkillService iSkillService;
	@Autowired
	Environment environment;
	
	@PostMapping(value="/save")
	public ResponseEntity<String> save(@RequestBody SkillDTO skillDTO) throws Exception{
		String inserted = iSkillService.save(skillDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@PutMapping(value="/update")
	public ResponseEntity<String> update(@RequestBody SkillDTO skillDTO) throws Exception{
		String updated = iSkillService.update(skillDTO);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/remove")
	public ResponseEntity<String> remove(@RequestBody SkillDTO skillDTO) throws Exception{
		iSkillService.remove(skillDTO);
		String successMessage = environment.getProperty("API.REMOVE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}	
}
