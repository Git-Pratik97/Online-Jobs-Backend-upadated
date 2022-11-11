package com.jobportal.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jobportal.dto.SkillExperienceDTO;

@Entity
public class SkillExperience {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "skill_id", unique = true)
	@NotNull(message="{skillExperience.job.absent}")
	private Skill skill;
	@NotNull(message = "Experience Years should not be empty")
	private Integer years;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "freelancer_id", unique = true)
	@NotNull(message="{skillExperience.freelancer.absent}")
	@JsonBackReference
	private Freelancer freelancer;
	
	public SkillExperience () {
		super();
	}
	
	public SkillExperience(int id, Skill skill, Integer years, Freelancer freelancer) {
		super();
		this.id = id;
		this.skill = skill;
		this.years = years;
		this.freelancer = freelancer;
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
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public Freelancer getFreelancer() {
		return freelancer;
	}
	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	
	
	
@Override
public String toString() {
	return "SkillExperience [id=" + id + ", skill=" + skill + ", years=" + years + ", freelancer=" + freelancer
			+ "]";
}		

public SkillExperienceDTO toSkillExperienceDTO() {
	SkillExperienceDTO skillExperienceDTO = new SkillExperienceDTO();
	skillExperienceDTO.setSkillId(this.skill.getId());
	skillExperienceDTO.setYears(this.years);
	skillExperienceDTO.setFreelancerId(this.freelancer.getId());
	return skillExperienceDTO;
}


}
