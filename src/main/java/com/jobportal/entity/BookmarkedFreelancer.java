package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jobportal.dto.BookmarkedFreelancerDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class BookmarkedFreelancer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Entering a skill is mandatory")
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "skill_id")
	private Skill skill;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "freelancer_id")
	@NotNull(message="Entering a freelancer is mandatory")
	private Freelancer freelancer;
	@NotNull(message="Entering a skill is mandatory")
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "bookmarkedby_id")
	@JsonBackReference
	private Recruiter bookmarkedBy;
	
	public BookmarkedFreelancer() {
		super();
	}
	
	public BookmarkedFreelancer(int id, Recruiter bookmarkedBy, Skill skill, Freelancer freelancer) {
		super();
		this.id = id;
		this.skill = skill;
		this.freelancer = freelancer;
		this.bookmarkedBy = bookmarkedBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Freelancer getFreelancer() {
		return freelancer;
	}
	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}
	public Recruiter getBookmarkedBy() {
		return bookmarkedBy;
	}
	public void setBookmarkedBy(Recruiter bookmarkedBy) {
		this.bookmarkedBy = bookmarkedBy;
	}
	
	public BookmarkedFreelancerDTO toBookmarkedFreelancerDTO() {
		BookmarkedFreelancerDTO bookmarkedFreelancerDTO = new BookmarkedFreelancerDTO();
		bookmarkedFreelancerDTO.setId(this.getId());
		bookmarkedFreelancerDTO.setBookmarkedById(this.bookmarkedBy.getId());
		bookmarkedFreelancerDTO.setFreelancerId(this.freelancer.getId());
		bookmarkedFreelancerDTO.setSkillId(this.skill.getId());
		return bookmarkedFreelancerDTO;
	}

}

