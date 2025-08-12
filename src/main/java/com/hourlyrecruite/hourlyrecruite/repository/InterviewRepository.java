package com.hourlyrecruite.hourlyrecruite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hourlyrecruite.hourlyrecruite.model.Interview;

public interface InterviewRepository  extends JpaRepository<Interview, Long>{

}
