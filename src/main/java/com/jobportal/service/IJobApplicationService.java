
package com.jobportal.service;
 

import java.util.List;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.exception.InvalidJobApplicationException;



public interface IJobApplicationService {

	public String applyToJob(int jobId, String coverletter, int freelancerId) throws Exception;
	
	public String updateJobApplication(int jobDId, String coverLetter, int freelancerId) throws Exception;
	
	public String remove(int jobId, int freelancerId) throws Exception;

	public JobApplicationDTO findById(int id) throws Exception;

	List<JobApplicationDTO> findByFreelancer(int freelancerId) throws Exception;

	List<JobApplicationDTO> findAll() throws InvalidJobApplicationException;

//	List<JobApplicationDTO> findByRecruiter(int recruiterId) throws Exception;
}

