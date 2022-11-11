package com.jobportal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jobportal.dto.FeedbackDTO;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;
	private Integer rating;
	private String comment;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recruiter_id", unique = false)
	@JsonBackReference
	private Recruiter recruiter;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "freelancer_id", unique = false)
	@JsonBackReference
	private Freelancer freelancer;

	public Feedback(int feedbackId, Integer rating, String comment, Recruiter recruiter, Freelancer freelancer) {
		super();
		this.feedbackId = feedbackId;
		this.rating = rating;
		this.comment = comment;
		this.recruiter = recruiter;
		this.freelancer = freelancer;
	}

	public Feedback() {
		super();
	}

	public int getId() {
		return feedbackId;
	}

	public void setId(int id) {
		this.feedbackId = id;
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

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}

	public FeedbackDTO toFeedbackDTO() {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setId(this.feedbackId);
		feedbackDTO.setRating(this.rating);
		feedbackDTO.setComment(this.comment);
		feedbackDTO.setRecruiterId(this.recruiter.getId());
		feedbackDTO.setFreelancerId(this.freelancer.getId());
		return feedbackDTO;
	}

}
