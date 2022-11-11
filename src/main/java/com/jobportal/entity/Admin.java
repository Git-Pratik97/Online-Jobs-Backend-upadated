package com.jobportal.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.jobportal.dto.AdminDTO;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message = "{admin.firstName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{admin.firstName.invalid}")
	private String firstName;
	@NotNull(message ="{admin.lastName.absent}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{admin.lastName.invalid}")
	private String lastName;
	@NotNull(message ="admin.userName.absent")
	private String userName;
	@NotNull(message ="{admin.password.absent}")
	private String password;
	

	public Admin() {
		super();
	}
	
	public int getId() {
		return adminId;
	}
	public void setId(int id) {
		this.adminId = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public AdminDTO toAdminDTO() {
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(this.adminId);
		adminDTO.setFirstName(this.firstName);
		adminDTO.setLastName(this.lastName);
		adminDTO.setUserName(this.userName);
		adminDTO.setPassword(this.password);
		return adminDTO;
	}
}
