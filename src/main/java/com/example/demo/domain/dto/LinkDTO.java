package com.example.demo.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LinkDTO {
	private String text;

	public String getText() {
		return text;
	}

	@XmlElement
	public void setText(String text) {
		this.text = text;
	}
}
