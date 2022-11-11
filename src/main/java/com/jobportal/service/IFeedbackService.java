package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.FeedbackDTO;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.exception.InvalidFeedbackException;


public interface IFeedbackService {
	
	public String createFeedback(int rating,String review,int recruiterId,int freelancerId) throws  Exception;
	public int averageRating(int freelancerId) throws InvalidFeedbackException;
	public List<FeedbackDTO> findFeedbacksByFreelancer(int freelancerId) throws InvalidFeedbackException;

}
