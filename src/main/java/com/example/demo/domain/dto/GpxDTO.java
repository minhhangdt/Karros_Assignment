package com.example.demo.domain.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gpx")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GpxDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6728541280457851313L;
	
	MetadataDTO metadata;

	public MetadataDTO getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}

}
