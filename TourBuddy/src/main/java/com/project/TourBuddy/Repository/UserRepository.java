package com.project.TourBuddy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.TourBuddy.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public  Optional<User> findByEmail(String email);

	public Optional<User> findById(Long id);
	
	 List<User> findByRole_Name(String name);
//	Optional <User> findByRole_Name(String name);
}


