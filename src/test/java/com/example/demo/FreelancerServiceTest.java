package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobportal.OnlineJobPortalSprint1Application;
import com.jobportal.dto.FreelancerDTO;
import com.jobportal.entity.Freelancer;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.service.IFreelancerService;
import com.jobportal.service.IFreelancerServiceImpl;

@SpringBootTest(classes = OnlineJobPortalSprint1Application.class)
public class FreelancerServiceTest 
{
	@InjectMocks
	private IFreelancerService freelancerService = new IFreelancerServiceImpl();
	
	@Mock
	private IFreelancerDao freelancerDao;
	
	private Freelancer freelancer;
	
	@BeforeEach
	void Setup()
	{
		freelancer = new Freelancer();
		freelancer.setId(1);
		freelancer.setFirstName("Virat");
		freelancer.setLastName("Kohli");
		freelancer.setPassword("chaukaMaar");
		
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void authenticateFreelancerTestValidCredentials() throws InvalidFreelancerException, JobPortalException 
	{
		int freelancerId = 1;
		Mockito.when(freelancerDao.findById(freelancerId)).thenReturn(Optional.of(freelancer));
		
		FreelancerDTO expectedfreelancerDto = new FreelancerDTO(freelancerId, "Virat", "Kohli", "chaukaMaar", null, null, null, null);
		FreelancerDTO actualfreelancerDto = freelancerService.findById(freelancerId);
		Assertions.assertEquals(expectedfreelancerDto, actualfreelancerDto);
	}
	
	@Test
	public void authenticateFreelancerTestInValidCredentials() throws InvalidFreelancerException, JobPortalException 
	{
		int freelancerId = 1;
		Mockito.when(freelancerDao.findById(freelancerId)).thenReturn(Optional.empty());
		
		InvalidFreelancerException exception = Assertions.assertThrows(InvalidFreelancerException.class, ()->freelancerService.findById(freelancerId));
		Assertions.assertEquals("Freelancer.NOT_FOUND", exception.getMessage());
	}
}
