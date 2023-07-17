package com.pro.ss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.ss.model.categories;


@Repository
public interface CategoriesRepo extends JpaRepository<categories, Integer> {


}
