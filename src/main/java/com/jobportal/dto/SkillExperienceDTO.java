package com.jobportal.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Skill;
import com.jobportal.entity.SkillExperience;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.ISkillDao;



public class SkillExperienceDTO {
	
	@Autowired
	IFreelancerDao iFreelancerDao;
	@Autowired
	ISkillDao iSkillDao;
	
	private int skillExperienceId;
	@NotNull(message="{skillExperience.skill.absent}")
	private int skillId;
	private Integer years;
	@NotNull(message="{skillExperience.freelancer.absent}")
	private int freelancerId;
	public SkillExperienceDTO() {
		super();
	}

	public SkillExperienceDTO( Integer years, int skillId, int freelancerId, int skillExperienceId) {
		super();
		this.skillExperienceId = skillExperienceId;
		this.years = years;
		this.skillId = skillId;
		this.freelancerId = freelancerId;
	}
	
	public int getSkillExperienceId() {
		return skillExperienceId;
	}
	public void setSkillExperienceId(int id) {
		this.skillExperienceId = id;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public int getFreelancerId() {
		return freelancerId;
	}
	public void setFreelancerId(int freelancerId) {
		this.freelancerId = freelancerId;
	}
	@Override
	public String toString() {
		return "SkillExperience [skillExperienceId=" + skillExperienceId + ", skill=" + skillId + ", years=" + years + ", freelancer=" + freelancerId
				+ "]";
	}
	
	public SkillExperience toSkillExperience() {
		SkillExperience skillExperience = new SkillExperience();		
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(this.freelancerId);
		Freelancer freelancer = optionalFreelancer.orElse(null);
		Optional<Skill> optionalSkill = iSkillDao.findById(this.skillId);
		Skill skill = optionalSkill.orElse(null);
		skillExperience.setSkill(skill);
		skillExperience.setYears(this.years);
		skillExperience.setFreelancer(freelancer);
		return skillExperience;
	}
}