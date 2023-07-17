package com.pro.ss.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.ss.model.PurchaseReport;
import com.pro.ss.model.UserDetails;
import com.pro.ss.model.categories;
import com.pro.ss.model.product;
import com.pro.ss.repositories.CategoriesRepo;
import com.pro.ss.repositories.ProductRepo;
import com.pro.ss.repositories.PurchaseReportRepo;
import com.pro.ss.repositories.UserDetailsRepo;
import com.pro.ss.service.CategoryService;

@RestController
@RequestMapping("/admin")
public class AdminController{

	@Autowired
	CategoryService catService;
	
	@Autowired
	CategoriesRepo catRepo;
	
	@Autowired
	ProductRepo proRepo;
	
	@Autowired
	PurchaseReportRepo purRepo;
	
	@Autowired
	UserDetailsRepo userRepo;
	
	
	@PostMapping("/login")
	public String loginAdmin(@RequestBody UserDetails user)
	{
		String username = user.getUsername();
		String password = user.getPassword();
		
		try {
			
			if(username==null)
			{
				return "All Fields are mandatory, please enter username!";
			}
			else if(password==null)
			{
				return "All Fields are mandatory, please enter password!";
			}
			
			
			
			List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
			if(getUser.isEmpty())
				return "Username or Password Incorrect! Try Again!";
			
			UserDetails admin = getUser.get(0);
			
			
			if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
				return "Login Successful!";
			else if(!getUser.isEmpty() && admin.getRole()!="Admin")
				return "Access Denied! You are not an admin";
				
		}
		catch(Exception e)
		{
			return "An error occured! Please try again! "+e.getMessage();
			
		}
		
		return "";
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<String> changePass(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword)
	{
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		
		if(getUser.isEmpty())
			return new ResponseEntity<String>("Username or Password Incorrect! Try Again!", HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{
			admin.setPassword(newPassword);
			userRepo.save(admin);
			return new ResponseEntity<String>("Password Changed Successfully! New Password is: "+newPassword, HttpStatus.OK);
		}
		else if(!getUser.isEmpty() && admin.getRole()!="Admin")
			return new ResponseEntity<String>("Access Denied! You are not an admin!", HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<String>("Something went wrong! Try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			
	}
	
	
	@GetMapping("/fetchAllUsers")
	public ResponseEntity<List<UserDetails>> fetchAllUsers(@RequestBody UserDetails user)
	{
		String username = user.getUsername();
		String password = user.getPassword();
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{
			List<UserDetails> users = userRepo.findByRole("User");
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/searchUser")
	public ResponseEntity<List<UserDetails>> searchUser(@RequestParam String adminusername, @RequestParam String adminpassword, @RequestParam String username)
	{
		List<UserDetails> getAdmin = userRepo.findByUsernameAndPassword(adminusername, adminpassword);
		if(getAdmin.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getAdmin.get(0);
		
		
		if(!getAdmin.isEmpty() && admin.getRole().equals("Admin"))
		{
			List<UserDetails> users = userRepo.findByUsername(username);
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}

	
	@GetMapping("/fetchAllProducts")
	public ResponseEntity<List<product>> fetchAllProducts(@RequestBody UserDetails user)
	{
		String username = user.getUsername();
		String password = user.getPassword();
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{
			List<product> prods = proRepo.findAll();
			return new ResponseEntity<>(prods, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/fetchAllCat")
	public ResponseEntity<List<categories>> fetchAllCategories(@RequestBody UserDetails user)
	{
		String username = user.getUsername();
		String password = user.getPassword();
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{	
			List<categories> cat = catRepo.findAll();
			return new ResponseEntity<>(cat, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/fetchPR")
	public ResponseEntity<List<PurchaseReport>> fetchPR(@RequestBody UserDetails user)
	{
		String username = user.getUsername();
		String password = user.getPassword();
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{	List<PurchaseReport> pr = purRepo.findAll();
		
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/fetchPRbyDate")
	public ResponseEntity<List<PurchaseReport>> fetchPRbyDate(@RequestParam String username, @RequestParam String password, @RequestParam Date startDate, @RequestParam Date endDate)
	{
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{	
			List<PurchaseReport> pr = purRepo.findByListDateRange(startDate, endDate);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/fetchPRbyCategory")
	public ResponseEntity<List<PurchaseReport>> fetchPRbyCategory(@RequestParam String username, @RequestParam String password, @RequestParam int cat)
	{
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin"))
		{	
			List<PurchaseReport> pr = purRepo.findByCategoryid(cat);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<categories> addCat(@RequestParam String username, @RequestParam String password, @RequestParam int cat_id, @RequestParam String cat_name)
	{
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin")) {
			try {
			categories _cat=new categories();
			
			_cat.setCategory_id(cat_id);
			_cat.setCategory_name(cat_name);
			
			catRepo.save(_cat);
			return new ResponseEntity<>(_cat, HttpStatus.CREATED);
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<product> addProduct(@RequestParam String username, @RequestParam String password, @RequestParam int product_id, 
			@RequestParam String product_name, @RequestParam int cost, @RequestParam int cat_id)
	{
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin")) {
			try {
			product _pro=new product();
			
			_pro.setProduct_id(product_id);
			_pro.setProduct_name(product_name);
			_pro.setCost(cost);
			_pro.setCategories(catRepo.findById(cat_id).get());
			
			proRepo.save(_pro);
			return new ResponseEntity<>(_pro, HttpStatus.CREATED);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@DeleteMapping("/delProduct/{id}")
	public ResponseEntity<String> delProduct(@PathVariable("id") int id, @RequestBody UserDetails user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		List<UserDetails> getUser = userRepo.findByUsernameAndPassword(username, password);
		if(getUser.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		UserDetails admin = getUser.get(0);
		
		if(!getUser.isEmpty() && admin.getRole().equals("Admin")) {
		try {
			proRepo.deleteById(id);
			return new ResponseEntity<>("Product id: "+id+" is deleted",HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	

}
