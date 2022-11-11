package com.example.demo;

import com.jobportal.OnlineJobPortalSprint1Application;
import com.jobportal.dto.BookmarkedFreelancerDTO;
import com.jobportal.entity.BookmarkedFreelancer;
import com.jobportal.entity.Freelancer;
import com.jobportal.entity.Recruiter;
import com.jobportal.entity.Skill;
import com.jobportal.exception.InvalidBookmarkedFreelancerException;
import com.jobportal.repository.IBookmarkedFreelancerDao;
import com.jobportal.repository.IFreelancerDao;
import com.jobportal.repository.IRecruiterDao;
import com.jobportal.repository.ISkillDao;
import com.jobportal.service.IBookmarkFreelancerService;
import com.jobportal.service.IBookmarkFreelancerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes= OnlineJobPortalSprint1Application.class)
public class BookmarkedFreelanceTests {

    @InjectMocks
    private IBookmarkFreelancerServiceImpl iBookmarkFreelancerService;

    @Mock
    private IBookmarkedFreelancerDao iBookmarkedFreelancerDao;

    @Mock
    private IFreelancerDao iFreelancerDao;

    @Mock
    private IRecruiterDao iRecruiterDao;

    @Mock
    private ISkillDao iSkillDao;

    Freelancer freelancer;
    Skill skill;
    Recruiter bookmarkedBy;
    BookmarkedFreelancer bookmarkedFreelancer;

    @BeforeEach
    public void setUp() {
        freelancer = new Freelancer(101, "name", "lastname", "temp");
        skill = new Skill(301, "name", "temp");
        bookmarkedBy = new Recruiter(201, "name", "last_name", "password", "username");
        bookmarkedFreelancer = new BookmarkedFreelancer(901, bookmarkedBy, skill, freelancer);
    }

    @Test
    void bookmarkFreelancerTest() throws InvalidBookmarkedFreelancerException {
//        BookmarkedFreelancerDTO bookmarkedFreelancerDTO = new BookmarkedFreelancerDTO();
//        bookmarkedFreelancerDTO.setId(101);
//        bookmarkedFreelancerDTO.setBookmarkedById(301);
//        bookmarkedFreelancerDTO.setFreelancerId(201);
//        bookmarkedFreelancerDTO.setSkillId(401);
//        Mockito.when(iBookmarkedFreelancerDao.save(bookmarkedFreelancerDTO.toBookmarkedFreelancer()))
//                .thenReturn(bookmarkedFreelancerDTO.toBookmarkedFreelancer());
//        String status = iBookmarkFreelancerService.bookmarkFreelancer(201, 301, 401);
//        Assertions.assertEquals(status, "SUCCESS");

        Mockito.when(iFreelancerDao.findById(freelancer.getId())).thenReturn(Optional.of(freelancer));
        Mockito.when(iRecruiterDao.findById(bookmarkedBy.getId())).thenReturn(Optional.of(bookmarkedBy));
        Mockito.when(iSkillDao.findById(skill.getId())).thenReturn(Optional.of(skill));
        Mockito.when(iBookmarkedFreelancerDao.findById(bookmarkedFreelancer.getId())).thenReturn(Optional.of(bookmarkedFreelancer));
//        Mockito.when(iBookmarkedFreelancerDao.save(ArgumentMatchers.any(BookmarkedFreelancer.class))).thenReturn(bookmarkedFreelancer);
        Mockito.when(iBookmarkedFreelancerDao.save(bookmarkedFreelancer)).thenReturn(bookmarkedFreelancer);
        String status = iBookmarkFreelancerService.bookmarkFreelancer(freelancer.getId(), bookmarkedBy.getId(), skill.getId());
        Assertions.assertEquals("SUCCESS", status);
    }

}
