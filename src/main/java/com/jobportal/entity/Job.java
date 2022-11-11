
package com.jobportal.entity;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.JobDTO;
@Entity
public class Job {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@NotNull
private int id ;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "skill_id", unique = true)
@JsonBackReference
private Skill skill;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "recruiter_id", unique = true)
@JsonBackReference
private  Recruiter postedBy;

private  LocalDate postedDate;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "freelancer_id", unique = true)
@JsonBackReference
private Freelancer awardedTo;

/*
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "job_id", unique = true)
private  List<JobApplication> jobApplications;
*/
private Boolean active;

public Job() {
	super();
	
}


public Job(int id, Skill skill, Recruiter postedBy, LocalDate postedDate, Freelancer awardedTo,
		List<JobApplication> jobApplications, Boolean active) {
	super();
	this.id = id;
	this.skill = skill;
	this.postedBy = postedBy;
	this.postedDate = postedDate;
	this.awardedTo = awardedTo;
	//this.jobApplications = jobApplications;
	this.active = active;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Skill getSkill() {
	return skill;
}
public void setSkill(Skill skill) {
	this.skill = skill;
}
public Recruiter getPostedBy() {
	return postedBy;
}
public void setPostedBy(Recruiter postedBy) {
	this.postedBy = postedBy;
}


public LocalDate getPostedDate() {
	return postedDate;
}


public void setPostedDate(LocalDate postedDate) {
	this.postedDate = postedDate;
}


public Freelancer getAwardedTo() {
	return awardedTo;
}


public void setAwardedTo(Freelancer awardedTo) {
	this.awardedTo = awardedTo;
}

/*
public List<JobApplication> getJobApplications() {
	return jobApplications;
}
public void setJobApplications(List<JobApplication> jobApplications) {
	this.jobApplications = jobApplications;
}
*/
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}




	public JobDTO toJobDTO() {
		JobDTO jobdto = new JobDTO();
	jobdto.setId(this.id);
	jobdto.setAwardedTo(this.awardedTo);
	jobdto.setActive(this.active);
//	jobdto.setJobApplications(this.jobApplications);
	jobdto.setPostedBy(this.postedBy);
	jobdto.setPostedDate(this.postedDate);
	jobdto.setSkill(this.skill);
	return jobdto;
 
    }
}