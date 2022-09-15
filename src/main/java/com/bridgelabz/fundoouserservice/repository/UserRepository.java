package com.bridgelabz.fundoouserservice.repository;

import com.bridgelabz.fundoouserservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Purpose: Creating repository for fundoo user
 * @author: Annu Kumari
 * @Param:  To save data in database
 * Version: 1.0
 */

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmailId(String emailId);
}
