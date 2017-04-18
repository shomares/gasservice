package com.ishimura.gasService.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ishimura.gasService.MongoDB.IMongoDBProvider;
import com.ishimura.gasService.MongoDB.MongoContext;
import com.ishimura.gasService.dao.IDaoCatalogo;
import com.ishimura.gasService.dto.Ano;
import com.ishimura.gasService.dto.Marca;
import com.ishimura.gasService.dto.SubModelo;
import com.ishimura.gasService.dto.Vehiculo;
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component("DaoCatalogos")
public class DaoCatalogosMongoDB implements IDaoCatalogo {

	@Autowired
	private IMongoDBProvider Provider;

	@Autowired
	@Qualifier("Source")
	private IDaoCatalogo source;

	private int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	@Override
	public List<Marca> getMarcas() {
		// TODO Auto-generated method stub
		Date now = new Date();
		MongoContext context = Provider.getContext();

		try {
			MongoCollection<Document> collection = context.getCollection("marcas");
			List<Marca> marcas = new ArrayList<Marca>();
			FindIterable<Document> result = collection.find();
			for(Document objeto : result) {
				Date date = (Date) objeto.get("fechaActualizacion");
				if (diferenciaEnDias2(now, date) < 1) {
					Marca marca = new Marca();
					marca.setValue((String) objeto.get("value"));
					marca.setText((String) objeto.get("text"));
					marcas.add(marca);
				} else
					break;
			}
			if (marcas.isEmpty()) {
				marcas = source.getMarcas();
				saveMarcas(collection, marcas);
			}
			return marcas;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
			
		} 
		
		finally {
			context.close();
		}
	}

	private void saveMarcas(MongoCollection<Document> collection, List<Marca> marcas) {
		// TODO Auto-generated method stub
		Date date = new Date();

		for (Marca marca : marcas) {
			Document doc = new Document("value", marca.getValue()).append("text", marca.getText())
					.append("fechaActualizacion", date);
			collection.deleteOne(Filters.eq("value", marca.getValue()));
			collection.insertOne(doc);
		}

	}

	@Override
	public List<SubModelo> getSubModelos(String marca) {
		// TODO Auto-generated method stub
		Date now = new Date();
		MongoContext context = Provider.getContext();

		try {
			MongoCollection<Document> collection = context.getCollection("subModelo");
			List<SubModelo> salida = new ArrayList<SubModelo>();
			FindIterable<Document> result = collection.find(Filters.eq("marca", marca));
			for(Document objeto : result) { 
				Date date = (Date) objeto.get("fechaActualizacion");
				if (diferenciaEnDias2(now, date) < 1) {
					SubModelo elemento = new SubModelo();
					elemento.setValue((String) objeto.get("value"));
					elemento.setText((String) objeto.get("text"));
					salida.add(elemento);
				} else
					break;
			}
			if (salida.isEmpty()) {
				salida = source.getSubModelos(marca);
				saveSubmodelo(collection, salida, marca);
			}
			return salida;

		} finally {
			context.close();
			context = null;
		}
	}

	private void saveSubmodelo(MongoCollection<Document> collection, List<SubModelo> salida, String marca) {
		// TODO Auto-generated method stub
		Date date = new Date();
		collection.deleteMany(Filters.and(Filters.eq("marca", marca)));

		for (SubModelo elemento : salida) {
			Document doc = new Document("value", elemento.getValue()).append("text", elemento.getText())
					.append("fechaActualizacion", date).append("marca", marca);
			collection.insertOne(doc);
		}

	}

	@Override
	public List<Ano> getAnos(String marca, String subModelo) {
		// TODO Auto-generated method stub
		Date now = new Date();
		MongoContext context = Provider.getContext();

		try {
			MongoCollection<Document> collection = context.getCollection("ano");

			List<Ano> salida = new ArrayList<Ano>();
			FindIterable<Document> result = collection
					.find(Filters.and(Filters.eq("marca", marca), Filters.eq("subModelo", subModelo)));
			for(Document objeto : result) { 
				Date date = (Date) objeto.get("fechaActualizacion");
				if (diferenciaEnDias2(now, date) < 1) {
					Ano elemento = new Ano();
					elemento.setValue((String) objeto.get("value"));
					elemento.setText((String) objeto.get("text"));
					salida.add(elemento);
				} else
					break;
			}
			if (salida.isEmpty()) {
				salida = source.getAnos(marca, subModelo);
				saveAno(collection, salida, marca, subModelo);
			}
			return salida;

		} finally {
			context.close();
			context = null;
		}
	}

	private void saveAno(MongoCollection<Document> collection, List<Ano> salida, String marca, String subModelo) {
		// TODO Auto-generated method stub
		Date date = new Date();
		collection.deleteMany(Filters.and(Filters.eq("marca", marca), Filters.eq("subModelo", subModelo)));
		for (Ano elemento : salida) {
			Document doc = new Document("value", elemento.getValue()).append("text", elemento.getText())
					.append("fechaActualizacion", date).append("marca", marca).append("subModelo", subModelo);
			collection.insertOne(doc);
		}
	}

	@Override
	public List<Vehiculo> getVehiculos(String marca, String subModelo, String ano) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Date now = new Date();
		MongoContext context = Provider.getContext();

		try {
			MongoCollection<Document> collection = context.getCollection("vehiculo");
			List<Vehiculo> salida = new ArrayList<Vehiculo>();
			FindIterable<Document> result = collection
					.find(Filters.and(Filters.eq("marca", marca), Filters.eq("subModelo", subModelo), Filters.eq("ano", ano)));
			for(Document objeto : result) { 
				Date date = (Date) objeto.get("fechaActualizacion");
				if (diferenciaEnDias2(now, date) < 1) {
					Vehiculo elemento = new Vehiculo();
					elemento.setMarca((String) objeto.get("marca"));
					elemento.setAno(ano);
					elemento.setCombustible((String) objeto.get("combustible"));
					elemento.setEmision(objeto.getDouble("emision"));
					elemento.setEmisionNOX(objeto.getDouble("emisionNOX"));
					elemento.setTransmision((String) objeto.get("transmision"));
					elemento.setRendimientoPorLitro(objeto.getDouble("rendimientoLitro"));
					elemento.setNombre((String)objeto.get("nombre"));
					salida.add(elemento);
				} else
					break;
			}
			if (salida.isEmpty()) {
				salida = source.getVehiculos(marca, subModelo, ano);
				saveVehiculo(collection, salida, marca, subModelo,ano);
			}
			return salida;

		} finally {
			context.close();
			context = null;
		}
	}

	private void saveVehiculo(MongoCollection<Document> collection, List<Vehiculo> salida, String marca,
			String subModelo, String ano) {
		// TODO Auto-generated method stub
		Date date = new Date();
		collection.deleteMany(Filters.and(Filters.eq("marca", marca), Filters.eq("subModelo", subModelo), Filters.eq("ano", ano)));
		for (Vehiculo elemento : salida) {
			Document doc = new Document("nombre", elemento.getNombre())
					.append("combustible", elemento.getCombustible())
					.append("emision", elemento.getEmision())
					.append("emisionNOX", elemento.getEmisionNOX())
					.append("transmision", elemento.getTransmision())
					.append("rendimientoLitro", elemento.getRendimientoPorLitro())
					.append("fechaActualizacion", date).append("marca", marca).append("subModelo", subModelo).append("ano", ano);
			collection.insertOne(doc);
		}
		
	}

	public IDaoCatalogo getSource() {
		return source;
	}

	public void setSource(IDaoCatalogo source) {
		this.source = source;
	}

	public IMongoDBProvider getProvider() {
		return Provider;
	}

	public void setProvider(IMongoDBProvider provider) {
		Provider = provider;
	}

}
