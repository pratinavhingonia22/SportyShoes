package com.pro.ss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.ss.model.product;

@Repository
public interface ProductRepo extends JpaRepository<product, Integer> {
	

}
