package com.ishimura.gasService.dao.impl;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ishimura.gasService.dto.Gasolinera.Places;
import com.ishimura.gasService.dto.Gasolinera.Precios.Precio;

@Component("DaoGasolinera")
public class DaoGasolinerasHTTP extends DaoGasolinerasMongoDB {
	
	@Autowired
	@Qualifier("ContextGasolina")
	private JAXBContext JaxContext;
	
	@Autowired
	@Qualifier("ContextPrecio")
	private JAXBContext JaxContextPrecio;
	
	
	@Autowired
	@Qualifier("URLOriginGasolina")
	private URL urlGasolina;
	
	@Autowired
	@Qualifier("URLOriginPrecio")
	private URL urlPrecio;
	
	
	
	
	@Override
	public  Places getAllPlacesOrigin(){
		Places salida= new Places();
		try {
			Unmarshaller u = JaxContext.createUnmarshaller();
			salida= (Places) u.unmarshal(urlGasolina);
			return salida;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Precio getAllPrecioOrigin(){
		Precio salida= new Precio();
		try {
			Unmarshaller u = JaxContextPrecio.createUnmarshaller();
			salida= (Precio) u.unmarshal(urlPrecio);
			return salida;
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	

	

	

}
