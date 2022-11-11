package com.jobportal.service;

import java.util.List;

import com.jobportal.dto.BookmarkedJobDTO;
import com.jobportal.entity.BookmarkedJob;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.Skill;
import com.jobportal.exception.JobPortalException;

public interface IBookmarkJobService 
{
	public BookmarkedJob bookmarkJob(Job job,Freelancer freelancer)throws JobPortalException;
	public void removeBookmark(Job job,Freelancer freelancer)throws JobPortalException;
	public List<BookmarkedJob> findBookmarkedJobsBySkill(Skill skill,Freelancer freelancer)throws JobPortalException;
	public BookmarkedJobDTO findById(Integer id)throws JobPortalException;
}
