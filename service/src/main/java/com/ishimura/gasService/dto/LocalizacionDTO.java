package com.ishimura.gasService.dto;

public class LocalizacionDTO {

	public String type;
	public GeometryDTO geometry;
	public PropertiesDTO properties;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public GeometryDTO getGeometry() {
		return geometry;
	}
	public void setGeometry(GeometryDTO geometry) {
		this.geometry = geometry;
	}
	public PropertiesDTO getProperties() {
		return properties;
	}
	public void setProperties(PropertiesDTO properties) {
		this.properties = properties;
	}
	
	
}
