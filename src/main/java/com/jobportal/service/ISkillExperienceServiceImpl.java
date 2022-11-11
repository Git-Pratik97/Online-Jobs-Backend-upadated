package com.jobportal.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.SkillDTO;
import com.jobportal.dto.SkillExperienceDTO;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.Skill;
import com.jobportal.entity.SkillExperience;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.exception.InvalidSkillException;
import com.jobportal.exception.InvalidSkillExperienceException;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.ISkillDao;
import com.jobportal.repository.ISkillExperienceDao;

@Service
@Transactional
public class ISkillExperienceServiceImpl implements ISkillExperienceService{
	
	@Autowired
	ISkillExperienceDao iSkillExperienceDao;
	@Autowired
	IFreelancerDao iFreelancerDao;
	@Autowired
	ISkillDao iSkillDao;
	SkillExperience skillExperienceDto;
	
	
//	@Override			
//	public SkillExperienceDTO addSkill(SkillDTO skillDTO, FreelancerDTO freelancerDTO, int ExperienceYears) throws InvalidSkillExperienceException{
//		Skill skill = new Skill();
//		skill.setId(skillDTO.getId());	
//		Freelancer freelancer = new Freelancer();
//		freelancer.setId(freelancerDTO.getId());
//		List<SkillExperience> skills=freelancerDTO.getSkills();
//		
//		int idskill =0;
//		int count = 0;
//		for(SkillExperience skillexp : skills) {			
//			if(skillexp.getSkill().equals(skill)) {
//				count++;
//				idskill = skillexp.getId();
//				break;
//			}
//		}
//		if(count > 0) {
//			throw new InvalidSkillExperienceException("Service.ALREADY_APPLIED");
//		}
//		SkillExperience skillExperience = new SkillExperience();
//		skillExperience.setSkill(skill);
//		skillExperience.setFreelancer(freelancer);
//		skillExperience.setYears(ExperienceYears);
//		iSkillExperienceDao.save(skillExperience);
//		
//		SkillExperienceDTO skillExperienceDTO = skillExperience.toSkillExperienceDTO();	
//		
//		return skillExperienceDTO;
//	}
	
	@Override
	public String addSkill(int skillId, int freelancerId, Integer years) throws Exception{
//		
//		Optional<SkillExperience> optional = iSkillExperienceDao.findBySkillIdAndFreelancerId(skillId, freelancerId);
//		if(optional.isPresent()) {
//			throw new InvalidSkillExperienceException("Service.ALREADY_ADDED");
		Optional<Skill> optionalSkill = iSkillDao.findById(skillId);
		Skill skill = optionalSkill.orElseThrow(() -> new InvalidSkillException("Service.NO_SUCH_SKILL"));
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		Optional<SkillExperience> optional = iSkillExperienceDao.findBySkillIdAndFreelancerFreelancerId(skillId, freelancerId);
		if(optional.isPresent()) {
			throw new InvalidSkillExperienceException("Service.ALREADY_ADDEED");
		}
		
		SkillExperience skillExperience = new SkillExperience();
		skillExperience.setYears(years);			
		skillExperience.setFreelancer(freelancer);
		skillExperience.setSkill(skill);
		iSkillExperienceDao.save(skillExperience);
		
		Optional<SkillExperience> optional1 = iSkillExperienceDao.findBySkillIdAndFreelancerFreelancerId(skillId, freelancerId);
		if(optional1.isPresent()) {
			return "SUCCESS";
		}
		else
			throw new InvalidSkillExperienceException("Service.NOT_FOUND");
		}
			
	@Override
	public String updateSkillYears(int skillId, int freelancerId, Integer years) throws Exception{
		Optional<Skill> optionalSkill = iSkillDao.findById(skillId);
		Skill skill = optionalSkill.orElseThrow(() -> new InvalidSkillException("Service.NO_SUCH_SKILL"));
		
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		
		Optional<SkillExperience> optional = iSkillExperienceDao.findBySkillIdAndFreelancerFreelancerId(skillId, freelancerId);
		SkillExperience skillExperience = optional.orElseThrow(() -> new InvalidSkillExperienceException("Service.NOT_APPLIED"));
		skillExperience.setYears(years);
		
		Optional<SkillExperience> optional1 = iSkillExperienceDao.findById(skillExperience.getId());
		SkillExperience skillExp = optional1.orElseThrow(() -> new InvalidSkillExperienceException("Service.NOT_APPLIED"));
		if(skillExp.getYears().equals(years))
			return "SUCCESS";
		else
			throw new InvalidSkillExperienceException("Service.UPDATE_FAILED");
		
	}
}
			

