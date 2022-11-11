
package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.jobportal.entity.Job;

import com.jobportal.entity.Skill;

public interface IJobDao extends CrudRepository<Job, Integer>{
	@Query("SELECT j FROM Job j WHERE j.skill = :skill")
	List<Job> findJobBySkill(@Param("skill") Skill skill );
  
}
