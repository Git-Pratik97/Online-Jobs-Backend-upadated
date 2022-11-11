package com.example.demo;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobportal.OnlineJobPortalSprint1Application;
import com.jobportal.entity.Feedback;
import com.jobportal.repository.IFeedbackDao;

@SpringBootTest(classes = OnlineJobPortalSprint1Application.class)
public class FeedbackTests {
	
	@Mock
	IFeedbackDao ifeedbackDao;
	private Feedback feedback;
	

}
