package com.jobportal.service;


public interface ISkillExperienceService {
	
	public String addSkill(int skillId, int freelancerId, Integer years) throws Exception;
	
	public String updateSkillYears(int skillId, int freelancerId, Integer years) throws Exception;
}
