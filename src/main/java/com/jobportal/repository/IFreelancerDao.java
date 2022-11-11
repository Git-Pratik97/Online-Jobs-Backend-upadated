package com.jobportal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jobportal.entity.Admin;
import com.jobportal.entity.Freelancer;

public interface IFreelancerDao extends CrudRepository<Freelancer, Integer>
{
	
}
