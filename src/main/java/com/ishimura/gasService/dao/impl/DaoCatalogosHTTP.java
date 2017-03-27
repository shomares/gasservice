package com.ishimura.gasService.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ishimura.gasService.HTTP.IAnalizador;
import com.ishimura.gasService.HTTP.IHandler;
import com.ishimura.gasService.dao.IDaoCatalogo;
import com.ishimura.gasService.dto.Ano;
import com.ishimura.gasService.dto.Marca;
import com.ishimura.gasService.dto.SubModelo;
import com.ishimura.gasService.dto.Vehiculo;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


@Component("Source")
public class DaoCatalogosHTTP implements IDaoCatalogo {

	private String urlBase;

	@Autowired
	private IAnalizador analizador;

	@Override
	public List<Marca> getMarcas() {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();
		List<Marca> salida = null;
		Request request = new Request.Builder().url("http://www.ecovehiculos.gob.mx/buscamarcamodelo.php").get()
				.addHeader("upgrade-insecure-requests", "1")
				.addHeader("x-devtools-emulate-network-conditions-client-id", "c3e6f888-2077-4c2f-9d76-f0b7c44abadc")
				.addHeader("user-agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("accept-encoding", "gzip, deflate, sdch")
				.addHeader("accept-language", "es,en-US;q=0.8,en;q=0.6")
				.addHeader("cookie",
						"__utmt=1; encuestaecoetiquetado=1; __utma=21458479.1460983810.1490377859.1490377859.1490377859.1; __utmb=21458479.3.10.1490377859; __utmc=21458479; __utmz=21458479.1490377859.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=21458479.1460983810.1490377859.1490377859.1490377859.1; __utmb=21458479.4.10.1490377859; __utmc=21458479; __utmz=21458479.1490377859.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "f47ff85f-d2bc-8250-6a28-d6d42b55697a").build();

		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				InputStream body = response.body().byteStream();
				salida = analizador.getList(Marca.class, "marca_id", body);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public List<SubModelo> getSubModelos(String marca) {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();
		List<SubModelo> salida = null;
		// Peticion HTTP------------------------------------
		Request request = new Request.Builder()
				.url("http://www.ecovehiculos.gob.mx/buscamarcamodelo.php?marca_id=" + marca).get()
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "942958e2-4e3e-b03e-b896-beeba6ae1cb5").build();
		// ---------------------------------------------------------------------------------------
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				InputStream body = response.body().byteStream();
				salida = analizador.getList(SubModelo.class, "vehiculo_submarca", body);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public List<Ano> getAnos(String marca, String subModelo) {
		OkHttpClient client = new OkHttpClient();
		List<Ano> salida = null;
		Request request = new Request.Builder()
				.url("http://www.ecovehiculos.gob.mx/buscamarcamodelo.php?marca_id=" + marca + "&vehiculo_submarca="
						+ subModelo)
				.get()
				.addHeader("x-devtools-emulate-network-conditions-client-id", "c3e6f888-2077-4c2f-9d76-f0b7c44abadc")
				.addHeader("upgrade-insecure-requests", "1")
				.addHeader("user-agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("referer", "http://www.ecovehiculos.gob.mx/buscamarcamodelo.php?marca_id=" + marca)
				.addHeader("accept-encoding", "gzip, deflate, sdch")
				.addHeader("accept-language", "es,en-US;q=0.8,en;q=0.6")
				.addHeader("cookie",
						"__utma=21458479.1460983810.1490377859.1490377859.1490377859.1; __utmb=21458479.3.10.1490377859; __utmc=21458479; __utmz=21458479.1490377859.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; encuestaecoetiquetado=1; __utma=21458479.1460983810.1490377859.1490377859.1490377859.1; __utmb=21458479.3.10.1490377859; __utmc=21458479; __utmz=21458479.1490377859.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "403419b5-3b54-8cb5-2886-59fd377889e3").build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				InputStream body = response.body().byteStream();
				salida = analizador.getList(Ano.class, "vehiculo_modelo", body);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;

	}

	@Override
	public List<Vehiculo> getVehiculos(final String marca, String subModelo, String ano) {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();
		List<Vehiculo> salida = null;
		RequestBody formBody = new FormEncodingBuilder().
				add("marca_id", marca)
				.add("vehiculo_modelo",  ano)
				.add("vehiculo_submarca", subModelo)
				.build();
		Request request = new Request.Builder().url("http://www.ecovehiculos.gob.mx/buscamarcamodelo2.php")
				.post(formBody).addHeader("origin", "http://www.ecovehiculos.gob.mx")
				.addHeader("upgrade-insecure-requests", "1")
				.addHeader("user-agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
				.addHeader("content-type", "application/x-www-form-urlencoded")
				.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.addHeader("referer",
						"http://www.ecovehiculos.gob.mx/buscamarcamodelo.php?marca_id="+  marca +"&vehiculo_submarca=" +  subModelo)
				.addHeader("accept-encoding", "gzip, deflate").addHeader("accept-language", "es,en-US;q=0.8,en;q=0.6")
				.addHeader("cookie",
						"__utmt=1; __utma=21458479.1460983810.1490377859.1490377859.1490464944.2; __utmb=21458479.3.10.1490464944; __utmc=21458479; __utmz=21458479.1490377859.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
				.addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "1a7c3ddd-1ce1-a46d-3206-05791df887b2").build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				InputStream body = response.body().byteStream();
				
				salida= analizador.getListFromTable(Vehiculo.class, body, new IHandler(){

					@Override
					public Object getObject(Stack<String> pila) {
						// TODO Auto-generated method stub
						
						Vehiculo vehiculo= new Vehiculo();
						String args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setEmisionNOX(Double.valueOf(args));
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setEmision(Double.valueOf(args));
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setRendimientoPorLitro(Double.valueOf(args));
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setCombustible(args);
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setTransmision(args);
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setAno(args);
						args= pila.pop().replaceAll("&nbsp;>", "").replaceAll(">", "");
						vehiculo.setNombre(args);
						vehiculo.setMarca(marca);
						
						return vehiculo;
					}
					
				}, "tabla_ecoetiqEF", "tabla_ecoetiqGH");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salida;
	}

	public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

}
