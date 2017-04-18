package com.ishimura.gasService.dao;

import java.util.List;

import com.ishimura.gasService.dto.Ano;
import com.ishimura.gasService.dto.Marca;
import com.ishimura.gasService.dto.SubModelo;
import com.ishimura.gasService.dto.Vehiculo;

public interface IDaoCatalogo {
	List<Marca> getMarcas();
	List<SubModelo> getSubModelos(String marca);
	List<Ano> getAnos(String marca, String subModelo);
	List<Vehiculo> getVehiculos(String marca, String subModelo, String ano);
}
