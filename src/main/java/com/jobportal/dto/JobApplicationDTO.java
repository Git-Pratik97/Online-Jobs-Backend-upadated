package com.jobportal.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IJobDao;

public class JobApplicationDTO {

	@Autowired
	IJobDao iJobDao;
	
	@Autowired
	IFreelancerDao iFreelancerDao;
	
	private int jobApplicationId;
	@NotNull(message="{jobApplication.job.absent}")
	private int jobId;
	@NotNull(message="{jobApplication.freelancer.absent}")
	private int freelancerId;
	private LocalDateTime appliedDate;
	@NotNull(message="{jobApplication.coverLetter.absent}")
	private String coverLetter;
	
	public JobApplicationDTO() {
		super();
	}
	
	public JobApplicationDTO(int jobApplicationId, int jobId, int freelancerId, LocalDateTime appliedDate, String coverLetter) {
		super();
		this.jobApplicationId = jobApplicationId;
		this.jobId = jobId;
		this.freelancerId = freelancerId;
		this.appliedDate = appliedDate;
		this.coverLetter = coverLetter;
	}
	public int getId() {
		return jobApplicationId;
	}
	public void setId(int id) {
		this.jobApplicationId = id;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getFreelancerId() {
		return freelancerId;
	}
	public void setFreelancerId(int freelancerId) {
		this.freelancerId = freelancerId;
	}
	public LocalDateTime getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDateTime appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	
	public JobApplication toJobApplication() {
		JobApplication jobApplication = new JobApplication();
		jobApplication.setId(this.jobApplicationId);
		Optional<Job> optional = iJobDao.findById(this.jobId);
		Job job = optional.orElse(null);
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(this.freelancerId);
		Freelancer freelancer = optionalFreelancer.orElse(null);
		jobApplication.setJob(job);
		jobApplication.setFreelancer(freelancer);
		jobApplication.setAppliedDate(this.appliedDate);
		jobApplication.setCoverLetter(this.coverLetter);
		return jobApplication;
	}

	@Override
	public String toString() {
		return "JobApplicationDTO [iJobDao=" + iJobDao + ", iFreelancerDao=" + iFreelancerDao + ", jobApplicationId="
				+ jobApplicationId + ", jobId=" + jobId + ", freelancerId=" + freelancerId + ", appliedDate="
				+ appliedDate + ", coverLetter=" + coverLetter + "]";
	}
	
	

}