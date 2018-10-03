package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.TrackPoint;

public interface TrackRepository extends JpaRepository<TrackPoint, String>{
	@Query("select T from TrackPoint T Order By T.time DESC")
	List<TrackPoint> getLastestList(Pageable pageable);
}
