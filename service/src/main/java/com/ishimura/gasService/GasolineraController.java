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

import com.ishimura.gasService.dao.IDaoGasolinera;
import com.ishimura.gasService.dto.GasolineraDTO;
import com.ishimura.gasService.dto.PrecioDTO;
import com.ishimura.gasService.dto.Gasolinera.Places;
import com.ishimura.gasService.dto.Gasolinera.Precios.Precio;

@Controller
@RequestMapping(value = "/Gasolinera")
public class GasolineraController {

	@Autowired
	@Qualifier("DaoGasolinera")
	private IDaoGasolinera dao;

	@RequestMapping(value = "/FindGasolineras", method = RequestMethod.GET)
	public ResponseEntity<List<GasolineraDTO>> findByLocation(Float x, Float y) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		ResponseEntity<List<GasolineraDTO>> salida = null;
		try {
			List<GasolineraDTO> modelo = dao.findGasolineras(x, y);

			if (modelo == null) {
				salida = new ResponseEntity<List<GasolineraDTO>>(null, headers, HttpStatus.NOT_FOUND);
			} else {
				salida = new ResponseEntity<List<GasolineraDTO>>(modelo, headers, HttpStatus.OK);
			}
		} catch (Exception ex) {
			salida = new ResponseEntity<List<GasolineraDTO>>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;

	}

	@RequestMapping(value = "/FindPrecio", method = RequestMethod.GET)
	public ResponseEntity<List<PrecioDTO>> findByPrice(Integer idGasolinera) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		ResponseEntity<List<PrecioDTO>> salida = null;
		try {
			List<PrecioDTO> modelo = dao.getPrecio(idGasolinera);
			if (modelo == null) {
				salida = new ResponseEntity<List<PrecioDTO>>(null, headers, HttpStatus.NOT_FOUND);
			} else {
				salida = new ResponseEntity<List<PrecioDTO>>(modelo, headers, HttpStatus.OK);
			}
		} catch (Exception ex) {
			salida = new ResponseEntity<List<PrecioDTO>>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return salida;

	}

}
