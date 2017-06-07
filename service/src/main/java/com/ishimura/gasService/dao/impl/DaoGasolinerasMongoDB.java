package com.ishimura.gasService.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.ishimura.gasService.MongoDB.IMongoDBProvider;
import com.ishimura.gasService.MongoDB.MongoContext;
import com.ishimura.gasService.dao.IDaoGasolinera;
import com.ishimura.gasService.dto.GasolineraDTO;
import com.ishimura.gasService.dto.GeometryDTO;
import com.ishimura.gasService.dto.LocalizacionDTO;
import com.ishimura.gasService.dto.PrecioDTO;
import com.ishimura.gasService.dto.PropertiesDTO;
import com.ishimura.gasService.dto.Gasolinera.Places;
import com.ishimura.gasService.dto.Gasolinera.Places.Place;
import com.ishimura.gasService.dto.Gasolinera.Precios.Precio;
import com.ishimura.gasService.dto.Gasolinera.Precios.Precio.Place.GasPrice;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public abstract class DaoGasolinerasMongoDB implements IDaoGasolinera {
	@Autowired
	private IMongoDBProvider Provider;

	public abstract Places getAllPlacesOrigin();

	public abstract Precio getAllPrecioOrigin();

	@Override
	public void getAllPlaces() throws Exception {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DAY_OF_YEAR, 4);
		MongoContext mongo = Provider.getContext();

		try {
			MongoCollection<Document> collection;
			Places places = getAllPlacesOrigin();
			List<Document> documents = new ArrayList<Document>();

			for (Place pl : places.getPlace()) {
				List<Float> array = new ArrayList<Float>();
				array.add(pl.getLocation().getX());
				array.add(pl.getLocation().getY());

				Document place = new Document("id", pl.getPlaceId()).append("brad", pl.getBrad())
						.append("category", pl.getCategory()).append("name", pl.getName())
						.append("creId", pl.getCreId()).append("expires", cl.getTime()).append("loc", array);
				Document geoLocalizacion = new Document().append("type", "Feature");
				Document geometry = new Document().append("type", "Point").append("coordinates", array);
				Document properties = new Document().append("name", pl.getLocation().getAddressStreet());
				geoLocalizacion.put("geometry", geometry);
				geoLocalizacion.put("properties", properties);
				place.put("localizacion", geoLocalizacion);
				documents.add(place);
			}
			collection = mongo.getCollection("Gasolineras");
			collection.insertMany(documents);
		} catch (Exception ex) {
			throw ex;

		} finally {
			mongo.close();
		}

	}

	@Override
	public List<GasolineraDTO> findGasolineras(Float x, Float y) throws Exception {
		// TODO Auto-generated method stub
		int i = 0, j = 0;
		Calendar cl = Calendar.getInstance();
		Date now = cl.getTime();

		List<GasolineraDTO> gasolineras = new ArrayList<GasolineraDTO>();

		MongoContext mongo = Provider.getContext();

		try {
			MongoCollection<Document> collection;
			collection = mongo.getCollection("Gasolineras");
			FindIterable<Document> resultE = collection.find();
			for (Document doc : resultE) {
				j++;
				break;
			}
			if (j == 0)
				getAllPlaces();
			else {
				resultE = collection.find(Filters.lte("expires", now));
				for (Document doc : resultE) {
					System.out.println(doc.toJson());
					i++;
					break;
				}
				if (i > 0) {
					collection.deleteMany(Filters.lte("expires", now));
					getAllPlaces();
				}
			}

			FindIterable<Document> result = collection.find(Filters.geoWithinCenter("loc", x, y, 0.05));
			for (Document objeto : result) {
				Document localizacion = (Document) objeto.get("localizacion");
				Document geometry = (Document) localizacion.get("geometry");
				Document properties = (Document) localizacion.get("properties");
				GasolineraDTO dto = new GasolineraDTO();
				dto.setBrad(objeto.getString("brad"));
				dto.setCreID(objeto.getString("creId"));
				dto.setId(objeto.getInteger("id"));
				@SuppressWarnings("unchecked")
				List<Double> cor = (List<Double>) objeto.get("loc");
				dto.getLoc().addAll(cor);
				LocalizacionDTO loc = new LocalizacionDTO();
				GeometryDTO geo = new GeometryDTO();
				PropertiesDTO prop = new PropertiesDTO();
				loc.setType(localizacion.getString("type"));
				geo.setType(geometry.getString("type"));
				prop.setName(properties.getString("name"));
				geo.getCoordinates().addAll(cor);
				loc.setGeometry(geo);
				loc.setProperties(prop);
				dto.setLocalizacion(loc);
				gasolineras.add(dto);
			}

		} catch (Exception ex) {
			throw ex;
		} finally {
			mongo.close();
			mongo=null;
		}
		return gasolineras;

	}

	public void getAllPrecio() throws Exception {
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.HOUR, 4);
		MongoContext mongo = Provider.getContext();

		try {
			MongoCollection<Document> collection;
			Precio precio = getAllPrecioOrigin();
			List<Document> documents = new ArrayList<Document>();
			for (Precio.Place place : precio.getPlace()) {
				List<Document> values = new ArrayList<Document>();
				Document preDoc = new Document("id", place.getPlaceId()).append("expires", cl.getTime());
				for (GasPrice gasPrice : place.getGasPrice())
					values.add(new Document().append("fecha", gasPrice.getUpdateTime())
							.append("Tipo", gasPrice.getType()).append("Valor", gasPrice.getValue()));
				preDoc.put("values", values);
				documents.add(preDoc);
			}
			collection = mongo.getCollection("Precios");
			collection.insertMany(documents);
		} catch (Exception ex) {
			throw ex;
		} finally {
			mongo.close();
			mongo=null;
		}
	}

	public List<PrecioDTO> getPrecio(Integer idGasolinera) throws Exception {
		Calendar cl = Calendar.getInstance();
		Date now = cl.getTime();
		int i = 0, j = 0;
		List<PrecioDTO> precios = new ArrayList<PrecioDTO>();
		MongoContext mongo = Provider.getContext();
		try {
			MongoCollection<Document> collection;
			collection = mongo.getCollection("Precios");
			// Borramos los
			// expirados-----------------------------------------------------------
			FindIterable<Document> resultE = collection.find();
			for (Document doc : resultE) {
				j++;
				break;
			}
			if (j == 0)
				getAllPrecio();
			else {
				resultE = collection.find(Filters.lte("expires", now));
				for (Document doc : resultE) {
					System.out.println(doc.toJson());
					i++;
					break;
				}
				if (i > 0) {
					collection.deleteMany(Filters.lte("expires", now));
					getAllPrecio();
				}
			}

			FindIterable<Document> result = collection.find(Filters.eq("id", idGasolinera));
			for (Document objeto : result) {
				@SuppressWarnings("unchecked")
				List<Document> lista = (List<Document>) objeto.get("values");
				for (Document doc : lista) {
					PrecioDTO precio = new PrecioDTO();
					precio.setValor(doc.getDouble("Valor"));
					precio.setType(doc.getString("Tipo"));
					// precio.setUpdateTime(new Date());
					precios.add(precio);
				}
			}
			return precios;
		} catch (Exception ex) {
			throw ex;
		} finally {
			mongo.close();
			mongo=null;
		}
	}

}
