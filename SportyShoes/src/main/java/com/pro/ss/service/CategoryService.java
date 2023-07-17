package com.pro.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.ss.model.categories;
import com.pro.ss.repositories.CategoriesRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoriesRepo adminRepo;
	
	public List<categories> getAllCategories()
	{
		List<categories> cat = adminRepo.findAll() ;
		return cat;
	}
}
