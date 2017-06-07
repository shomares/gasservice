package com.ishimura.gasService.dao;


import java.util.List;

import com.ishimura.gasService.dto.GasolineraDTO;
import com.ishimura.gasService.dto.PrecioDTO;
import com.ishimura.gasService.dto.Gasolinera.Places;
import com.ishimura.gasService.dto.Gasolinera.Precios.Precio;

public interface IDaoGasolinera {
	 void getAllPlaces() throws Exception;
	 List<GasolineraDTO>  findGasolineras(Float x, Float y)throws Exception;
	 List<PrecioDTO> getPrecio(Integer idGasolinera) throws Exception;
	 void getAllPrecio() throws Exception;
	 
}
