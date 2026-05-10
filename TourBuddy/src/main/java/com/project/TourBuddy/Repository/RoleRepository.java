package com.project.TourBuddy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.TourBuddy.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByName(String name);
}