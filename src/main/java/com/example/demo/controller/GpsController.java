package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.domain.Gps;
import com.example.demo.domain.TrackPoint;
import com.example.demo.service.GpsService;


@RestController
@RequestMapping(value = "${spring.data.rest.basePath}" + "/gps")
public class GpsController {

	@Autowired
	GpsService gpsService;
	
	@GetMapping(value = "/get-lastest-track")
	public ResponseEntity<List<TrackPoint>> getLastestTrack(@RequestParam(value="top", defaultValue = "10") int top){
		List<TrackPoint> gpsLastestList = gpsService.getLastestList(top);
		return new ResponseEntity<>(gpsLastestList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/view-detail/{id}")
	public ResponseEntity<Gps> getViewDetail(@PathVariable("id") long id){
		Gps gps = gpsService.getDetail(id);
		return new ResponseEntity<>(gps, HttpStatus.OK);
	}
	 
	@PostMapping(value = "/upload")
	public ResponseEntity<Gps> saveDataFromGpx(@RequestParam("file") MultipartFile file) throws Exception{
		Gps gps = gpsService.saveDataFromGpx(file);
		return new ResponseEntity<>(gps, HttpStatus.OK);
	}
}
