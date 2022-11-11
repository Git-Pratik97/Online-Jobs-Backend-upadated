package com.jobportal.service;

import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;

import java.util.List;


public interface IBookmarkFreelancerService {

//    List<BookmarkedFreelancer> findBookmarkedFreelancerBySkill(int skillId) throws InvalidBookmarkedFreelancerException;
    String bookmarkFreelancer(int freelancerId, int skillId, int bookmarkedById) throws InvalidBookmarkedFreelancerException;

    // New Edited findBookmarkedFreelancerBySkill (Nov - 05)
    List<BookmarkedFreelancerDTO> findBookmarkedFreelancerBySkillId(int skillId) throws InvalidBookmarkedFreelancerException;

    List<BookmarkedFreelancerDTO> findBookmarkedFreelancerBySkillName(String skillName) throws InvalidBookmarkedFreelancerException;

    String removeBookmarkedFreelancer(int freelancerId, int bookmarkedById, int skillId) throws InvalidBookmarkedFreelancerException, InvalidRecruiterException, InvalidFreelancerException;

    BookmarkedFreelancerDTO findById(int id) throws InvalidBookmarkedFreelancerException;
}
