package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gps")
public class Gps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8884372812182103771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gps_id_seq")
	@SequenceGenerator(name = "gps_id_seq", sequenceName = "GPS_ID_SEQ", allocationSize = 100)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "gps", cascade = CascadeType.ALL)
	private Metadata metadata;

	@OneToMany(mappedBy = "gps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WayPoint> wayPoint;

	@OneToMany(mappedBy = "gps", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrackPoint> trackPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<TrackPoint> getTrackPoint() {
		return trackPoint;
	}

	public void setTrackPoint(List<TrackPoint> trackPoint) {
		this.trackPoint = trackPoint;
	}

	public List<WayPoint> getWayPoint() {
		return wayPoint;
	}

	public void setWayPoint(List<WayPoint> wayPoint) {
		this.wayPoint = wayPoint;
	}

}
