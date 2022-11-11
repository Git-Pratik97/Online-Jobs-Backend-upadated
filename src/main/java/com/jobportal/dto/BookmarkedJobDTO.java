package com.jobportal.dto;

import com.jobportal.entity.BookmarkedJob;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Skill;

public class BookmarkedJobDTO 
{
	
	private Integer bookmarkedJobDtoId;
	private Skill skill;
	private Job job;
	private Freelancer freelancer;
	
	// defining the getter and setter methods
	public Integer getId() {
		return bookmarkedJobDtoId;
	}
	public void setId(Integer bookmarkedJobDtoId) {
		this.bookmarkedJobDtoId = bookmarkedJobDtoId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Freelancer getFreelancer() {
		return freelancer;
	}
	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	
	//defining toString method
	@Override
	public String toString() {
		return "BookmarkedJobDTO [bookmarkedJobDtoId=" + bookmarkedJobDtoId + ", skill=" + skill + ", job=" + job + ", freelancer=" + freelancer + "]";
	}
	
	public BookmarkedJob toBookmarkedJobEntity()
	{
		BookmarkedJob bookmarkedJob = new BookmarkedJob();
		bookmarkedJob.setId(this.getId());
		bookmarkedJob.setSkill(this.getSkill());
		bookmarkedJob.setJob(this.getJob());
		bookmarkedJob.setFreelancer(this.getFreelancer());
		
		return(bookmarkedJob);
	}
}
