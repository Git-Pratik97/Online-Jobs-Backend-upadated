package com.jobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jobportal.dto.FeedbackDTO;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.JobApplication;

public interface IFeedbackDao extends JpaRepository<Feedback, Integer> {
	@Query("SELECT fb FROM Feedback fb WHERE fb.freelancer=:freelancer")
	List<Feedback> findFeedbacksByFreelancer(@Param("freelancer") Freelancer freelancer);
	
	Optional<Feedback> findByRecruiterIdAndFreelancerFreelancerId (int recruiterId, int  freelancerId);


}
