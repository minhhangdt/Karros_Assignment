package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Gps;
import com.example.demo.domain.TrackPoint;

public interface GpsService {

	public List<TrackPoint> getLastestList(int top);
	
	public Gps getDetail(long id);
	
	public Gps saveDataFromGpx(MultipartFile file) throws Exception;
}
