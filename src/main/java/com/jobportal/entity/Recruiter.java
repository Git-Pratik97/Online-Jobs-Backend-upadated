package com.jobportal.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jobportal.dto.AdminDTO;
import com.jobportal.dto.RecruiterDTO;

@Entity
public class Recruiter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "{recruiter.firstName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{recruiter.firstName.invalid}")
	private String firstName;
	@NotNull(message = "{recruiter.lastName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{recruiter.lastName.invalid}")
	private String lastName;
	@NotNull(message ="recruiter.userName.absent")
	private String userName;
	@NotNull(message ="recruiter.password.absent")
	private String password;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "postedBy")
	// @JoinColumn(name="job_id")
	@JsonManagedReference
	private List<Job> postedJobs;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recruiter")
	// @JoinColumn(name="feedback_id")
	@JsonManagedReference
	private List<Feedback> feedbacks;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bookmarkedBy")
	// @JoinColumn(name="bookmarkedfreelancer_id")
	@JsonManagedReference
	private List<BookmarkedFreelancer> bookmarkedfreelancers;

	public Recruiter() {
	}

	public Recruiter(int id, String firstName, String lastName, String userName, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Job> getPostedJobs() {
		return postedJobs;
	}

	public void setPostedJobs(List<Job> postedJobs) {
		this.postedJobs = postedJobs;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<BookmarkedFreelancer> getFreelancers() {
		return bookmarkedfreelancers;
	}

	public void setBookmarkedFreelancers(List<BookmarkedFreelancer> bookmarkedfreelancers) {
		this.bookmarkedfreelancers = bookmarkedfreelancers;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RecruiterDTO toRecruiterDTO() {
		RecruiterDTO recruiterDTO = new RecruiterDTO();
		recruiterDTO.setId(id);
		recruiterDTO.setFirstName(this.firstName);
		recruiterDTO.setLastName(this.lastName);
		recruiterDTO.setUserName(this.userName);
		recruiterDTO.setPassword(this.password);
		List<Integer> feedbackslist = new ArrayList<>();
		feedbacks.forEach(feedback -> feedbackslist.add(feedback.getId()));
		recruiterDTO.setFeedbackIds(feedbackslist);
		List<Integer> freelancerlist = new ArrayList<>();
		bookmarkedfreelancers.forEach(freelancer -> freelancerlist.add(freelancer.getId()));
		recruiterDTO.setFreelancerIds(freelancerlist);
		List<Integer> jobslist = new ArrayList<>();
		postedJobs.forEach(job -> jobslist.add(job.getId()));
		recruiterDTO.setFeedbackIds(feedbackslist);
		recruiterDTO.setJobIds(jobslist);
		return recruiterDTO;
	}

}
