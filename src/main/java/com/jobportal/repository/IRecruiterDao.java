package com.jobportal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jobportal.entity.JobApplication;
import com.jobportal.entity.Recruiter;

public interface IRecruiterDao extends CrudRepository<Recruiter, Integer>{
	Optional<Recruiter> findByFirstNameAndLastName (String firstName,String lastName);
	Optional<Recruiter> findByUserName(String userName);
}
