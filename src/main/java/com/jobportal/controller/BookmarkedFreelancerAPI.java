package com.jobportal.controller;

import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.exception.InvalidFreelancerException;
import com.jobportal.exception.InvalidRecruiterException;
import com.jobportal.service.IBookmarkFreelancerService;
import com.jobportal.service.IFreelancerService;
import com.jobportal.service.IRecruiterService;
import com.jobportal.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value="/jobportal/bookmarkedfreelancer")
public class BookmarkedFreelancerAPI {
	
	@Autowired
	private IBookmarkFreelancerService iBookmarkFreelancerService;

	@Autowired
	IFreelancerService iFreelancerService;

	@Autowired
	IRecruiterService iRecruiterService;

	@Autowired
	ISkillService iSkillService;
	
	@Autowired
	Environment environment;
	
	
	@PostMapping(value="/bookmarkFreelancer/{freelancerId}/{bookmarkedById}/{skillId}")
	public ResponseEntity<String> bookmarkFreelancer(@PathVariable Integer freelancerId, @PathVariable Integer bookmarkedById, @PathVariable Integer skillId) throws InvalidBookmarkedFreelancerException{
		String status = iBookmarkFreelancerService.bookmarkFreelancer(freelancerId, bookmarkedById, skillId);
		String successMessage = environment.getProperty("API.BOOKMARKED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@GetMapping(value="/findbyid/{bookmarkedFreelancerId}")
	public ResponseEntity<BookmarkedFreelancerDTO> findById(@PathVariable int bookmarkedFreelancerId) throws InvalidBookmarkedFreelancerException{
		BookmarkedFreelancerDTO bookmarkedFreelancerDTO = iBookmarkFreelancerService.findById(bookmarkedFreelancerId);
		return new ResponseEntity<>(bookmarkedFreelancerDTO, HttpStatus.OK);
	}

	@GetMapping(value="/findbookmarkedfreelancerbyskillId/{skillId}")
	public ResponseEntity<List<BookmarkedFreelancerDTO>> findBookmarkedFreelancerBySkillId(@PathVariable Integer skillId) throws InvalidBookmarkedFreelancerException {
		List<BookmarkedFreelancerDTO> list = iBookmarkFreelancerService.findBookmarkedFreelancerBySkillId(skillId);
		return new ResponseEntity<List<BookmarkedFreelancerDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value="/findbookmarkedfreelancerbyskillName/{skillName}")
	public ResponseEntity<List<BookmarkedFreelancerDTO>> findBookmarkedFreelancerBySkillName(@PathVariable String skillName) throws InvalidBookmarkedFreelancerException {
		List<BookmarkedFreelancerDTO> list = iBookmarkFreelancerService.findBookmarkedFreelancerBySkillName(skillName);
		return new ResponseEntity<List<BookmarkedFreelancerDTO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/removeBookmarkedFreelancer/{freelancerId}/{bookmarkedById}/{skillId}")
	public ResponseEntity<String> removeBookmarkedFreelancer(@PathVariable Integer freelancerId, @PathVariable Integer bookmarkedById, @PathVariable Integer skillId) throws InvalidBookmarkedFreelancerException, InvalidFreelancerException, InvalidRecruiterException {
		iBookmarkFreelancerService.removeBookmarkedFreelancer(freelancerId, bookmarkedById, skillId);
		String successMessage = environment.getProperty("API.REMOVED_SUCCESSFULLY");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
}
