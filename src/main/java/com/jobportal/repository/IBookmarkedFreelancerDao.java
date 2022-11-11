package com.jobportal.repository;

import com.jobportal.entity.BookmarkedFreelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IBookmarkedFreelancerDao extends JpaRepository<BookmarkedFreelancer, Integer> {

//	List<BookmarkedFreelancer>  findBySkillIdAndBookmarkedById(int skillId, int bookmarkedById);
//	Integer removeByFreelancerFreelancerIdAndSkillIdAndBookmarkedById(int freelancerId, int skillId, int bookmarkedById);

//	@Query("SELECT bf FROM BookmarkedFreelancer bf WHERE bf.skill =:skill")
//	List<BookmarkedFreelancer> findBookmarkedFreelancerBySkill(@Param("skill") Skill skill);
//	@Query("SELECT bf FROM BookmarkedFreelancer bf WHERE (bf.freelancer_Id = ?1 AND bf.bookmarkedBy_Id = ?1 AND bf.skill_Id = ?1)")
	Optional<BookmarkedFreelancer>  findByFreelancerFreelancerIdAndBookmarkedByIdAndSkillId(int freelancerId, int bookmarkedById, int skillId);

	List<BookmarkedFreelancer> findBySkillId(int skillId);

    List<BookmarkedFreelancer> findBySkillName(String skillName);
}


