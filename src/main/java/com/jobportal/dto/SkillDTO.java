package com.jobportal.dto;

import com.jobportal.entity.Skill;

public class SkillDTO {

	private int id;
	private String name;
	private String description;
	
	
	public SkillDTO() {
		super();
	}
	
	public SkillDTO(String name, String description) {
		super();
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
	
	public Skill toSkill() {
		Skill skill = new Skill();
		skill.setName(this.name);
		skill.setDescription(this.description);
		return skill;
	}


}
