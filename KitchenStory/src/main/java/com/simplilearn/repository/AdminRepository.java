package com.simplilearn.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.simplilearn.entity.Admin;


@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	 
	 Admin findByid(int id);

	Admin findByusername(String username);

	
	}


