
package com.jobportal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jobportal.entity.Admin;

public interface IAdminDao extends CrudRepository<Admin, Integer>{

	Optional<Admin> findByUserName(String userName);

}


