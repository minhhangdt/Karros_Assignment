//package com.example.demo.controller;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.util.List;
//
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.alternativevision.gpx.GPXParser;
//import org.alternativevision.gpx.beans.GPX;
//import org.springframework.core.io.ClassPathResource;
//import org.xml.sax.SAXException;
//
//public class test {
//	private static Double latitude;
//	private static Double longitude;
//	private static Double elevation;
//	private static Date time;
//	
//	public static Double getLatitude() {
//		return latitude;
//	}
//
//	public static void setLatitude(Double latitude) {
//		test.latitude = latitude;
//	}
//
//	public static Double getLongitude() {
//		return longitude;
//	}
//
//	public static void setLongitude(Double longitude) {
//		test.longitude = longitude;
//	}
//
//	public static Double getElevation() {
//		return elevation;
//	}
//
//	public static void setElevation(Double elevation) {
//		test.elevation = elevation;
//	}
//
//	public static Date getTime() {
//		return time;
//	}
//
//	public static void setTime(Date time) {
//		test.time = time;
//	}
//
//	public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException{
//		ClassPathResource resource = new ClassPathResource("sample/sample.gpx");
//		InputStream input = resource.getInputStream();
//			
//		GPXParser parser = new GPXParser();
//		GPX gpx = parser.parseGPX(input);
//		Track trk = new Track();
//		List<Waypoint> trkpt = trk.getTrackPoints();
//		Object[] pointArray = trkpt.toArray();
////		for (Object o : pointArray){
////			Point p = (Point) o;
////		}
//		Waypoint wp = new Waypoint();
//		latitude = wp.getLatitude();
//		longitude = wp.getLongitude();
//		elevation = wp.getElevation();
//		time = wp.getTime();
//	
//	}
//	
//
//}
//class Track {
//	private List<Waypoint> trackPoints;
//
//	public List<Waypoint> getTrackPoints() {
//		return trackPoints;
//	}
//
//	public void setTrackPoints(List<Waypoint> trackPoints) {
//		this.trackPoints = trackPoints;
//	}
//
//	
//	
//}
//class Waypoint {
//	private Double latitude;
//	private Double longitude;
//	private Double elevation;
//	private Date time;
//	public Double getLatitude() {
//		return latitude;
//	}
//	public void setLatitude(Double latitude) {
//		this.latitude = latitude;
//	}
//	public Double getLongitude() {
//		return longitude;
//	}
//	public void setLongitude(Double longitude) {
//		this.longitude = longitude;
//	}
//	public Double getElevation() {
//		return elevation;
//	}
//	public void setElevation(Double elevation) {
//		this.elevation = elevation;
//	}
//	public Date getTime() {
//		return time;
//	}
//	public void setTime(Date time) {
//		this.time = time;
//	}
//	
//	
//}
