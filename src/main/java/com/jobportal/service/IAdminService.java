
package com.jobportal.service;

import com.jobportal.dto.AdminDTO;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;


public interface IAdminService {
	
	public String save (AdminDTO adminDTO) throws InvalidAdminException;
	public String update (AdminDTO adminDTO) throws InvalidAdminException;
	public AdminDTO findById(int Id) throws InvalidAdminException;
	String deleteFreelancerById(int freelancerId) throws InvalidFreelancerException;
	String deleteRecruiterById(int recruiterId) throws InvalidRecruiterException;
	
	
}
