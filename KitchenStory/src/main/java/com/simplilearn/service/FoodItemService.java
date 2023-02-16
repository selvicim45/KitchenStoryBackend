package com.simplilearn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.simplilearn.repository.FoodItemRepository;
import com.simplilearn.entity.FoodItem;

@Service
public class FoodItemService {
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	//Method to List all Products from DB
		public List<FoodItem> listAll(){		
			List<FoodItem> fooditem = new ArrayList<>();
			foodItemRepository.findAll().forEach(p -> fooditem.add(p));
		    return fooditem;
		}
		
		//Method to delete fooditem from DB
		 public void deleteFoodItem(int id)		 {		 
		 foodItemRepository.deleteById(id);
			
		 }
		 
		

}
