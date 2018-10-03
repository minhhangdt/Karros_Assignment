package com.example.demo.domain.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MetadataDTO {
	
	private String name;

	private String desc;
	
	private String author;
	
	private String link;
	
	private Date time;
	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthor() {
		return author;
	}
	
	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getTime() {
		return time;
	}
	@XmlElement
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getLink() {
		return link;
	}
	
	@XmlAttribute
	public void setLink(String link) {
		this.link = link;
	}
	
	
}
