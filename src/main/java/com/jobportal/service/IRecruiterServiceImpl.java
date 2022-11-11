package com.jobportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.jobportal.dto.RecruiterDTO;
import com.jobportal.entity.Admin;
import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.Recruiter;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidFeedbackException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.IFeedbackDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IJobDao;
import com.jobportal.repository.IRecruiterDao;


@Service(value="iRecruiterService")
@Transactional
public class IRecruiterServiceImpl implements IRecruiterService{
	
	@Autowired
	IRecruiterDao irecruiterDao;
	@Autowired
	IJobDao iJobDao;
	@Autowired
	IFreelancerDao iFreelancerDao;
	@Autowired
	IFeedbackDao iFeedbackDao;
	
	@Override
	public String save(String firstName,String lastName,String userName,String password) throws Exception {
		
		
		Recruiter recruiter = new Recruiter();
		recruiter.setFirstName(firstName);
		recruiter.setLastName(lastName);
		recruiter.setUserName(userName);
		recruiter.setPassword(password);
		List<Job> jobslist = new ArrayList<>();
		List<BookmarkedFreelancer> bookmarkedfreelancerslist = new ArrayList<>();
		List<Feedback> feedbackslist = new ArrayList<>();

		recruiter.setPostedJobs(jobslist);
		recruiter.setBookmarkedFreelancers(bookmarkedfreelancerslist);
		recruiter.setFeedbacks(feedbackslist);
		Optional<Recruiter> optional = irecruiterDao.findByFirstNameAndLastName(firstName,lastName);
		if(optional.isPresent()) {
			throw new InvalidRecruiterException("Service.RECRUITER_ALREADY_ADDED");
		}

		irecruiterDao.save(recruiter);
		Optional<Recruiter> optional1 = irecruiterDao.findByFirstNameAndLastName(firstName,lastName);
		
		if(optional1.isPresent()) {
			return "SUCCESS";
		}
		else {
			throw new InvalidJobApplicationException("Service.INSERTION_FAILED");
		}

		
		
	}
	@Override
	public RecruiterDTO findById(int id) throws InvalidRecruiterException{
		Optional<Recruiter> optional = irecruiterDao.findById(id);
		Recruiter recruiter = optional.orElseThrow(() -> new InvalidRecruiterException("Service.RECRUITER_NOT_FOUND_WITH_ID"));
		RecruiterDTO recruiterDTO = recruiter.toRecruiterDTO();
		return recruiterDTO;
		
	}
	@Override
	public String update(int id,String firstName,String lastName,String userName,String password) throws InvalidRecruiterException{
		Optional<Recruiter> optional1 = irecruiterDao.findById(id);
		Recruiter recruiter = optional1.orElseThrow(()-> new InvalidRecruiterException("Service.RECRUITER_NOT_FOUND"));
			recruiter.setUserName(userName);
			recruiter.setPassword(password);
			recruiter.setFirstName(firstName);
			recruiter.setLastName(lastName);
		
		Optional<Recruiter> optional2 = irecruiterDao.findByUserName(userName);
		if((optional2.isPresent()) && !((recruiter.getUserName()).equals(userName)))
			throw new InvalidRecruiterException("Service.USERNAME_ALREADY_EXISTS");
		recruiter.setUserName(userName);
		
		Optional<Recruiter> optional3 = irecruiterDao.findById(id);
		Recruiter recruiter2 = optional3.orElseThrow(() -> new InvalidRecruiterException("Service.RECRUITER_NOT_FOUND"));
		if(recruiter2.getFirstName() == firstName && recruiter2.getLastName() == lastName && recruiter2.getUserName() == userName && recruiter2.getPassword() == password) {
			return "SUCCESS";
		}
		return "FAILED";
		
	}


}
