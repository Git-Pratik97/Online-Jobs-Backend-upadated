package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.JobDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.dto.SkillDTO;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidJobException;

public interface IJobService {

	String postjob(int freelancerId, int recruiterId, int skillId) throws InvalidJobException, InvalidBookmarkedFreelancerException;

	public JobDTO findById(int id) throws InvalidJobException;

	List<JobDTO> findJobsBySkillName(String name) throws InvalidJobException;

	//	public List<JobDTO> findJobsBySkill(SkillDTO skillDTO)throws InvalidJobException;
    public void close(int id)throws InvalidJobException;

	void awardJob(int jobId, int freelancerId) throws InvalidJobException, InvalidFreelancerException;
}
