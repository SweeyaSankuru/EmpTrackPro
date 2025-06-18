package com.sweeya.emptrackpro.repository;

import com.sweeya.emptrackpro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}