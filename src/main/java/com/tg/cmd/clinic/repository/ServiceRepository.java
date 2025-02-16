package com.tg.cmd.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.cmd.clinic.model.Clinic;

public interface ServiceRepository extends JpaRepository<Clinic, String> {

}