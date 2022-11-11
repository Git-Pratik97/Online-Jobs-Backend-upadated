package com.jobportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.FeedbackDTO;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.exception.InvalidFeedbackException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.repository.IFeedbackDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;

@Service
@Transactional
public class IFeedbackServiceImpl implements IFeedbackService{
	
	@Autowired
	IFeedbackDao ifeedbackDao;
	@Autowired
	IRecruiterDao irecruiterDao;

	@Autowired
	IFreelancerDao ifreelancerDao;

	@Override
	public String createFeedback(int rating, String review,int recruiterId,int freelancerId)throws Exception {	
		Optional<Recruiter> optionalRecruiter = irecruiterDao.findById(recruiterId);
		Recruiter recruiter = optionalRecruiter.orElseThrow(() -> new InvalidRecruiterException("Service.RECRUITER_NOT_FOUND"));
		
		Optional<Freelancer> optionalFreelancer = ifreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		
		Optional<Feedback> optional = ifeedbackDao.findByRecruiterIdAndFreelancerFreelancerId(recruiterId, freelancerId);
		if(optional.isPresent()) {
			throw new InvalidFeedbackException("Service.ALREADY_GIVEN");
		}

		Feedback feedback = new Feedback();
		feedback.setRating(rating);
		feedback.setComment(review);
		feedback.setFreelancer(freelancer);
		feedback.setRecruiter(recruiter);
		feedback.setComment(optional.get().getComment());
		ifeedbackDao.save(feedback);
		
		Optional<Feedback> optional1 = ifeedbackDao.findByRecruiterIdAndFreelancerFreelancerId(recruiterId, freelancerId);
		if(optional1.isPresent()) {
			return "SUCCESS";
		}
		else
			throw new InvalidFeedbackException("Service.GIVING_FEEDBACK_FAILED");	
		
	}

	@Override
	public int averageRating(int freelancerId) throws InvalidFeedbackException {
		Optional<Feedback> optional = ifeedbackDao.findById(freelancerId);
		List<Integer> ratings= new ArrayList<Integer>();
		Feedback feedback = optional.orElseThrow(()->new InvalidFeedbackException("Service.NO_RATINGS_AVAILABLE"));
		ratings.add(feedback.getRating());
		int sum=ratings.stream().mapToInt(Integer::intValue).sum();
		return sum/ratings.size();
		

}

	@Override
	public List<FeedbackDTO> findFeedbacksByFreelancer(int freelancerId) throws InvalidFeedbackException {
//		Optional<Feedback> optional = ifeedbackDao.findById(freelancerId);
//		Feedback feedback = optional.orElseThrow(()->new InvalidFeedbackException("Service.NO_FEEDBACKS_FOR_THIS_FREELANCER"));
//		List<FeedbackDTO> feedbackByFreelancerDTO = new ArrayList<>();
//		FeedbackDTO feedbackDto = feedback.toFeedbackDTO();
//		feedbackByFreelancerDTO.add(feedback.toFeedbackDTO());
//		return feedbackByFreelancerDTO;
		List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
		List<Feedback> feedbacks = ifeedbackDao.findAll();
		for(Feedback feedback: feedbacks) {
			int freelancerId1 = feedback.getFreelancer().getId();
			if(freelancerId1 == freelancerId){
				feedbackDTOList.add(feedback.toFeedbackDTO());
			}
		}
		if(feedbackDTOList.size() == 0) {
			throw new InvalidFeedbackException("Service.NOT_PRESENT");
		}
		return feedbackDTOList;
	}

}
