package com.jobportal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.FreelancerDTO;
import com.jobportal.dto.JobApplicationDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidJobApplicationException;
import com.jobportal.exception.InvalidJobException;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IJobApplicationDao;
import com.jobportal.repository.IJobDao;
import com.jobportal.repository.IRecruiterDao;

@Service
@Transactional
public class IJobApplicationServiceImpl implements IJobApplicationService{

	@Autowired
	IJobApplicationDao iJobApplicationDao;
	@Autowired
	IJobDao iJobDao;
	@Autowired
	IFreelancerDao iFreelancerDao;
	@Autowired
	IRecruiterDao iRecruiterDao;
	
	@Override
	public String applyToJob(int jobId, String coverletter, int freelancerId) throws Exception{
		Optional<Job> optionalJob = iJobDao.findById(jobId);
		Job job = optionalJob.orElseThrow(() -> new InvalidJobException("Service.NO_SUCH_JOB"));
		
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		
		Optional<JobApplication> optional = iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(jobId, freelancerId);
		if(optional.isPresent()) {
			throw new InvalidJobApplicationException("Service.ALREADY_APPLIED");
		}
		
			
		JobApplication jobApplication = new JobApplication();
		jobApplication.setCoverLetter(coverletter);
		jobApplication.setJob(job);
		jobApplication.setAppliedDate(LocalDateTime.now());
		jobApplication.setFreelancer(freelancer);
		
		if(job.getActive() == true)
			iJobApplicationDao.save(jobApplication);
		else {
			throw new InvalidJobApplicationException("Service.JOB_NOT_ACTIVE");
		}
		
		
		Optional<JobApplication> optional1 = iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(jobId, freelancerId);
		if(optional1.isPresent()) {
			return "SUCCESS";
		}
		else
			throw new InvalidJobApplicationException("Service.APPLICATION_FAILED");
		
		
		
	}

	@Override
	public String updateJobApplication(int jobId, String coverLetter, int freelancerId) throws Exception{
		Optional<Job> optionalJob = iJobDao.findById(jobId);
		Job job = optionalJob.orElseThrow(() -> new InvalidJobException("Service.NO_SUCH_JOB"));
		
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		
		Optional<JobApplication> optional = iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(jobId, freelancerId);
		JobApplication jobApplication = optional.orElseThrow(() -> new InvalidJobApplicationException("Service.NOT_APPLIED"));
		jobApplication.setCoverLetter(coverLetter);
		
		Optional<JobApplication> optional1 = iJobApplicationDao.findById(jobApplication.getId());
		JobApplication jobApp = optional1.orElseThrow(() -> new InvalidJobApplicationException("Service.NOT_APPLIED"));
		if(jobApp.getCoverLetter().equals(coverLetter))
			return "SUCCESS";
		else
			throw new InvalidJobApplicationException("Service.UPDATE_FAILED");
		
	}

	@Override
	public String remove(int jobId, int freelancerId) throws Exception{
		Optional<Job> optionalJob = iJobDao.findById(jobId);
		Job job = optionalJob.orElseThrow(() -> new InvalidJobException("Service.NO_SUCH_JOB"));
		
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.FREELANCER_NOT_FOUND"));
		
		Optional<JobApplication> optional = iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(jobId, freelancerId);
		JobApplication jobApplication = optional.orElseThrow(() -> new InvalidJobApplicationException("Service.NOT_APPLIED"));	
		iJobApplicationDao.delete(jobApplication);
		
		Optional<JobApplication> optional1 = iJobApplicationDao.findByJobIdAndFreelancerFreelancerId(jobId, freelancerId);
		if(optional1.isPresent()) {
			throw new InvalidJobApplicationException("Service.DELETE_FAILED");
		
		}
		return "SUCCESS";
	}
	
	@Override
	public JobApplicationDTO findById(int id) throws Exception {
		Optional<JobApplication> optional = iJobApplicationDao.findById(id);
		JobApplication jobApplication = optional.orElseThrow(() -> new InvalidJobApplicationException("Service.NOT_APPLIED"));
		
		JobApplicationDTO jobApplicationDTO = jobApplication.toJobApplicationDTO();
		return jobApplicationDTO;
		
	}
	
	@Override
	public List<JobApplicationDTO> findByFreelancer(int freelancerId) throws Exception{
		List<JobApplication> jobApps = iJobApplicationDao.findByFreelancerFreelancerId(freelancerId);
		if(jobApps.isEmpty()) 
			throw new InvalidJobApplicationException("Service.NOT_APPLIED");
			List<JobApplicationDTO> jobAppDTOs = new ArrayList<>();
			
			jobApps.forEach(jobApp -> {
			JobApplicationDTO jobAppDTO = jobApp.toJobApplicationDTO();
			jobAppDTOs.add(jobAppDTO);
			});
			
			return jobAppDTOs;
		}

	@Override
	public List<JobApplicationDTO> findAll() throws InvalidJobApplicationException {
		List<JobApplication> jobApplications = (List<JobApplication>) iJobApplicationDao.findAll();
		if(jobApplications.isEmpty())
			throw new InvalidJobApplicationException("Service.NOT_FOUND");
		List<JobApplicationDTO> jobApplicationDTOs = new ArrayList<>();

		jobApplications.forEach(jobApp -> {
			JobApplicationDTO jobAppDTO = jobApp.toJobApplicationDTO();
			jobApplicationDTOs.add(jobAppDTO);
		});
		return jobApplicationDTOs;
	}
//	@Override
//	public List<JobApplicationDTO> findByRecruiter(int recruiterId) throws Exception{
//		List<JobApplication> jobApps = iJobApplicationDao.findByJobRecruiterRecruiterId(recruiterId);
//		if(jobApps.isEmpty())
//			throw new InvalidJobApplicationException("Service.NOT_FOUND");
//		List<JobApplicationDTO> jobAppDTOs = new ArrayList<>();
//
//		jobApps.forEach(jobApp -> {
//			JobApplicationDTO jobAppDTO = jobApp.toJobApplicationDTO();
//			jobAppDTOs.add(jobAppDTO);
//		});
//
//		return jobAppDTOs;
//	}
	}


