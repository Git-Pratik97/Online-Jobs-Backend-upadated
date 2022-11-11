package com.jobportal.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;

public class FeedbackDTO {
	
	@Autowired
	IRecruiterDao irecruiterDao;
	
	@Autowired
	IFreelancerDao ifreelancerDao;
	
	private int feedbackId;
	private Integer rating;
	private String comment;
	private int recruiterId;
	private int freelancerId;
	
	
	
	public FeedbackDTO() {
		super();
	}
	
	
	public FeedbackDTO(int feedbackId, Integer rating, String comment, int recruiterId, int freelancerId) {
		super();
		this.feedbackId = feedbackId;
		this.rating = rating;
		this.comment = comment;
		this.recruiterId = recruiterId;
		this.freelancerId = freelancerId;
	}


	public int getId() {
		return feedbackId;
	}
	public void setId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getRecruiterId() {
		return recruiterId;
	}


	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}


	public int getFreelancerId() {
		return freelancerId;
	}


	public void setFreelancerId(int freelancerId) {
		this.freelancerId = freelancerId;
	}


	
	public Feedback toFeedback() {
		Feedback feedback = new Feedback();
		feedback.setId(this.feedbackId);
		feedback.setRating(this.rating);
		feedback.setComment(this.comment);
		
		Optional<Recruiter> optional = irecruiterDao.findById(this.recruiterId);
		Recruiter recruiter = optional.orElse(null);
		feedback.setRecruiter(recruiter);
		
		Optional<Freelancer> optionalFreelancer = ifreelancerDao.findById(this.freelancerId);
		Freelancer freelancer = optionalFreelancer.orElse(null);
		feedback.setFreelancer(freelancer);
		
		return feedback;
	}

}
