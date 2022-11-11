//package com.example.demo;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//
//import com.jobportal.OnlineJobPortalSprint1Application;
//import com.jobportal.dto.SkillDTO;
//import com.jobportal.entity.Skill;
//import com.jobportal.exception.InvalidSkillException;
//import com.jobportal.repository.ISkillDao;
//import com.jobportal.service.ISkillService;
//import com.jobportal.service.ISkillServiceImpl;
//
//
//@SpringBootTest(classes=OnlineJobPortalSprint1Application.class)
//@ComponentScan("com.jobportal.service.ISkillService")
//@AutoConfigureMockMvc
//
//public class SkillServiceTests {
//	@Mock
//	private ISkillDao iSkillDao;
//	private Skill skill;
//
//
//	@InjectMocks
//	private ISkillService iSkillService = new ISkillServiceImpl();
//
//
//	@BeforeEach
//	void setup2() {
//		skill = new Skill();
//		skill.setId(1);
//		skill.setName("Priya");
//		skill.setDescription("WonderWomen");
//
//
//	}
//
//	@Test
//	void saveSkill() throws InvalidSkillException{
//		SkillDTO skillDTO = new SkillDTO();
//		skillDTO.setId(1);
//		skillDTO.setName("Priya");
//		skillDTO.setDescription("WonderWomen");
//		Mockito.when(iSkillDao.save(skillDTO.toSkill())).thenReturn(skillDTO.toSkill());
//		SkillDTO actualSkillDTO = iSkillService.save(skillDTO);
//		Assertions.assertEquals(actualSkillDTO, skillDTO);
//	}
//
//	@Test
//	void updateSkillTest() throws InvalidSkillException{
//		SkillDTO skillDTO = new SkillDTO();
//		skillDTO.setId(1);
//		skillDTO.setName("Priya");
//		skillDTO.setDescription("WonderWomen");
//		SkillDTO returnedSkillDTO = new SkillDTO();
//		returnedSkillDTO.setId(1);
//		returnedSkillDTO.setName("Priya");
//		returnedSkillDTO.setDescription("BeautyBeast");
//		Optional <Skill> optional = Optional.of(returnedSkillDTO.toSkill());
//		Mockito.when(iSkillDao.findById(skillDTO.getId())).thenReturn(optional);
//		SkillDTO actual = iSkillService.update(skillDTO);
//		Assertions.assertEquals(skillDTO, actual);
//	}
//
//	@Test
//	void updatefailedSkillTest() throws InvalidSkillException{
//		SkillDTO skillDTO = new SkillDTO();
//		skillDTO.setId(1);
//		skillDTO.setName("Priya");
//		skillDTO.setDescription("WonderWomen");
//		Mockito.when(iSkillDao.findById(skillDTO.getId())).thenReturn(null);
//		SkillDTO actual = iSkillService.update(skillDTO);
//		InvalidSkillException exception = Assertions.assertThrows(InvalidSkillException.class,() -> iSkillService.update(skillDTO));
//		Assertions.assertEquals("Service.SKILL_NOT_FOUND", exception.getMessage());
//
//	}
//
//	@Test
//	void removeSkill() throws InvalidSkillException{
//		SkillDTO skillDTO = new SkillDTO();
//		skillDTO.setId(1);
//		skillDTO.setName("Priya");
//		skillDTO.setDescription("WonderWomen");
//		Optional <Skill> optional = Optional.of(skillDTO.toSkill());
//		Mockito.when(iSkillDao.findById(skillDTO.getId())).thenReturn(optional);
//		iSkillService.remove(skillDTO);
//		Mockito.verify(iSkillDao).deleteById(skillDTO.getId());
//	}
//
//	@Test
//	void skillDoesntExist() throws InvalidSkillException {
//		SkillDTO skillDTO = new SkillDTO();
//		skillDTO.setId(2);
//		skillDTO.setName("Krishna");
//		skillDTO.setDescription("she is a princess");
//		Mockito.when(iSkillDao.findById(skillDTO.getId())).thenReturn(null);
//		iSkillService.remove(skillDTO);
//
//		}
//}
//
