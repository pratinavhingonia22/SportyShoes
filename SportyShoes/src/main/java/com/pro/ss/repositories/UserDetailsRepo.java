package com.pro.ss.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.ss.model.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {

	List<UserDetails> findByUsername(String username);
	List<UserDetails> findByEmail(String email);
	List<UserDetails> findByUsernameAndPassword(String username, String password);

	List<UserDetails> findByRole(String role);

}
