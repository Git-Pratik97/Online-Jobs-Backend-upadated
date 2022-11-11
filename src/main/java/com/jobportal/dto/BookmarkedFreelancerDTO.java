package com.jobportal.dto;

import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.entity.Skill;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;
import com.jobportal.repository.ISkillDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BookmarkedFreelancerDTO {

	@Autowired
	IFreelancerDao iFreelancerDao;

	@Autowired
	IRecruiterDao iRecruiterDao;

	@Autowired
	ISkillDao iSkillDao;
	private int id;
	@NotNull(message = "Customer name should not be empty")
	private int skillId;
	@NotNull(message = "Freelancer Id is missing")
	private int freelancerId;
	@NotNull(message = "BookmarkedBy Id is missing")
	private int bookmarkedById;


	public BookmarkedFreelancerDTO() {
	}

	public BookmarkedFreelancerDTO(int id, int skillId, int freelancerId, int bookmarkedById) {
		this.id = id;
		this.skillId = skillId;
		this.freelancerId = freelancerId;
		this.bookmarkedById = bookmarkedById;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(int freelancerId) {
		this.freelancerId = freelancerId;
	}

	public int getBookmarkedById() {
		return bookmarkedById;
	}

	public void setBookmarkedById(int bookmarkedById) {
		this.bookmarkedById = bookmarkedById;
	}

	public BookmarkedFreelancer toBookmarkedFreelancer() {
		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();
		bookmarkedFreelancer.setId(this.getId());

		Optional<Recruiter> optionalBookmarkedBy = iRecruiterDao.findById(this.bookmarkedById);
		Recruiter bookmarkedBy = optionalBookmarkedBy.orElse(null);
		bookmarkedFreelancer.setBookmarkedBy(bookmarkedBy);

		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(this.freelancerId);
		Freelancer freelancer = optionalFreelancer.orElse(null);
		bookmarkedFreelancer.setFreelancer(freelancer);

		Optional<Skill> optionalSkill = iSkillDao.findById(this.skillId);
		Skill skill = optionalSkill.orElse(null);
		bookmarkedFreelancer.setSkill(skill);

		return bookmarkedFreelancer;
	}
	

}

