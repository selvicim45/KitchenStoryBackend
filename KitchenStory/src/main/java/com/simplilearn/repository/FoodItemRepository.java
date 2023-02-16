package com.simplilearn.repository;

 

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.entity.FoodItem;

@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Integer> {

	@Query(value = "SELECT p.fid,p.fname,p.fprice FROM FoodItems p WHERE p.fdesc = ?1") 
	List<FoodItem> findAllByfdesc(String fdesc);
}
