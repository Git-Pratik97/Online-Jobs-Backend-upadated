
package com.jobportal.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.jobportal.entity.Admin;

public class AdminDTO {
	
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
	
	public AdminDTO() {
		super();
	}
	

	public AdminDTO(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}


	public Integer getId() {
		return adminId;
	}
	public void setId(Integer id) {
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

	
	
	public Admin toAdmin() {
		Admin admin = new Admin();
		admin.setId(this.getId());
		admin.setFirstName(this.firstName);
		admin.setLastName(this.lastName);
		admin.setUserName(this.userName);
		admin.setPassword(this.password);
		return admin;
	}





	@Override
	public String toString() {
		return "AdminDTO [id=" + adminId + ", adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
