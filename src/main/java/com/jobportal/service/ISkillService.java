package com.jobportal.service;

import com.jobportal.dto.SkillDTO;
import com.jobportal.exception.InvalidSkillException;


public interface ISkillService {
	
	public String save(SkillDTO skillDTO) throws InvalidSkillException;
	public String update(SkillDTO skillDTO) throws InvalidSkillException;
	public void remove(SkillDTO skillDTO) throws InvalidSkillException;

}
