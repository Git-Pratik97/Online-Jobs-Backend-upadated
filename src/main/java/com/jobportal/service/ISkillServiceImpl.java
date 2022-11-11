package com.jobportal.service;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.jobportal.dto.SkillDTO;
import com.jobportal.entity.Admin;
import com.jobportal.entity.Skill;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.InvalidSkillException;
import com.jobportal.repository.ISkillDao;

@Service(value="iSkillService")
@Transactional
public class ISkillServiceImpl implements ISkillService{
	
	@Autowired
	ISkillDao iSkillDao;
	
	@Override
	public String save(SkillDTO skillDTO) throws InvalidSkillException {
		
		Optional<Skill> optional = iSkillDao.findByName(skillDTO.getName());
		if(optional.isPresent())
			throw new InvalidSkillException("Service.SKILLNAME_ALREADY_EXISTS");
		Skill skill = new Skill();	
		skill.setId(skillDTO.getId());
		skill.setName(skillDTO.getName());
		skill.setDescription(skillDTO.getDescription());
		try {
			iSkillDao.save(skill);
		} catch(Exception e ) {
			throw new InvalidSkillException("Service.COULD_NOT_ADD_SKILL");
		}	
		return "SUCCESS";
	}
	
	public String update(SkillDTO skillDTO) throws InvalidSkillException{	
		Optional<Skill> optional = iSkillDao.findById(skillDTO.getId());
		Skill skill = optional.orElseThrow(() -> new InvalidSkillException("Service.ADMIN_NOT_FOUND"));
		skill.setName(skillDTO.getName());
		skill.setDescription(skillDTO.getDescription());
		
		Optional<Skill> optional1 = iSkillDao.findByName(skillDTO.getName());
		if((optional1.isPresent()) && !((skill.getName()).equals(skillDTO.getName())))
			throw new InvalidSkillException("Service.SKILL_ALREADY_EXISTS");
		skill.setName(skillDTO.getName());
	
		Optional<Skill> optional2 = iSkillDao.findById(skillDTO.getId());
		Skill skill1 = optional2.orElseThrow(() -> new InvalidSkillException("Service.SKILL_NOT_FOUND"));
		if(skill1.getName() == skillDTO.getName() && skill1.getDescription() == skillDTO.getDescription()) {
			return "SUCCESS";
		}
		return "FAILED";
}

	
	@Override
	public void remove(SkillDTO skillDTO) throws InvalidSkillException{	
		Skill skill = new Skill();
		skill.setId(skill.getId());
		Optional<Skill> optional = iSkillDao.findById(skillDTO.getId());
		Skill skill1 = optional.orElseThrow(() -> new InvalidSkillException("INVALID SKILLID"));
		iSkillDao.delete(skill1);		
	}
	
}
