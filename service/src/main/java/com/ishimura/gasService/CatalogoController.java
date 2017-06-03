package com.ishimura.gasService;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
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


@Api(name = "Servicio Consumo de Gasolina", description = "Consulta para obtener el rendimiento de gasolina segun el vehiculo")
@ApiVersion(since = "1.0", until = "2.12")
@ApiAuthNone
@Controller
@RequestMapping(value = "/Catalogos")
public class CatalogoController {

	@Autowired
	@Qualifier("DaoCatalogos")
	private IDaoCatalogo DaoCatalogo;

	@ApiMethod
	@RequestMapping(value = "/Marcas", method = RequestMethod.GET)
	public ResponseEntity<List<Marca>> getMarcas() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		ResponseEntity<List<Marca>> salida = null;
		List<Marca> modelo = DaoCatalogo.getMarcas();

		if (modelo.isEmpty()){
			salida = new ResponseEntity<List<Marca>>(null, headers, HttpStatus.NOT_FOUND);
		}
		else {
			salida = new ResponseEntity<List<Marca>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

	@ApiMethod(description="Consulta los submodelos segun la marca")
	@RequestMapping(value = "/SubModelos", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<SubModelo>> getSubmodelo(@ApiQueryParam(description = "Id de la marca de vehiculos") @RequestParam String marca) {
		ResponseEntity<List<SubModelo>> salida = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		
		List<SubModelo> modelo = DaoCatalogo.getSubModelos(marca);
		if (modelo.isEmpty()){
			salida = new ResponseEntity<List<SubModelo>>(null, headers, HttpStatus.NOT_FOUND);
		}
		else {
			salida = new ResponseEntity<List<SubModelo>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

	@ApiMethod(description="Consulta los anos de los submodelos segun la marca")
	@RequestMapping(value = "/AnoModelo", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Ano>> getAnoSubmodelo(@ApiQueryParam(description = "Id de la marca de vehiculos") @RequestParam String marca, @ApiQueryParam(description = "Nombre del submodelo de los vehiculos") @RequestParam String subModelo) {
		ResponseEntity<List<Ano>> salida = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");

		List<Ano> modelo = DaoCatalogo.getAnos(marca, subModelo);
		if (modelo.isEmpty())
			salida = new ResponseEntity<List<Ano>>(null, headers, HttpStatus.NOT_FOUND);
		else {
			salida = new ResponseEntity<List<Ano>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}
	
	@ApiMethod(description="Consulta el rendimiento del vehiculo")
	@RequestMapping(value = "/Vehiculos", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Vehiculo>> getVehiculos(@ApiQueryParam(description = "Id de la marca de vehiculos")  @RequestParam String marca, @ApiQueryParam(description = "Nombre del submodelo de los vehiculos") @RequestParam String subModelo, @ApiQueryParam(description = "Ano de los submodelo de los vehiculos")@RequestParam String ano) {
		ResponseEntity<List<Vehiculo>> salida = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");

		
		List<Vehiculo> modelo = DaoCatalogo.getVehiculos(marca, subModelo, ano);
		if (modelo.isEmpty())
			salida = new ResponseEntity<List<Vehiculo>>(null, headers, HttpStatus.NOT_FOUND);
		else {
			salida = new ResponseEntity<List<Vehiculo>>(modelo, headers, HttpStatus.OK);
		}
		return salida;
	}

}
