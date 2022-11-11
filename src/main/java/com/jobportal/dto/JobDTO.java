package com.jobportal.dto;

import java.time.LocalDate;
import java.util.List;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Recruiter;
import com.jobportal.entity.Skill;
import com.jobportal.entity.JobApplication;
import org.aspectj.bridge.IMessage;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

public class JobDTO {
	private int id;
	@NotNull(message = "Skill should not be empty")
	private Skill skill;
	@NotNull(message = "Recruiter details are missing")
	private  Recruiter postedBy;
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private  LocalDate postedDate;
	@NotNull(message = "Freelancer details are missing")
	private Freelancer awardedTo;
	private  List<JobApplication> jobApplications;
	private Boolean active;

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
	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "JobDTO [id=" + id + ", skill=" + skill + ", postedBy=" + postedBy + ", postedDate=" + postedDate
				+ ", awardedTo=" + awardedTo + ", jobApplications=" + jobApplications + ", active=" + active + "]";
	}
    public Job toJob() {
		Job job = new Job();
	job.setId(this.id);
	job.setAwardedTo(this.awardedTo);
	job.setActive(this.active);
//	job.setJobApplications(this.jobApplications);
	job.setPostedBy(this.postedBy);
	job.setPostedDate(this.postedDate);
	job.setSkill(this.skill);
	return job;
    	
    }
}
