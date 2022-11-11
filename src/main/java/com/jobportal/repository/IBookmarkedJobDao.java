package com.jobportal.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jobportal.entity.BookmarkedJob;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Recruiter;
import com.jobportal.entity.Skill;

public interface IBookmarkedJobDao extends CrudRepository<BookmarkedJob, Integer>
{
	
	@Query("DELETE FROM BookmarkedJob bj WHERE bj.job = :job AND bj.freelancer = :freelancer")
	@Modifying
	@Transactional
	Integer removeBookmarked(@Param("job") Job job,@Param("freelancer") Freelancer freelancer);
}
