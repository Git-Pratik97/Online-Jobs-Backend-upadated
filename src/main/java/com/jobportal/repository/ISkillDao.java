package com.jobportal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.jobportal.entity.Skill;

public interface ISkillDao extends CrudRepository<Skill, Integer>{
	Optional<Skill> findByName(String Name);

}
