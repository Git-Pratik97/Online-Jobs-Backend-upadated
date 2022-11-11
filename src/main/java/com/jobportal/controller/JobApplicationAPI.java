
package com.jobportal.controller;

import java.util.List;


import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.jobportal.dto.AdminDTO;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.IAdminService;
import com.jobportal.service.IFreelancerService;
import com.jobportal.service.IJobApplicationService;
import com.jobportal.service.IJobService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value="/jobportal/jobapplication")
public class JobApplicationAPI {
	
	@Autowired
	private IJobApplicationService iJobApplicationService;
	
	@Autowired
	private IFreelancerService iFreelancerService;
	
	@Autowired
	private IJobService iJobService;
	
	@Autowired
	Environment environment;
	
	
	@PostMapping(value="/applytojob/{freelancerId}/{jobId}")
	public ResponseEntity<String> applyToJob(@PathVariable Integer jobId, @PathVariable Integer freelancerId, @RequestBody String coverLetter) throws Exception{
		
		String status = iJobApplicationService.applyToJob(jobId, coverLetter, freelancerId);
		String successMessage = environment.getProperty("API.APPLIED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@PutMapping(value="/updatejobapplication/{freelancerId}/{jobId}")
	public ResponseEntity<String> updateJobApplicatio(@PathVariable Integer jobId, @PathVariable Integer freelancerId, @RequestBody String coverLetter) throws Exception{
		
		String status = iJobApplicationService.updateJobApplication(jobId, coverLetter, freelancerId);
		String successMessage = environment.getProperty("API.UPDATED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{freelancerId}/{jobId}")
	public ResponseEntity<String> remove(@PathVariable Integer jobId, @PathVariable Integer freelancerId) throws Exception{
		String status = iJobApplicationService.remove(jobId, freelancerId);
		String successMessage = environment.getProperty("API.REMOVED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/findbyid/{jobApplicationId}")
	public ResponseEntity<JobApplicationDTO> findById(@PathVariable int jobApplicationId) throws Exception{
		JobApplicationDTO jobApplication = iJobApplicationService.findById(jobApplicationId);
		return new ResponseEntity<>(jobApplication, HttpStatus.OK);
	}

	@GetMapping(value="/findbyfreelancer/{freelancerId}")
	public ResponseEntity<List<JobApplicationDTO>> findByFreelancer(@PathVariable int freelancerId) throws Exception{
		List<JobApplicationDTO> jobAppDTOs = iJobApplicationService.findByFreelancer(freelancerId);
		return new ResponseEntity<>(jobAppDTOs, HttpStatus.OK);
	}

	@GetMapping(value="/findAll")
	public ResponseEntity<List<JobApplicationDTO>> findAll() throws Exception {
		List<JobApplicationDTO> jobApplicationDTOs = iJobApplicationService.findAll();
		return new ResponseEntity<>(jobApplicationDTOs, HttpStatus.OK);
	}
//	@GetMapping(value="/findbyrecruiter/{recruiterId}")
//	public ResponseEntity<List<JobApplicationDTO>> findByRecruiter(@PathVariable int recruiterId) throws Exception{
//		List<JobApplicationDTO> jobAppDTOs = iJobApplicationService.findByRecruiter(recruiterId);
//		return new ResponseEntity<>(jobAppDTOs, HttpStatus.OK);
//	}
	
	
	@GetMapping(value="/findbyfreelancer/{freelancerId}")
	public ResponseEntity<List<JobApplicationDTO>> findByFreelancer(@PathVariable int freelancerId) throws Exception{
		List<JobApplicationDTO> jobAppDTOs = iJobApplicationService.findByFreelancer(freelancerId);
		return new ResponseEntity<>(jobAppDTOs, HttpStatus.OK);
	}
	
}
