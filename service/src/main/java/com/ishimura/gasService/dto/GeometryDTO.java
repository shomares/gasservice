package com.ishimura.gasService.dto;

import java.util.ArrayList;
import java.util.List;

public class GeometryDTO {

	private String type;
	private List<Double> coordinates;
	
	
	public GeometryDTO() {
		coordinates= new ArrayList<Double>();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Double> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	
	
	
}
