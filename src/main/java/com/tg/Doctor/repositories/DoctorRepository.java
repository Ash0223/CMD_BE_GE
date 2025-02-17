package com.tg.Doctor.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.Doctor.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,String>{
	
	Optional<Doctor> findByPhoneNum(Long phoneNum);
	
	Page<Doctor> findAll(Pageable pageable);
}
   