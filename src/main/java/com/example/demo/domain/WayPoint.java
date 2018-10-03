package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "way_point")
public class WayPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8725997192694905192L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "way_point_id_seq")
	@SequenceGenerator(name = "way_point_id_seq", sequenceName = "WAY_POINT_ID_SEQ", allocationSize = 100)
	private Long id;
	private String name;
	private Double longitude;
	private Double latitude;
	private String sym;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gps_id", nullable = false)
	private Gps gps;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Gps getGps() {
		return gps;
	}
	public void setGps(Gps gps) {
		this.gps = gps;
	}
	public String getSym() {
		return sym;
	}
	public void setSym(String sym) {
		this.sym = sym;
	}

}
