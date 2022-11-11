//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import com.jobportal.OnlineJobPortalSprint1Application;
//import com.jobportal.dto.AdminDTO;
//import com.jobportal.dto.FreelancerDTO;
//import com.jobportal.dto.JobDTO;
//import com.jobportal.dto.RecruiterDTO;
//import com.jobportal.dto.SkillDTO;
//import com.jobportal.entity.Freelancer;
//import com.jobportal.entity.Job;
//import com.jobportal.entity.JobApplication;
//import com.jobportal.entity.Recruiter;
//import com.jobportal.entity.Skill;
//import com.jobportal.exception.InvalidJobException;
//import com.jobportal.repository.IJobDao;
//import com.jobportal.service.IJobService;
//import com.jobportal.service.IJobServiceImpl;
//
//@SpringBootTest(classes=OnlineJobPortalSprint1Application.class)
//
//public class JobTests {
//
//	@InjectMocks
//	IJobService ijobservice = new IJobServiceImpl();
//
//	@Mock
//    IJobDao ijobdao;
//
//	@Test
//	void postJobValidTests() throws InvalidJobException{
//		SkillDTO skillDTO = new SkillDTO();
//		RecruiterDTO recruiterdto = new RecruiterDTO();
//		recruiterdto.setId(1);
//		skillDTO.setId(1);
//		Skill skill = new Skill();
//		skill.setId(skillDTO.getId());
//		Recruiter recruit = new Recruiter();
//		recruit.setId(recruiterdto.getId());
//		JobDTO jobdto = new JobDTO();
//        List<JobApplication> jobApplications = jobdto.getJobApplications();
//        Freelancer freelancer = new Freelancer();
//        Recruiter recruiter = new Recruiter();
//        jobdto.setId(jobdto.getId());
//        jobdto.setSkill(skill);
//        jobdto.setPostedBy(recruiter);
//		jobdto.setPostedDate(LocalDate.now());
//        jobdto.setAwardedTo(freelancer);
//        jobdto.setActive(true);
//        jobdto.setJobApplications(jobApplications);
//
//           when(ijobdao.save(jobdto.toJob())).thenReturn(jobdto.toJob());
//           JobDTO actual = ijobservice.postjob(skillDTO, recruiterdto);
//		 assertEquals(actual,jobdto);
//
//
//
//
////		JobDTO jobdto = new JobDTO();
////		Skill skill = new Skill();
////		Recruiter recruiter = new Recruiter();
////		Freelancer freelancer = new Freelancer();
////		skill.setId(0);
////		skill.setName(null);
////		skill.setDescription(null);
////		recruiter.setId(0);
////		recruiter.setFirstName(null);
////		recruiter.setLastName(null);
////		recruiter.setFeedbacks(null);
////		freelancer.setId(0);
////		freelancer.setFirstName(null);
////		freelancer.setLastName(null);
////		freelancer.setPassword(null);
////		freelancer.setAppliedJobs(null);
////		freelancer.setFeedbacks(null);
////		freelancer.setSkills(null);
////		freelancer.setBookmarkedJobs(null);
////		jobdto.setJobApplications(null);
////		jobdto.setSkill(skill);
////		jobdto.setAwardedTo(freelancer);
////		jobdto.setPostedBy(recruiter);
////		SkillDTO skilldto = new SkillDTO();
////		RecruiterDTO recruiterdto = new RecruiterDTO();
////		 jobdto.setId(0);
////		 jobdto.setActive(true);
////		 jobdto.setPostedDate(LocalDate.now());
////		 when(ijobdao.save(jobdto.toJob())).thenReturn(jobdto.toJob());
////		 //assertEquals(jobdto,ijobservice.postjob(skilldto, recruiterdto));
////		 assertEquals(jobdto,jobdto);
//
//
//
//	}
////	@Test
////	void findByIdTests() throws InvalidJobException{
////		JobDTO jobdto = new JobDTO();
////		 jobdto.setId(2);
////		 jobdto.setActive(true);
////		 jobdto.setPostedDate(LocalDate.now());
////		 jobdto.setJobApplications(null);
////		 jobdto.setAwardedTo(null);
////		 Skill skill = new Skill();
////		 skill.setId(201);
////		 jobdto.setSkill(skill);
//		 //Recruiter recruit = new Recruiter();
//		// recruit.setId(301);
//		// jobdto.setPostedBy(null);
////		 int id =2;
////		Mockito.when(ijobdao.findById(id)).thenReturn(Optional.of(Stream.of(new Job())));
////		 assertEquals(1,ijobservice.findById(2).size());
//
////		 Optional<Job> optional = Optional.of(jobdto.toJob());
////		 Mockito.when(ijobdao.findById(2)).thenReturn(optional);
////			JobDTO actual = ijobservice.findById(2);
////			Assertions.assertEquals(actual, jobdto);
//	//}
////	@Test
////	void closeTest () throws InvalidJobException{
////		JobDTO jobdto = new JobDTO();
////		 jobdto.setId(1);
////		 jobdto.setActive(true);
////		 jobdto.setPostedDate(LocalDate.now());
////
////
////	}
////	@Test
////	void findJobsBySkillTests() throws InvalidJobException{
////		JobDTO jobdto = new JobDTO();
////		 jobdto.setId(1);
////		 jobdto.setActive(true);
////		 jobdto.setPostedDate(LocalDate.now());
//
//	//}
//
//
//}
