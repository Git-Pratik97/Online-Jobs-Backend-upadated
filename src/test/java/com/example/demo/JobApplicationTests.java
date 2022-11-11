//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.jobportal.service.IJobApplicationService;
//import com.jobportal.service.IJobApplicationServiceImpl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.jobportal.OnlineJobPortalSprint1Application;
//import com.jobportal.dto.JobApplicationDTO;
//import com.jobportal.dto.JobDTO;
//import com.jobportal.entity.Freelancer;
//import com.jobportal.entity.Job;
//import com.jobportal.entity.JobApplication;
//import com.jobportal.entity.Recruiter;
//import com.jobportal.entity.Skill;
//import com.jobportal.exception.InvalidJobApplicationException;
//import com.jobportal.repository.IFreelancerDao;
//import com.jobportal.repository.IJobApplicationDao;
//import com.jobportal.repository.IJobDao;
//
//@SpringBootTest(classes=OnlineJobPortalSprint1Application.class)
//class JobApplicationTests {
//
//	@Mock
//	IJobApplicationDao iJobApplicationDao;
//
//	@Mock
//	IJobDao iJobDao;
//
//	@Mock
//	IFreelancerDao iFreelancerDao;
//
//	@InjectMocks
//	IJobApplicationService iJobApplicationService = new IJobApplicationServiceImpl();
//
//	Skill skill;
//	Recruiter recruiter;
//	JobDTO jobDTO;
//	Freelancer freelancer;
//	Job job;
//	JobApplication jobApplication1;
//	JobApplication jobApplication2;
//	JobApplication jobApplication3;
//	@BeforeEach
//	void setup() {
//
//		skill = new Skill();
//		skill.setId(11);
//		recruiter = new Recruiter();
//		recruiter.setId(101);
//
//		freelancer = new Freelancer();
//		freelancer.setId(1);
//		jobDTO = new JobDTO();
//		jobDTO.setId(1);
//		jobDTO.setSkill(skill);
//		jobDTO.setPostedBy(recruiter);
//		jobDTO.setPostedDate(LocalDate.now());
//		jobDTO.setAwardedTo(null);
//		jobDTO.setJobApplications(null);
//		jobDTO.setActive(true);
//		job = jobDTO.toJob();
//
//		List<JobApplication> applications = new ArrayList<>();
//		freelancer.setAppliedJobs(applications);
//
//		JobApplication jobApplication1 = new JobApplication();
//		jobApplication1 = new JobApplication();
//		jobApplication1.setAppliedDate(LocalDateTime.now());
//		jobApplication1.setId(1);
//		jobApplication1.setCoverLetter("Applicant 1");
//		jobApplication1.setJob(jobDTO.toJob());
//
//		jobApplication2 = new JobApplication();
//		jobApplication2.setAppliedDate(LocalDateTime.now());
//		jobApplication2.setId(2);
//		jobApplication2.setCoverLetter("Applicant 2");
//		jobApplication2.setJob(jobDTO.toJob());
//
//		jobApplication3 = new JobApplication();
//		jobApplication3.setAppliedDate(LocalDateTime.now());
//		jobApplication3.setId(1);
//		jobApplication3.setCoverLetter("Applicant 1");
//		jobApplication3.setJob(jobDTO.toJob());
//	}
//
//
//
//	void applyToJobTest2() throws InvalidJobApplicationException{
//		JobApplication jobApplication = new JobApplication(3, job.toJobDTO(), LocalDateTime.now(),"Applicant 4");
//		Mockito.when(iJobApplicationDao.save(jobApplication1)).thenReturn(jobApplication1);
//		JobApplicationDTO actual =iJobApplicationService.applyToJob(job.toJobDTO(), "Applicant 1",freelancer.toFreelancerDTO());
//		InvalidJobApplication = jobApplication.toJobApplicationDTO();
//		Assertions.assertThrows(actual, jobApplicationDTO);
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	void applyToJobTest() throws Exception{
//
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.empty()).thenReturn(Optional.of(jobApplication));
//		Mockito.when(iJobApplicationDao.save(jobApplication)).thenReturn(jobApplication);
//		String actual =iJobApplicationService.applyToJob(job.getId(), "Applicant 4",freelancer.getId());
//		Assertions.assertEquals("SUCCESS", actual);
//	}
//
//	@Test
//	void failedApplyToJobTest() throws Exception{
//
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.of(jobApplication));
//		Mockito.when(iJobApplicationDao.save(jobApplication)).thenReturn(jobApplication);
//		InvalidJobApplicationException exception = Assertions.assertThrows(InvalidJobApplicationException.class, () -> iJobApplicationService.applyToJob(1, "Applicant 4", 1));
//		Assertions.assertEquals("Service.ALREADY_APPLIED", exception.getMessage());
//	}
//
//	@Test
//	void updateJobApplicationTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		JobApplication updatedJobApplication = new JobApplication();
//		updatedJobApplication.setId(1);
//		updatedJobApplication.setJob(job);
//		updatedJobApplication.setFreelancer(freelancer);
//		updatedJobApplication.setAppliedDate(LocalDateTime.now());
//		updatedJobApplication.setCoverLetter("updated coverletter for Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.of(jobApplication));
//		Mockito.when(iJobApplicationDao.findById(1)).thenReturn(Optional.of(updatedJobApplication));
//		String status =iJobApplicationService.updateJobApplication(job.getId(), "updated coverletter for Applicant 4",freelancer.getId());
//		Assertions.assertEquals("SUCCESS", status);
//	}
//
//	@Test
//	void failedUpdateJobApplicationTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		JobApplication jobApplication1 = new JobApplication();
//		jobApplication1.setId(1);
//		jobApplication1.setJob(job);
//		jobApplication1.setFreelancer(freelancer);
//		jobApplication1.setAppliedDate(LocalDateTime.now());
//		jobApplication1.setCoverLetter("Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.of(jobApplication));
//		Mockito.when(iJobApplicationDao.findById(1)).thenReturn(Optional.of(jobApplication1));
//		InvalidJobApplicationException exception =Assertions.assertThrows(InvalidJobApplicationException.class,() -> iJobApplicationService.updateJobApplication(job.getId(), "updated coverletter for Applicant 4",freelancer.getId()));
//		Assertions.assertEquals("Service.UPDATE_FAILED", exception.getMessage());
//	}
//
//	@Test
//	void removeJobApplicationTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		JobApplication jobApplication1 = new JobApplication();
//		jobApplication1.setId(1);
//		jobApplication1.setJob(job);
//		jobApplication1.setFreelancer(freelancer);
//		jobApplication1.setAppliedDate(LocalDateTime.now());
//		jobApplication1.setCoverLetter("Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.of(jobApplication)).thenReturn(Optional.empty());
//		String status =iJobApplicationService.remove(job.getId(),freelancer.getId());
//		Assertions.assertEquals("SUCCESS", status);
//	}
//
//	@Test
//	void failedRemoveJobApplicationTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		JobApplication jobApplication1 = new JobApplication();
//		jobApplication1.setId(1);
//		jobApplication1.setJob(job);
//		jobApplication1.setFreelancer(freelancer);
//		jobApplication1.setAppliedDate(LocalDateTime.now());
//		jobApplication1.setCoverLetter("Applicant 4");
//		Mockito.when(iJobDao.findById(job.getId())).thenReturn(Optional.of(job));
//		Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
//		Mockito.when(iJobApplicationDao.findByJobIdAndFreelancerId(job.getId(), freelancer.getId())).thenReturn(Optional.of(jobApplication));
//		InvalidJobApplicationException exception =Assertions.assertThrows(InvalidJobApplicationException.class,() -> iJobApplicationService.remove(job.getId(),freelancer.getId()));
//		Assertions.assertEquals("Service.DELETE_FAILED", exception.getMessage());
//	}
//
//	@Test
//	void findByIdTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setJob(job);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		Mockito.when(iJobApplicationDao.findById(1)).thenReturn(Optional.of(jobApplication));
//		JobApplicationDTO jobApplicationDTO = jobApplication.toJobApplicationDTO();
//		JobApplicationDTO actual = iJobApplicationService.findById(1);
//		Assertions.assertEquals(jobApplicationDTO.toString(), actual.toString());
//	}
//
//	/*@Test
//	void applyToJobTest() throws InvalidJobApplicationException{
//		JobApplication jobApplication = new JobApplication(3, job.toJobDTO(), LocalDateTime.now(),"Applicant 4");
//		Mockito.when(iJobApplicationDao.save(jobApplication)).thenReturn(jobApplication);
//		JobApplicationDTO actual =iJobApplicationService.applyToJob(job.toJobDTO(), "Applicant 4",freelancer.toFreelancerDTO());
//		JobApplicationDTO jobApplicationDTO = jobApplication.toJobApplicationDTO();
//		Assertions.assertEquals(actual, jobApplicationDTO);
//	}*/
//
//	@Test
//	void failedFindByIdTest() throws Exception{
//		JobApplication jobApplication = new JobApplication();
//		jobApplication.setId(1);
//		jobApplication.setJob(job);
//		jobApplication.setFreelancer(freelancer);
//		jobApplication.setAppliedDate(LocalDateTime.now());
//		jobApplication.setCoverLetter("Applicant 4");
//		Mockito.when(iJobApplicationDao.findById(1)).thenReturn(Optional.empty());
//		InvalidJobApplicationException exception =Assertions.assertThrows(InvalidJobApplicationException.class,() -> iJobApplicationService.findById(1));
//		Assertions.assertEquals("Service.NOT_APPLIED", exception.getMessage());
//	}
//
//}
