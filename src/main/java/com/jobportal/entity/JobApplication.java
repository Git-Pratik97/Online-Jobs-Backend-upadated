package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jobportal.dto.JobApplicationDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class JobApplication {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
 	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="job_id")
	private Job job;
 	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="freelancer_id")
 	@JsonBackReference
	private Freelancer freelancer;
	private LocalDateTime appliedDate;
	private String coverLetter;
	
	public JobApplication() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
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
	public Freelancer getFreelancer() {
		return freelancer;
	}
	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	
	public JobApplicationDTO toJobApplicationDTO() {
		JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
		jobApplicationDTO.setId(this.id);
		jobApplicationDTO.setJobId(this.job.getId());
		jobApplicationDTO.setAppliedDate(this.appliedDate);
		jobApplicationDTO.setCoverLetter(this.coverLetter);
		jobApplicationDTO.setFreelancerId(this.freelancer.getId());
		return jobApplicationDTO;
	}
	
}