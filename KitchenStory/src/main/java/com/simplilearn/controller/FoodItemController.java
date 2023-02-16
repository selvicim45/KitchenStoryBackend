package com.simplilearn.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.FoodItem;
import com.simplilearn.repository.FoodItemRepository;
import com.simplilearn.service.FoodItemService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class FoodItemController {
	@Autowired
	private FoodItemService foodItemService;
	FoodItem fooditem;
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@GetMapping("/fooditems")
	public List<FoodItem> display()
	{
		return foodItemService.listAll();
	}
	
	@GetMapping("/fooditems/selection/{selection}")
	public List<FoodItem> displayBySelection(@PathVariable String selection)
	{ 
		System.out.println("The selection varaiable is "+ selection);
		
		return foodItemRepository.findAllByfdesc(selection);
		
	}
	
	@GetMapping("/fooditems/{id}")
	public FoodItem displayById(@PathVariable Integer id) throws NotFoundException
	{
		return foodItemRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("/fooditems")
	public FoodItem  addFoodItem(@RequestBody FoodItem fooditem)
	{
		return foodItemRepository.save(fooditem);
	}

    @PutMapping("/fooditems/{id}")
	public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Integer id, @RequestBody FoodItem fooditem) throws NotFoundException
	{
	 
		FoodItem fooditemObject = foodItemRepository.findById(id).orElseThrow(NotFoundException::new);
		fooditemObject.setFname(fooditem.getFname());
		fooditemObject.setFprice(fooditem.getFprice());
		fooditemObject.setFdesc(fooditem.getFdesc());
		
		FoodItem updatedFoodItem=foodItemRepository.save(fooditemObject);
		return ResponseEntity.ok(updatedFoodItem);
	}
    
    @DeleteMapping("/fooditems/{id}")
    public void deleteFoodItem(@PathVariable Integer id)
    {
    	foodItemRepository.deleteById(id);
    }
    
 
   
    
}
