package com.jobportal.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.jobportal.dto.SkillDTO;

@Entity
public class Skill 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Name should not be empty")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{admin.firstName.invalid}")
	private String name;
	@NotNull(message = "Description should not be empty")
	private String description;
	
	public Skill() {
		super();
	}
	
	public Skill(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	public SkillDTO toSkillDTO() {
		SkillDTO skillDTO = new SkillDTO();
		skillDTO.setName(this.name);
		skillDTO.setDescription(this.description);
		return skillDTO;
	}
}

