package com.jobportal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobportal.entity.BookmarkedJob;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.SkillExperience;
import com.jobportal.repository.IBookmarkedJobDao;
import com.jobportal.repository.IFeedbackDao;
import com.jobportal.repository.IJobApplicationDao;
import com.jobportal.repository.ISkillExperienceDao;

public class FreelancerDTO 
{
	@Autowired
	IJobApplicationDao iJobApplicationDao;
	
	@Autowired
	IFeedbackDao iFeedbackDao;
	
	@Autowired
	ISkillExperienceDao ISkillExperienceDao;
	
	@Autowired
	IBookmarkedJobDao iBookmarkedJobDao;
	
	private int freelancerDtoId;
	@NotNull(message = "{freelancer.firstName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{freelancer.firstName.invalid}")
	private String firstName;
	@NotNull(message = "{freelancer.lastName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{freelancer.lastName.invalid}")
	private String lastName;
	@NotNull(message ="recruiter.password.absent")
	private String password;
	/*private List<JobApplication> appliedJobs;
	private List<Feedback> feedbacks;
	private List<SkillExperience> skills;
	private List<BookmarkedJob> bookmarkedJobs;*/
	private List<Integer> appliedJobIds;
	private List<Integer> feedbackIds;
	private List<Integer> skillIds;
	private List<Integer> bookmarkedJobIds;
	
	// defining the getter and setter methods
	public int getId() {
		return freelancerDtoId;
	}
	public void setId(int freelancerDtoId) {
		this.freelancerDtoId = freelancerDtoId;
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
	public List<Integer> getAppliedJobIds()
	{
		return(this.appliedJobIds);
	}
	public void setAppliedJobIds(List<Integer> appliedJobIds) {
		this.appliedJobIds = appliedJobIds;
	}
	public List<Integer> getFeedbacksIds(){
		return(this.feedbackIds);
	}
	public void setFeedbacksIds(List<Integer> feedbackIds) {
		this.feedbackIds = feedbackIds;
	}
	public List<Integer> getSkillsIds(){
		return(this.skillIds);
	}
	public void setSkillsIds(List<Integer> skillIds) {
		this.skillIds = skillIds;
	}
	public List<Integer> setBookmarkedJobsIds(){
		return(this.bookmarkedJobIds);
	}
	public void setBookmarkedJobsIds(List<Integer> bookmarkedJobIds) {
		this.bookmarkedJobIds = bookmarkedJobIds;
	}
	/*public List<JobApplication> getAppliedJobs() {
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
	}*/
	
	
	// constructors with parameters
	/*public FreelancerDTO(int freelancerDtoId, String firstName, String lastName, String password, List<JobApplication> appliedJobs,
			List<Feedback> feedbacks, List<SkillExperience> skills, List<BookmarkedJob> bookmarkedJobs) {
		super();
		this.freelancerDtoId = freelancerDtoId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.appliedJobs = appliedJobs;
		this.feedbacks = feedbacks;
		this.skills = skills;
		this.bookmarkedJobs = bookmarkedJobs;
	}
	
	// default constructor
	public FreelancerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	//defining toString method
	@Override
	public String toString() {
		return "FreelancerDTO [freelancerDtoId=" + freelancerDtoId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", appliedJobs=" + appliedJobs + ", feedbacks=" + feedbacks + ", skills=" + skills
				+ ", bookmarkedJobs=" + bookmarkedJobs + "]";
	}*/
	
	public Freelancer toFreelancerEntity()
	{
		Freelancer freelancer = new Freelancer();
		freelancer.setId(this.getId());
		freelancer.setFirstName(this.getFirstName());
		freelancer.setLastName(this.getLastName());
		freelancer.setPassword(this.getPassword());
		
		List<JobApplication> jobApplicationlist = new ArrayList<>();
		appliedJobIds.forEach(jobId -> {Optional<JobApplication> optional=iJobApplicationDao.findById(jobId);
		JobApplication jobApplication=optional.orElse(null);
		jobApplicationlist.add(jobApplication);});
		freelancer.setAppliedJobs(jobApplicationlist);
		
		List<Feedback> feedbackList = new ArrayList<>();
		feedbackIds.forEach(feedbackId -> {Optional<Feedback> optional=iFeedbackDao.findById(feedbackId);
		Feedback feedback=optional.orElse(null);
		feedbackList.add(feedback);});
		freelancer.setFeedbacks(feedbackList);
		
		List<SkillExperience> skillExperienceList = new ArrayList<>();
		feedbackIds.forEach(skillExperienceId -> {Optional<SkillExperience> optional=ISkillExperienceDao.findById(skillExperienceId);
		SkillExperience skillExperience=optional.orElse(null);
		skillExperienceList.add(skillExperience);});
		freelancer.setSkills(skillExperienceList);
		
		List<BookmarkedJob> bookmarkedJobList = new ArrayList<>();
		bookmarkedJobIds.forEach(bookmarkedJobId -> {Optional<BookmarkedJob> optional=iBookmarkedJobDao.findById(bookmarkedJobId);
		BookmarkedJob bookmarkedJob=optional.orElse(null);
		bookmarkedJobList.add(bookmarkedJob);});
		freelancer.setBookmarkedJobs(bookmarkedJobList);
		
		return(freelancer);
	}
}
