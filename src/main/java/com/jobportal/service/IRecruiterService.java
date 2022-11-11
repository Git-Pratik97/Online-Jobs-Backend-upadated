package com.jobportal.service;

import com.jobportal.dto.RecruiterDTO;
import com.jobportal.exception.InvalidRecruiterException;

public interface IRecruiterService {
	
	public String save(String firstName,String lastName,String userName,String password) throws Exception;
	public String update(int id,String firstName,String lastName,String userName,String password ) throws InvalidRecruiterException;
	public RecruiterDTO findById(int id) throws InvalidRecruiterException;

}
