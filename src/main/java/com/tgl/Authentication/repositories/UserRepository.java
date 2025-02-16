package com.tgl.Authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgl.Authentication.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByusername(String username);

}
