package com.pro.ss.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.ss.model.PurchaseReport;
import com.pro.ss.model.UserDetails;
import com.pro.ss.model.product;
import com.pro.ss.repositories.CategoriesRepo;
import com.pro.ss.repositories.ProductRepo;
import com.pro.ss.repositories.PurchaseReportRepo;
import com.pro.ss.repositories.UserDetailsRepo;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	CategoriesRepo catRepo;
	
	@Autowired
	ProductRepo proRepo;
	
	@Autowired
	PurchaseReportRepo purRepo;
	
	@Autowired
	UserDetailsRepo userRepo;
	
	@PostMapping("/signup")
	public String registerUser(@RequestBody UserDetails userDetails)
	{
		try {
			
			if(userDetails == null)
				return "Please enter user details";
			else if(userDetails.getUsername()==null || userDetails.getPassword()==null || 
					userDetails.getEmail()==null || userDetails.getName()==null || userDetails.getSurname()==null)
			{
				return "All Fields are mandatory, please try again!";
			}
			
			List<UserDetails> getUser = userRepo.findByUsername(userDetails.getUsername());
			List<UserDetails> getUseremail = userRepo.findByEmail(userDetails.getEmail());
			if(!getUser.isEmpty())
				return "Username already exists, use another one!";
			if(!getUseremail.isEmpty())
				return "Email already registered, use another one!";
			
			
			UserDetails user = new UserDetails(userDetails.getUsername(), userDetails.getPassword(), userDetails.getEmail(), userDetails.getName(), userDetails.getSurname());
			user.setRole("User");
			userRepo.save(user);
			
			return "Sign up Successful!";			
			
		}
		catch(Exception e)
		{
			return "An Error Occured, Please try again!";
		}
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody UserDetails userDetails)
	{
		try {
			
			if(userDetails == null)
				return "Please enter user details";
			else if(userDetails.getUsername()==null)
			{
				return "All Fields are mandatory, please enter username!";
			}
			else if(userDetails.getPassword()==null)
			{
				return "All Fields are mandatory, please enter password!";
			}
			
			List<UserDetails> getUser = userRepo.findByUsernameAndPassword(userDetails.getUsername(), userDetails.getPassword());
			
			if(getUser.isEmpty())
				return "Username or Password Incorrect! Try Again!";
			else if(!getUser.isEmpty())
				return "Login Successful!";
				
		}
		catch(Exception e)
		{
			return "An error occured! Please try again!";
			
		}
		
		return "";
	}
	
	@PostMapping("{username}/buy/{product_id}")
	public String ProductBuy(@PathVariable int product_id, @PathVariable String username)

	{
		
			Optional<product> getProduct = proRepo.findById(product_id);
			List<UserDetails> user = userRepo.findByUsername(username);
			
			if(getProduct.isPresent() && !user.isEmpty())
			{			
				product pro=getProduct.get();
				PurchaseReport pr = new PurchaseReport();
				
				pr.setCategory_id(pro.getCategories().getCategory_id());
				pr.setCategory_name(pro.getCategories().getCategory_name());
				pr.setProduct_id(pro.getProduct_id());
				pr.setProduct_name(pro.getProduct_name());
				pr.setCost(pro.getCost());
				pr.setUsername(username);
				pr.setTimestamp(pr.getTimestamp());
				
				purRepo.save(pr);
				
				return "Product Bought";				
				
			}
			else
			{
				return "404 ! Product not found";
			}

			
		
		
	}
	
	

}
