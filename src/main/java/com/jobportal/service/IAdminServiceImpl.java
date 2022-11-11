
package com.jobportal.service;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.jobportal.dto.AdminDTO;
import com.jobportal.entity.Admin;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.repository.IAdminDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;

@Service(value="iAdminService")
@Transactional
public class IAdminServiceImpl implements IAdminService{
	
	
	@Autowired
	IAdminDao iAdminDao;
	
	@Autowired
	IRecruiterDao iRecruiterDao;

	@Autowired
	IFreelancerDao iFreelancerDao;
	
	
	
	@Override
	public String save(AdminDTO adminDTO) throws InvalidAdminException {
		Optional<Admin> optional = iAdminDao.findByUserName(adminDTO.getUserName());
		if(optional.isPresent())
			throw new InvalidAdminException("Service.USERNAME_ALREADY_EXISTS");
		Admin admin = adminDTO.toAdmin();
		try {
			iAdminDao.save(admin);
		} catch(OptimisticLockingFailureException e ) {
			throw new InvalidAdminException("Service.COULD_NOT_ADD_ADMIN");
		}
		
		return "SUCCESS";
	}

	@Override
	public String update(AdminDTO adminDTO) throws InvalidAdminException{
		Optional<Admin> optional = iAdminDao.findById(adminDTO.getId());
		Admin admin1 = optional.orElseThrow(() -> new InvalidAdminException("Service.ADMIN_NOT_FOUND"));
		admin1.setFirstName(adminDTO.getFirstName());
		admin1.setLastName(adminDTO.getLastName());
		admin1.setPassword(adminDTO.getPassword());
		
		Optional<Admin> optional1 = iAdminDao.findByUserName(adminDTO.getUserName());
		if((optional1.isPresent()) && !((admin1.getUserName()).equals(adminDTO.getUserName())))
			throw new InvalidAdminException("Service.USERNAME_ALREADY_EXISTS");
		admin1.setUserName(adminDTO.getUserName());
	
		Optional<Admin> optional2 = iAdminDao.findById(adminDTO.getId());
		Admin admin2 = optional2.orElseThrow(() -> new InvalidAdminException("Service.ADMIN_NOT_FOUND"));
		if(admin2.getFirstName() == adminDTO.getFirstName() && admin2.getLastName() == adminDTO.getLastName() && admin2.getUserName() == adminDTO.getUserName() && admin2.getPassword() == adminDTO.getPassword()) {
			return "SUCCESS";
		}
		return "FAILED";
	}

	@Override
	public AdminDTO findById(int Id) throws InvalidAdminException{
		Optional<Admin> optional = iAdminDao.findById(Id);
		Admin admin = optional.orElseThrow(() -> new InvalidAdminException("Service.ADMIN_NOT_FOUND"));
		AdminDTO adminDTO = new AdminDTO();
		adminDTO = admin.toAdminDTO();
		return adminDTO;
		
	}
	
	@Override
	public String deleteFreelancerById(int freelancerId) throws InvalidFreelancerException {
		Optional<Freelancer> optional = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optional.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		iFreelancerDao.delete(freelancer);
		return "SUCCESS";
	}
	
	@Override
	public String deleteRecruiterById(int recruiterId) throws InvalidRecruiterException {
		Optional<Recruiter> optional = iRecruiterDao.findById(recruiterId);
		Recruiter recruiter = optional.orElseThrow(() -> new InvalidRecruiterException("Service.FREELANCER_NOT_FOUND"));
		iRecruiterDao.delete(recruiter);
		return "SUCCESS";
	}

}

