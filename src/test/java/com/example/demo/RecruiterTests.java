//
//package com.example.demo;
//
//import com.jobportal.OnlineJobPortalSprint1Application;
//import com.jobportal.dto.RecruiterDTO;
//import com.jobportal.entity.Recruiter;
//import com.jobportal.exception.InvalidRecruiterException;
//import com.jobportal.repository.IRecruiterDao;
//import com.jobportal.service.IRecruiterService;
//import com.jobportal.service.IRecruiterServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//@SpringBootTest(classes = OnlineJobPortalSprint1Application.class)
//public class RecruiterTests {
//
//	@Mock
//	IRecruiterDao iRecruiterDao;
//	private Recruiter recruiter;
//
//	@InjectMocks
//	IRecruiterService iRecruiterService = new IRecruiterServiceImpl();
//
//
//
//	@Test
//	void saveRecruiter() throws InvalidRecruiterException {
//		//int id=1;
//		RecruiterDTO recruiterDto = new RecruiterDTO();
//		recruiterDto.setId(1);
//		recruiterDto.setFirstName("Sri");
//		recruiterDto.setLastName("Ram");
//		Mockito.when(iRecruiterDao.save(recruiterDto.toRecruiter())).thenReturn(recruiterDto.toRecruiter());
//		RecruiterDTO actual = iRecruiterService.save(recruiterDto);
//		Assertions.assertEquals(recruiterDto, actual);
//	}
//
//	@Test
//	void findByIdTest() throws InvalidRecruiterException{
//		RecruiterDTO recruiterDTO = new RecruiterDTO();
//		recruiterDTO.setId(1);
//		recruiterDTO.setFirstName("Sri");
//		recruiterDTO.setLastName("Ram");
//		Optional<Recruiter> optional = Optional.of(recruiter);
//		Mockito.when(iRecruiterDao.findById(1)).thenReturn(optional);
//		RecruiterDTO actual = iRecruiterService.update(recruiterDTO);
//		Assertions.assertEquals(actual, recruiterDTO);
//	}
//
//	@Test
//	void failedFindByIdTest() throws InvalidRecruiterException{
//
//		Mockito.when(iRecruiterDao.findById(1)).thenReturn(null);
//		InvalidRecruiterException exception = Assertions.assertThrows(InvalidRecruiterException.class,() -> iRecruiterService.findById(1));
//		Assertions.assertEquals("Service.ADMIN_NOT_FOUND", exception.getMessage());
//
//	}
//
//
////	@Test
////	void saveRecruiter() {
////		int id=1;
////		Mockito.when(iRecruiterDao.findById((id)).thenReturn(Optional.of(recruiter)));
////		RecruiterDTO expectedRecruiterDto = new RecruiterDTO(1,"Sri","Ram",null,null,null);
////		RecruiterDTO actualAppointmentDto = iRecruiterService.save(recruiterDto);
//
//	@Test
//	void recruiterUpdateTest() throws InvalidRecruiterException{
//		RecruiterDTO recruiterDTO = new RecruiterDTO();
//		recruiterDTO.setId(1);
//		recruiterDTO.setFirstName("Sri");
//		recruiterDTO.setLastName("Ram");
//
//		RecruiterDTO returnedRecruiterDTO = new RecruiterDTO() ;
//		returnedRecruiterDTO.setId(1);
//		returnedRecruiterDTO.setFirstName("Sri");
//		returnedRecruiterDTO.setLastName("Krishna");
//		Optional <Recruiter> optional = Optional.of(returnedRecruiterDTO.toRecruiter());
//		Mockito.when(iRecruiterDao.findById(recruiterDTO.getId())).thenReturn(optional);
//		RecruiterDTO actual = iRecruiterService.update(recruiterDTO);
//		Assertions.assertEquals(recruiterDTO, actual);
//	}
//
//	@Test
//	void failedRecruiterUpdateTest() throws InvalidRecruiterException{
//		RecruiterDTO recruiterDTO = new RecruiterDTO();
//		recruiterDTO.setId(1);
//		recruiterDTO.setFirstName("Sri");
//		recruiterDTO.setLastName("Ram");
//		Mockito.when(iRecruiterDao.findById(recruiterDTO.getId())).thenReturn(null);
//		RecruiterDTO actual = iRecruiterService.update(recruiterDTO);
//		InvalidRecruiterException exception = Assertions.assertThrows(InvalidRecruiterException.class,() -> iRecruiterService.update(recruiterDTO));
//
//	}
//}
//
//
//
