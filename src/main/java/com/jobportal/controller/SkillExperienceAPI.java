package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.FreelancerDTO;

import com.jobportal.dto.SkillDTO;
import com.jobportal.dto.SkillExperienceDTO;
import com.jobportal.service.ISkillExperienceService;

@RestController
@RequestMapping(value="/skillexperience")
@CrossOrigin("http://localhost:3000")
public class SkillExperienceAPI {
	
	@Autowired
	private ISkillExperienceService iSkillExperienceService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value="/addSkill/{skillId}/{freelancerId}")
	public ResponseEntity<String> addSkill(@PathVariable Integer skillId, @PathVariable Integer freelancerId, @RequestParam Integer years) throws Exception{
		String status = iSkillExperienceService.addSkill(skillId, freelancerId, years);
		String successMessage = environment.getProperty("API.APPLIED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@PutMapping(value="/updateskillyears/{freelancerId}/{skillId}")
	public ResponseEntity<String> updateSkillYears(@PathVariable Integer skillId,@PathVariable Integer freelancerId, @RequestParam Integer years) throws Exception{
		String status = iSkillExperienceService.updateSkillYears(skillId, freelancerId, years);
		String successMessage = environment.getProperty("API.UPDATED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
}
