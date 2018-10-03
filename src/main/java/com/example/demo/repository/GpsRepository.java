package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Gps;

public interface GpsRepository extends JpaRepository<Gps, String> {
	
	public Gps findOneById(long id);

}
