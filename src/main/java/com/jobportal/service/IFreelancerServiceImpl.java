package com.jobportal.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.entity.Freelancer;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.IFreelancerDao;

@Service(value = "iFreelancerService")
@Transactional
public class IFreelancerServiceImpl implements IFreelancerService{
	@Autowired
	IFreelancerDao ifreelancerDao;
	
	@Override
	public FreelancerDTO save(FreelancerDTO freelancerDTO)throws JobPortalException
	{
		Optional<Freelancer> op1 = ifreelancerDao.findById(freelancerDTO.getId());
		if(op1.isPresent())
			throw new JobPortalException("Service.FREELANCER_FOUND");
		
		//adding/saving data to freelancer
		Freelancer freelancer = new Freelancer();
		freelancer.setId(freelancerDTO.getId());
		freelancer.setFirstName(freelancerDTO.getFirstName());
		freelancer.setLastName(freelancerDTO.getLastName());
		freelancer.setPassword(freelancerDTO.getPassword());
		freelancer.setAppliedJobs(freelancerDTO.getAppliedJobs());
		freelancer.setFeedbacks(freelancerDTO.getFeedbacks());
		freelancer.setSkills(freelancerDTO.getSkills());
		freelancer.setBookmarkedJobs(freelancerDTO.getBookmarkedJobs());
		
		ifreelancerDao.save(freelancer);
		
		return freelancerDTO;
	}

	@Override
	public FreelancerDTO findById(Integer id) throws JobPortalException 
	{
		Optional<Freelancer> op1 = ifreelancerDao.findById(id);
		Freelancer freelancer = op1.orElseThrow(() -> new JobPortalException("Service.FREELANCER_NOT_FOUND"));
		
		//adding data to the DTO by id
		FreelancerDTO freelancerDTO = new FreelancerDTO();
		freelancerDTO.setId(freelancer.getId());
		freelancerDTO.setFirstName(freelancer.getFirstName());
		freelancerDTO.setLastName(freelancer.getLastName());
		freelancerDTO.setPassword(freelancer.getPassword());
		freelancerDTO.setAppliedJobs(freelancer.getAppliedJobs());
		freelancerDTO.setFeedbacks(freelancer.getFeedbacks());
		freelancerDTO.setSkills(freelancer.getSkills());
		freelancerDTO.setBookmarkedJobs(freelancer.getBookmarkedJobs());
		
		return freelancerDTO;
	}

	@Override
	public FreelancerDTO update(FreelancerDTO freelancerDTO) throws JobPortalException 
	{
		Optional<Freelancer> op1 = ifreelancerDao.findById(freelancerDTO.getId());
		Freelancer freelancer = op1.orElseThrow(() -> new JobPortalException("Service.FREELANCER_NOT_FOUND"));
		
		//updating the freelancer DTO
		freelancer.setId(freelancerDTO.getId());
		freelancer.setFirstName(freelancerDTO.getFirstName());
		freelancer.setLastName(freelancerDTO.getLastName());
		freelancer.setPassword(freelancerDTO.getPassword());
		freelancer.setAppliedJobs(freelancerDTO.getAppliedJobs());
		freelancer.setFeedbacks(freelancerDTO.getFeedbacks());
		freelancer.setSkills(freelancerDTO.getSkills());
		freelancer.setBookmarkedJobs(freelancerDTO.getBookmarkedJobs());
		
		// saving the updated employee to the DataBase
		ifreelancerDao.save(freelancer);
		
		return freelancerDTO;
	}
}