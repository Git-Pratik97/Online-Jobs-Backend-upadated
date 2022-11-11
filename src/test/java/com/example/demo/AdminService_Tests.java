package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobportal.OnlineJobPortalSprint1Application;
import com.jobportal.dto.AdminDTO;
import com.jobportal.dto.RecruiterDTO;
import com.jobportal.entity.Admin;
import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Feedback;
import com.jobportal.entity.Job;
import com.jobportal.entity.Recruiter;
import com.jobportal.exception.InvalidAdminException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.repository.IAdminDao;
import com.jobportal.repository.IRecruiterDao;
import com.jobportal.service.IAdminService;
import com.jobportal.service.IAdminServiceImpl;
import com.jobportal.service.IRecruiterService;
import com.jobportal.service.IRecruiterServiceImpl;

@SpringBootTest(classes=OnlineJobPortalSprint1Application.class)
class AdminService_Tests {

	@InjectMocks
	private IAdminService iAdminService = new IAdminServiceImpl();
	
	@Mock
	private IAdminDao iAdminDao;
	
	
	
	
	@Test
	void adminAddTest() throws InvalidAdminException{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(1);
		adminDTO.setFirstName("John");
		adminDTO.setLastName("Jack");
		adminDTO.setUserName("JJuser");
		adminDTO.setPassword("123");
		Mockito.when(iAdminDao.save(adminDTO.toAdmin())).thenReturn(adminDTO.toAdmin());
		String status = iAdminService.save(adminDTO);
		Assertions.assertEquals(status, "SUCCESS");
		
	}
	
	@Test
	void failedAdminAddTest() throws InvalidAdminException{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(1);
		adminDTO.setFirstName("John");
		adminDTO.setLastName("Jack");
		adminDTO.setUserName("JJuser");
		adminDTO.setPassword("123");
		Mockito.when(iAdminDao.findByUserName(adminDTO.getUserName())).thenReturn(Optional.of((adminDTO.toAdmin())));
		InvalidAdminException exception = Assertions.assertThrows(InvalidAdminException.class,() -> iAdminService.save(adminDTO));
		Assertions.assertEquals("Service.USERNAME_ALREADY_EXISTS", exception.getMessage());
		
	}
	
	@Test
	void adminUpdateTest() throws InvalidAdminException{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(1);
		adminDTO.setFirstName("John");
		adminDTO.setLastName("Jack");
		adminDTO.setUserName("JJuser");
		adminDTO.setPassword("123");
		
		AdminDTO returnedAdminDTO = new AdminDTO() ;
		returnedAdminDTO.setId(1);
		returnedAdminDTO.setFirstName("John");
		returnedAdminDTO.setLastName("Jack");
		returnedAdminDTO.setUserName("JJuser");
		returnedAdminDTO.setPassword("2345");
		Optional <Admin> optional = Optional.of(returnedAdminDTO.toAdmin());
		Mockito.when(iAdminDao.findById(adminDTO.getId())).thenReturn(optional);
		String status = iAdminService.update(adminDTO);
		Assertions.assertEquals(status, "SUCCESS");
		
	}
	
	@Test
	void failedAdminUpdateTest() throws InvalidAdminException{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(1);
		adminDTO.setFirstName("John");
		adminDTO.setLastName("Jack");
		adminDTO.setUserName("JJuser");
		adminDTO.setPassword("123");
		Optional <Admin> optional = Optional.empty();
		Mockito.when(iAdminDao.findById(1)).thenReturn(optional);
		InvalidAdminException exception = Assertions.assertThrows(InvalidAdminException.class,() -> iAdminService.update(adminDTO));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", exception.getMessage());
		
	}
	
	@Test
	void findByIdTest() throws InvalidAdminException{
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setId(1);
		adminDTO.setFirstName("John");
		adminDTO.setLastName("Jack");
		adminDTO.setUserName("JJuser");
		adminDTO.setPassword("123");
		Mockito.when(iAdminDao.findById(1)).thenReturn(Optional.of(adminDTO.toAdmin()));
		AdminDTO actual = iAdminService.findById(1);
		Assertions.assertEquals(adminDTO.toString(), actual.toString());
		
	}
	
	@Test
	void failedFindByIdTest() throws InvalidAdminException{
		Optional<Admin> optional = Optional.empty();
		Mockito.when(iAdminDao.findById(1)).thenReturn(optional);
		InvalidAdminException exception = Assertions.assertThrows(InvalidAdminException.class,() -> iAdminService.findById(1));
		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", exception.getMessage());
		
	}
	
}
