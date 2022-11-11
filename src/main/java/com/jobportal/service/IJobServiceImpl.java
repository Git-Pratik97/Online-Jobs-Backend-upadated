package com.jobportal.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;


import com.jobportal.entity.*;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;
import com.jobportal.repository.ISkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobportal.dto.JobDTO;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.repository.IJobDao;

@Service(value="iJobsService")
@Transactional
public class IJobServiceImpl implements IJobService{
	
    @Autowired
    private IJobDao iJobDao;

	@Autowired
	private IFreelancerDao iFreelancerDao;

	@Autowired
	private IRecruiterDao iRecruiterDao;

	@Autowired
	private ISkillDao iSkillDao;
    
	@Override
	public String postjob(int freelancerId, int recruiterId, int skillId) throws InvalidJobException, InvalidBookmarkedFreelancerException {
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_FREELANCER"));

		Optional<Recruiter> optionalBookmarkedBy = iRecruiterDao.findById(recruiterId);
		Recruiter recruiter = optionalBookmarkedBy.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_RECRUITER"));

		Optional<Skill> optionalSkill = iSkillDao.findById(skillId);
		Skill skill = optionalSkill.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_SKILL"));

		Job job = new Job();
		job.setActive(true);
		job.setPostedBy(recruiter);
		job.setAwardedTo(freelancer);
		job.setSkill(skill);
		job.setPostedDate(LocalDate.now());

		iJobDao.save(job);
		Optional<Job> jobOptional = iJobDao.findById(job.getId());
		if(jobOptional.isPresent()){
			return "SUCCESS";
		}
		else{
			throw new InvalidJobException("Service.FAILED_POST_JOB");
		}
    }

	@Override
	public JobDTO findById(int id) throws InvalidJobException {
		Optional<Job> optional = iJobDao.findById(id);
		Job job = optional.orElseThrow(() -> new InvalidJobException("Service.NO_JOB_FOUND"));
		
		JobDTO jobDto = new JobDTO();
		jobDto.setId(job.getId());
		jobDto.setSkill(job.getSkill()); 
		jobDto.setActive(job.getActive());
		jobDto.setAwardedTo(jobDto.getAwardedTo());
		jobDto.setPostedDate(job.getPostedDate());
//		jobDto.setJobApplications(job.getJobApplications());
		jobDto.setPostedBy(jobDto.getPostedBy());
		
		return jobDto;
		
	}

	@Override
	public List<JobDTO> findJobsBySkillName(String name) throws InvalidJobException {
		List<JobDTO> JobDTOBySkill = new ArrayList<>();
		if(name == null) {
			throw new InvalidJobException("Service.ENTER_VALID_SKILL");
		}
		else {
			List<Job> joblist =(List<Job>) iJobDao.findAll();
			for(Job job : joblist) {
				Skill skill1 =job.getSkill();
				if(skill1.getName().equals(name)) {
					JobDTOBySkill.add(job.toJobDTO());
				}
			}
		}
		if(JobDTOBySkill.size()==0) {
			throw new InvalidJobException("Service.NOT_PRESENT");
		}
		return JobDTOBySkill;
	}
//	@Override
//	public void close(Long id) {
//		if (jobdao.existsById(id)) {
//			Job job = jobdao.findById(id).get();
//			job.setActive(false);
//			jobdao.save(job);
//		} else {
//			throw new InvalidJobException();
//		}
//	}

	@Override
	public void close(int id) throws InvalidJobException {

		if (iJobDao.existsById(id)) {
			Job job = iJobDao.findById(id).get();
			job.setActive(false);
			iJobDao.save(job);
		} else {
			throw new InvalidJobException("Service.NO_SUCH_JOB");
		}
	}

	@Override
	public void awardJob(int jobId,int freelancerId) throws InvalidJobException, InvalidFreelancerException {

		Optional<Job> optional = iJobDao.findById(jobId);
		Job job = optional.orElseThrow(() -> new InvalidJobException("Service.NO_SUCH_JOB"));

		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));

		job.setAwardedTo(freelancer);
		iJobDao.save(job);

	}

}
