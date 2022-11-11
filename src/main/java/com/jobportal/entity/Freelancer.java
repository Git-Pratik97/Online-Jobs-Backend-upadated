package com.jobportal.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jobportal.dto.FreelancerDTO;

@Entity
public class Freelancer 
{
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)

	private int freelancerId;
	private String firstName;
	private String lastName;
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="freelancer")
	//@JoinColumn(name="freelancer_id")
	@JsonManagedReference
	private List<JobApplication> appliedJobs;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="freelancer")
	//@JoinColumn(name="freelancer_id")
	@JsonManagedReference
	private List<Feedback> feedbacks;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="freelancer")
	//@JoinColumn(name="freelancer_id")	
	@JsonManagedReference
	private List<SkillExperience> skills;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="freelancer")
	//@JoinColumn(name="freelancer_id")
	@JsonManagedReference
	private List<BookmarkedJob> bookmarkedJobs;
	
	// defining default and parameterized constructors
	/*public Freelancer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Freelancer(int freelancerId, String firstName, String lastName, String password, List<JobApplication> appliedJobs,
			List<Feedback> feedbacks, List<SkillExperience> skills, List<BookmarkedJob> bookmarkedJobs) {
		super();
		this.freelancerId = freelancerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.appliedJobs = appliedJobs;
		this.feedbacks = feedbacks;
		this.skills = skills;
		this.bookmarkedJobs = bookmarkedJobs;
	}*/

	public Freelancer(int freelancerId, String firstName, String lastName, String password) {
		this.freelancerId = freelancerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	// defining the getter and setter methods
	public int getId() {
		return freelancerId;
	}
	public void setId(Integer freelancerId) {
		this.freelancerId = freelancerId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<JobApplication> getAppliedJobs() {
		return appliedJobs;
	}
	public void setAppliedJobs(List<JobApplication> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	public List<SkillExperience> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillExperience> skills) {
		this.skills = skills;
	}
	public List<BookmarkedJob> getBookmarkedJobs() {
		return bookmarkedJobs;
	}
	public void setBookmarkedJobs(List<BookmarkedJob> bookmarkedJobs) {
		this.bookmarkedJobs = bookmarkedJobs;
	}
	
	// defining hashcode and equals method
	/*@Override
	public int hashCode() {
		return Objects.hash(appliedJobs, bookmarkedJobs, feedbacks, firstName, freelancerId, lastName, password, skills);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Freelancer other = (Freelancer) obj;
		return Objects.equals(appliedJobs, other.appliedJobs) && Objects.equals(bookmarkedJobs, other.bookmarkedJobs)
				&& Objects.equals(feedbacks, other.feedbacks) && Objects.equals(firstName, other.firstName)
				&& freelancerId == other.freelancerId && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(skills, other.skills);
	}
	
	//defining toString method
	@Override
	public String toString() {
		return "Freelancer [freelancerId=" + freelancerId + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", appliedJobs=" + appliedJobs + ", feedbacks=" + feedbacks + ", skills=" + skills
				+ ", bookmarkedJobs=" + bookmarkedJobs + "]";
	}*/
	
	public FreelancerDTO toFreelancerDto()
	{
		FreelancerDTO freelancerDto = new FreelancerDTO();
		freelancerDto.setId(this.getId());
		freelancerDto.setFirstName(this.getFirstName());
		freelancerDto.setLastName(this.getLastName());
		freelancerDto.setPassword(this.getPassword());
		/*List<Integer> feedbackslist = new ArrayList<>();
		feedbacks.forEach(feedback -> feedbackslist.add(feedback.getId()));
		recruiterDTO.setFeedbackIds(feedbackslist);*/
		
		List<Integer> appliedJobList = new ArrayList<>();
		appliedJobs.forEach(appliedJob -> appliedJobList.add(appliedJob.getId()));
		freelancerDto.setAppliedJobIds(appliedJobList);
		
		List<Integer> feedbacksList = new ArrayList<>();
		feedbacks.forEach(feedback -> feedbacksList.add(feedback.getId()));
		freelancerDto.setFeedbacksIds(feedbacksList);
		
		List<Integer> skillsList = new ArrayList<>();
		skills.forEach(skillList -> skillsList.add(skillList.getId()));
		freelancerDto.setSkillsIds(skillsList);
		
		List<Integer> bookmarkedJobsList = new ArrayList<>();
		bookmarkedJobs.forEach(bookmarkedJobList -> bookmarkedJobsList.add(bookmarkedJobList.getId()));
		freelancerDto.setBookmarkedJobsIds(bookmarkedJobsList);
		
		return(freelancerDto);
	}
}
