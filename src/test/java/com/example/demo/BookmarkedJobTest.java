package com.example.demo;

import com.jobportal.repository.IBookmarkedFreelancerDao;
import com.jobportal.service.IBookmarkFreelancerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkedJobTest 
{
	@MockBean
    private IBookmarkedFreelancerDao iBookmarkedFreelancerDao;

    @Autowired
    private IBookmarkFreelancerService iBookmarkFreelancerService;

    @Test
    public void findBookmarkedFreelancerBySkillIdTest() {

    }
}
