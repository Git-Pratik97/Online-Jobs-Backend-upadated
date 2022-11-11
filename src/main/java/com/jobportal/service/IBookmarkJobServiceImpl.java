package com.jobportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.jobportal.dto.BookmarkedJobDTO;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.entity.BookmarkedJob;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Skill;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.IBookmarkedFreelancerDao;
import com.jobportal.repository.IBookmarkedJobDao;

@Service(value = "iBookmarkJobService")
@Transactional
public class IBookmarkJobServiceImpl implements IBookmarkJobService
{
	@Autowired
	IBookmarkedJobDao iBookMarkedJobDao;

	@Override
	public BookmarkedJob bookmarkJob(Job job, Freelancer freelancer) throws JobPortalException 
	{
		BookmarkedJob bookmarkedJob = new BookmarkedJob();
		bookmarkedJob.setJob(job);
		bookmarkedJob.setFreelancer(freelancer);
		bookmarkedJob.setSkill(job.getSkill());
		bookmarkedJob.setId(job.getId());
		
		iBookMarkedJobDao.save(bookmarkedJob);
		return bookmarkedJob;
	}

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void removeBookmark(Job job, Freelancer freelancer) throws JobPortalException 
	{
		Optional<BookmarkedJob> op1 = iBookMarkedJobDao.findById(job.getId());
		BookmarkedJob bookmarkedJob = op1.orElseThrow(() -> new JobPortalException("Service.BOOKMARKED_JOB_NOT_FOUND"));
		
		Query query = (Query) entityManager.createQuery("DELETE FROM Job j,Freelancer f where j.id = f.id"); 
		//extract job.id and free.id the write the query to delete
		//iBookMarkedJobDao.deleteById(job.getId());
		//iBookMarkedJobDao.del
	}

	@Override
	public List<BookmarkedJob> findBookmarkedJobsBySkill(Skill skill, Freelancer freelancer) throws JobPortalException 
	{
		//freelancer
		List<Freelancer> list = new ArrayList<>();
		for(int i = 0; i < list.size(); i++)//change here
		{
			if(skill.getName().equals(freelancer.getBookmarkedJobs().toString()))
			{
				return(freelancer.getBookmarkedJobs());
			}
		}
		return null;
	}

	@Override
	public BookmarkedJobDTO findById(Integer id) throws JobPortalException 
	{
		Optional<BookmarkedJob> op1 = iBookMarkedJobDao.findById(id);
		BookmarkedJob bookmarkedJob = op1.orElseThrow(() -> new JobPortalException("Service.BOOKMARKED_JOB_NOT_FOUND"));
		
		//adding data to the DTO by id
		BookmarkedJobDTO bookmarkedJobDTO = new BookmarkedJobDTO();
		bookmarkedJobDTO.setId(bookmarkedJob.getId());
		bookmarkedJobDTO.setSkill(bookmarkedJob.getSkill());
		bookmarkedJobDTO.setJob(bookmarkedJob.getJob());
		bookmarkedJobDTO.setFreelancer(bookmarkedJob.getFreelancer());
		
		return bookmarkedJobDTO;
	}

//	@Override
//	public BookmarkedJob findById(Long id) throws JobPortalException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
