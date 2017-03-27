package com.ishimura.gasService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ishimura.gasService.dao.IDaoCatalogo;
import com.ishimura.gasService.dto.Ano;
import com.ishimura.gasService.dto.Marca;
import com.ishimura.gasService.dto.SubModelo;
import com.ishimura.gasService.dto.Vehiculo;

@Controller
@RequestMapping(value = "/Catalogos")
public class CatalogoController {

	@Autowired
	@Qualifier("DaoCatalogos")
	private IDaoCatalogo DaoCatalogo;

	@RequestMapping(value = "/Marcas", method = RequestMethod.GET)
	public ResponseEntity<List<Marca>> getMarcas() {
		ResponseEntity<List<Marca>> salida = null;
		List<Marca> modelo = DaoCatalogo.getMarcas();

		if (modelo.isEmpty())
			throw new Exception404Controller();
		else {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			salida = new ResponseEntity<List<Marca>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

	@RequestMapping(value = "/SubModelos", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<SubModelo>> getSubmodelo(@RequestParam String marca) {
		ResponseEntity<List<SubModelo>> salida = null;

		List<SubModelo> modelo = DaoCatalogo.getSubModelos(marca);
		if (modelo.isEmpty())
			throw new Exception404Controller();
		else {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			salida = new ResponseEntity<List<SubModelo>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

	@RequestMapping(value = "/AnoModelo", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Ano>> getAnoSubmodelo(@RequestParam String marca, @RequestParam String subModelo) {
		ResponseEntity<List<Ano>> salida = null;

		List<Ano> modelo = DaoCatalogo.getAnos(marca, subModelo);
		if (modelo.isEmpty())
			throw new Exception404Controller();
		else {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			salida = new ResponseEntity<List<Ano>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}
	
	@RequestMapping(value = "/Vehiculos", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Vehiculo>> getVehiculos(@RequestParam String marca, @RequestParam String subModelo, @RequestParam String ano) {
		ResponseEntity<List<Vehiculo>> salida = null;
		List<Vehiculo> modelo = DaoCatalogo.getVehiculos(marca, subModelo, ano);
		if (modelo.isEmpty())
			throw new Exception404Controller();
		else {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			salida = new ResponseEntity<List<Vehiculo>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

}
