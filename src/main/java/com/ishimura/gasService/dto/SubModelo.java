package com.ishimura.gasService.dto;

import java.util.List;

import com.ishimura.gasService.HTTP.HttpExtends;

public class SubModelo  extends HttpExtends  {

	
	
	private List<Ano> anoModelo;
	public List<Ano> getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(List<Ano> anoModelo) {
		this.anoModelo = anoModelo;
	}
	
	
	
}
