package com.simplilearn.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.entity.Admin;

import com.simplilearn.repository.AdminRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v2/")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	//Update password
		@PutMapping("/admin/{id}")
		public void updatePassword(@PathVariable Integer id,@RequestBody Admin adminData) throws NotFoundException 
		{     
			System.out.println("Inside the update password method");
			int id1 = adminData.getId();
			String username1 = adminData.getUsername();
			String pwd = adminData.getPassword();
			System.out.println(id1);
			System.out.println(username1);
			System.out.println(pwd);
			
			
			Admin adminToUpdate = adminRepository.findByid(id);		
			adminToUpdate.setId(id1);
			adminToUpdate.setUsername(username1);
			adminToUpdate.setPassword(pwd);	
			adminRepository.save(adminToUpdate);
		
		}
		
		
		//Code for user login
		@PostMapping("/admin")
		public ResponseEntity<?> loginUser(@RequestBody Admin adminData)
		{
			//System.out.println("I am inside the method.......");
			//System.out.println("username is "+ adminData.getUsername());
			
			
			//get id of data from databse by passing username entered in front end textbox
			int id = adminRepository.findByusername(adminData.getUsername()).getId();

			//using the id found in previous step, get that user data into admin object
			Admin admin = adminRepository.findByid(id);
			
			//Check whether the password entered in the front end and database match
			if (admin.getPassword().equals(adminData.getPassword()))
				return ResponseEntity.ok(admin);
			else
			{
				System.out.println("error is " + (ResponseEntity<?>) ResponseEntity.internalServerError());
				return (ResponseEntity<?>) ResponseEntity.internalServerError();
			}
			
		
		}
}
		


