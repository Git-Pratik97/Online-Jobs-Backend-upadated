
package com.jobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jobportal.entity.JobApplication;

public interface IJobApplicationDao extends CrudRepository<JobApplication, Integer>{
	Optional<JobApplication> findByJobIdAndFreelancerFreelancerId (int jobId, int  freelancerId);

	List<JobApplication> findByFreelancerFreelancerId(int freelancerId);

//	List<JobApplication> findByJobRecruiterRecruiterId(int recruiterId);
}

