package com.jobportal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Recruiter;
import com.jobportal.repository.IBookmarkedFreelancerDao;
import com.jobportal.repository.IFeedbackDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IJobDao;

public class RecruiterDTO {

	@Autowired
	IJobDao iJobDao;

	@Autowired
	IBookmarkedFreelancerDao iBookmarkedFreelancerDao;

	@Autowired
	IFeedbackDao iFeedbackDao;

	private int id;
	private String firstName;
	private String lastName;

	private Job postedJobs;
	private Feedback Feedbacks;
	private BookmarkedFreelancer freelancers;
	
	
	
	public RecruiterDTO() {
		super();
	}
	
	
	public RecruiterDTO(int id, String firstName, String lastName, Job postedJobs, Feedback feedbacks,
			BookmarkedFreelancer freelancers) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postedJobs = postedJobs;
		Feedbacks = feedbacks;
		this.freelancers = freelancers;
	}

	private String userName;
	private String password;
	private List<Integer> jobIds;
	private List<Integer> feedbackIds;
	private List<Integer> freelancerIds;


	
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


	public Job getPostedJobs() {
		return postedJobs;
	}


	public void setPostedJobs(Job postedJobs) {
		this.postedJobs = postedJobs;
	}


	public Feedback getFeedbacks() {
		return Feedbacks;
	}


	public void setFeedbacks(Feedback feedbacks) {
		Feedbacks = feedbacks;
	}


	public BookmarkedFreelancer getFreelancers() {
		return freelancers;
	}


	public void setFreelancers(BookmarkedFreelancer freelancers) {
		this.freelancers = freelancers;
	}


	@Override
	public String toString() {
		return "RecruiterDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postedJobs="
				+ postedJobs + ", Feedbacks=" + Feedbacks + ", freelancers=" + freelancers + "]";
	}


	public List<Integer> getJobIds() {
		return jobIds;

	}

	public void setJobIds(List<Integer> jobIds) {
		this.jobIds = jobIds;
	}

	public List<Integer> getFeedbackIds() {
		return feedbackIds;
	}

	public void setFeedbackIds(List<Integer> feedbackIds) {
		this.feedbackIds = feedbackIds;
	}

	public List<Integer> getFreelancerIds() {
		return freelancerIds;
	}

	public void setFreelancerIds(List<Integer> freelancerIds) {
		this.freelancerIds = freelancerIds;
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

	public Recruiter toRecruiter() {
		Recruiter recruiter = new Recruiter();
		recruiter.setId(this.id);
		recruiter.setFirstName(this.firstName);
		recruiter.setLastName(this.lastName);
		recruiter.setUserName(this.userName);
		recruiter.setPassword(this.password);
		List<Job> jobslist = new ArrayList<>();
	    jobIds.forEach(jobId -> {Optional<Job> optional=iJobDao.findById(jobId);
	    Job job=optional.orElse(null);
	    jobslist.add(job);});
	    
		List<BookmarkedFreelancer> bookmarkedfreelancerslist = new ArrayList<>();
	    freelancerIds.forEach(freelancerId -> {Optional<BookmarkedFreelancer> optional=iBookmarkedFreelancerDao.findById(freelancerId);
	    BookmarkedFreelancer freelancer=optional.orElse(null);
	    bookmarkedfreelancerslist.add(freelancer);});
		
	    List<Feedback> feedbacklist = new ArrayList<>();
	    feedbackIds.forEach(feedbackId -> {Optional<Feedback> optional=iFeedbackDao.findById(feedbackId);
	    Feedback feedback=optional.orElse(null);
	    feedbacklist.add(feedback);});
	    
		recruiter.setBookmarkedFreelancers(bookmarkedfreelancerslist);
		recruiter.setFeedbacks(feedbacklist);
		recruiter.setPostedJobs(jobslist);
		return recruiter;
	}
}