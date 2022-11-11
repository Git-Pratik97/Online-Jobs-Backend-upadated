package com.jobportal.service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.entity.Freelancer;
import com.jobportal.exception.JobPortalException;

public interface IFreelancerService 
{
	public FreelancerDTO save(FreelancerDTO freelancerDTO)throws JobPortalException;
	public FreelancerDTO findById(Integer id)throws JobPortalException;
	public FreelancerDTO update(FreelancerDTO freelancerDTO)throws JobPortalException;
}
