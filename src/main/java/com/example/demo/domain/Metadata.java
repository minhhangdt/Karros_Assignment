package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Metadata {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metadata_id_seq")
	@SequenceGenerator(name = "metadata_id_seq", sequenceName = "METADATA_ID_SEQ", allocationSize = 100)
	private Long id;
	private String name;
	
	@Column(name = "desc", length=1000)
	private String desc;
	private String author;
	private String link;
	private Date time;
	
	public Metadata() {
		super();
	}
	public Metadata(Gps gps) {
		super();
		this.gps = gps;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gps_id", unique = true, nullable = false)
	@JsonIgnore
	private Gps gps;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Gps getGps() {
		return gps;
	}
	public void setGps(Gps gps) {
		this.gps = gps;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Metadata [id=" + id + ", name=" + name + ", desc=" + desc + ", author=" + author + ", link=" + link
				+ ", time=" + time + ", gps=" + gps + "]";
	}
	
	
}
