package com.ishimura.gasService.dto;

import java.util.List;

import com.ishimura.gasService.HTTP.HttpExtends;

public class Marca extends HttpExtends {
	
	private List<SubModelo> SubModelos;
	
	public List<SubModelo> getSubModelos() {
		return SubModelos;
	}
	public void setSubModelos(List<SubModelo> subModelos) {
		SubModelos = subModelos;
	}
	
	
	
}
