package com.jobportal.service;

import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.entity.Skill;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.repository.IBookmarkedFreelancerDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;
import com.jobportal.repository.ISkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value="iBookmarkFreelancerService")
@Transactional
public class IBookmarkFreelancerServiceImpl implements IBookmarkFreelancerService {

	@Autowired
	private IBookmarkedFreelancerDao iBookmarkedFreelancerDao;

	@Autowired
	IFreelancerDao iFreelancerDao;

	@Autowired
	IRecruiterDao iRecruiterDao;

	@Autowired
	ISkillDao iSkillDao;

	// New Edited bookmark functions (Nov - 05)
	@Override
	public String bookmarkFreelancer(int freelancerId, int bookmarkedById,int skillId) throws InvalidBookmarkedFreelancerException {
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_FREELANCER"));

		Optional<Recruiter> optionalBookmarkedBy = iRecruiterDao.findById(bookmarkedById);
		Recruiter bookmarkedBy = optionalBookmarkedBy.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_RECRUITER"));

		Optional<Skill> optionalSkill = iSkillDao.findById(skillId);
		Skill skill = optionalSkill.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_SKILL"));

		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();
		bookmarkedFreelancer.setFreelancer(freelancer);
		bookmarkedFreelancer.setBookmarkedBy(bookmarkedBy);
		bookmarkedFreelancer.setSkill(skill);

		iBookmarkedFreelancerDao.save(bookmarkedFreelancer);
		Optional<BookmarkedFreelancer> bookmarkedFreelancerOptional = iBookmarkedFreelancerDao.findById(bookmarkedFreelancer.getId());
		if(bookmarkedFreelancerOptional.isPresent()){
			return "Service.SUCCESS";
		}
		else{
			throw new InvalidBookmarkedFreelancerException("Service.FAILED_BOOKMARK_FREELANCER");
		}

	}

// New Edited findBookmarkedFreelancerBySkill (Nov - 05)
	@Override
	public List<BookmarkedFreelancerDTO> findBookmarkedFreelancerBySkillId(int skillId) throws InvalidBookmarkedFreelancerException {
			List<BookmarkedFreelancer> bookmarkedFreelancerList = iBookmarkedFreelancerDao.findBySkillId(skillId);
			if(bookmarkedFreelancerList.isEmpty()) {
				throw new InvalidBookmarkedFreelancerException("Service.EMPTY_BOOKMARK_FREELANCER_LIST");
			}
			List<BookmarkedFreelancerDTO> bookmarkedFreelancerDTOList = new ArrayList<>();
			for(BookmarkedFreelancer bf : bookmarkedFreelancerList) {
				BookmarkedFreelancerDTO bfDTO = bf.toBookmarkedFreelancerDTO();
				bookmarkedFreelancerDTOList.add(bfDTO);
			}
			return bookmarkedFreelancerDTOList;
	}

	@Override
	public List<BookmarkedFreelancerDTO> findBookmarkedFreelancerBySkillName(String skillName) throws InvalidBookmarkedFreelancerException {
		List<BookmarkedFreelancerDTO> bookmarkedFreelancerDTOList = new ArrayList<>();
		if(skillName == null) {
			throw new InvalidBookmarkedFreelancerException("Service.ENTER_VALID_SKILL");
		}
		else {
			List<BookmarkedFreelancer> bookmarkedFreelancerList = iBookmarkedFreelancerDao.findAll();
			for(BookmarkedFreelancer bookmarkedFreelancer : bookmarkedFreelancerList) {
				Skill skill = bookmarkedFreelancer.getSkill();
				if(skill.getName().equals(skillName)){
					bookmarkedFreelancerDTOList.add(bookmarkedFreelancer.toBookmarkedFreelancerDTO());
				}
			}
		}
		if(bookmarkedFreelancerDTOList.size() == 0) {
			throw new InvalidBookmarkedFreelancerException("Service.EMPTY_LIST");
		}
		return bookmarkedFreelancerDTOList;
	}



	@Override
	public String removeBookmarkedFreelancer(int freelancerId, int bookmarkedById, int skillId) throws InvalidBookmarkedFreelancerException, InvalidRecruiterException, InvalidFreelancerException {
		Optional<Freelancer> optionalFreelancer = iFreelancerDao.findById(freelancerId);
		Freelancer freelancer = optionalFreelancer.orElseThrow(() -> new InvalidFreelancerException("Service.NO_SUCH_FREELANCER"));

		Optional<Recruiter> optionalBookmarkedBy = iRecruiterDao.findById(bookmarkedById);
		Recruiter bookmarkedBy = optionalBookmarkedBy.orElseThrow(() -> new InvalidRecruiterException("Service.NO_SUCH_RECRUITER"));

		Optional<Skill> optionalSkill = iSkillDao.findById(skillId);
		Skill skill = optionalSkill.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.NO_SUCH_SKILL"));

		Optional<BookmarkedFreelancer> optionalBookmarkedFreelancer = iBookmarkedFreelancerDao.findByFreelancerFreelancerIdAndBookmarkedByIdAndSkillId(freelancerId, bookmarkedById, skillId);
		BookmarkedFreelancer bookmarkedFreelancer = optionalBookmarkedFreelancer.orElseThrow(() ->  new InvalidBookmarkedFreelancerException("Service.NO_SUCH_BOOKMARKEDFREELANCER"));
		iBookmarkedFreelancerDao.delete(bookmarkedFreelancer);

		Optional<BookmarkedFreelancer> optionalBookmarkedFreelancer1 = iBookmarkedFreelancerDao.findByFreelancerFreelancerIdAndBookmarkedByIdAndSkillId(freelancerId, bookmarkedById, skillId);
		if(optionalBookmarkedFreelancer1.isPresent()) {
			throw new InvalidBookmarkedFreelancerException("Service.DELETE_BOOKMARKED_FREELANCER_FAILED");
		}
		else {
			return "DELETE_BOOKMARKED_FREELANCER_SUCCESS";
		}
	}

	@Override
	public BookmarkedFreelancerDTO findById(int id) throws InvalidBookmarkedFreelancerException {
		Optional<BookmarkedFreelancer> optionalBookmarkedFreelancer = iBookmarkedFreelancerDao.findById(id);
		BookmarkedFreelancer bookmarkedFreelancer = optionalBookmarkedFreelancer.orElseThrow(() -> new InvalidBookmarkedFreelancerException("Service.BOOKMARKED_FREELANCER_NOT_PRESENT"));

		BookmarkedFreelancerDTO bookmarkedFreelancerDTO = bookmarkedFreelancer.toBookmarkedFreelancerDTO();

		return bookmarkedFreelancerDTO;
	}

}
