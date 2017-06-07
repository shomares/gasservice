package com.ishimura.gasService.dto;

import java.util.ArrayList;
import java.util.List;

public class GasolineraDTO {

	private Integer id;
	private String brad;
	private String creID;
	private List<Double> loc;
	private LocalizacionDTO localizacion;
	
	
	
	
	public GasolineraDTO() {
		loc= new ArrayList<Double>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrad() {
		return brad;
	}
	public void setBrad(String brad) {
		this.brad = brad;
	}
	public String getCreID() {
		return creID;
	}
	public void setCreID(String creID) {
		this.creID = creID;
	}
	public List<Double> getLoc() {
		return loc;
	}
	public void setLoc(List<Double> loc) {
		this.loc = loc;
	}
	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}
	
	
}
