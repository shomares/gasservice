package com.ishimura.gasService.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

import com.ishimura.gasService.HTTP.HttpExtends;
import com.ishimura.gasService.HTTP.IAnalizador;
import com.ishimura.gasService.HTTP.IHandler;

@Component
public class AnalizadorHTTP implements IAnalizador {

	private BufferedReader getBuffer(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream));
	}

	@Override
	public <T extends HttpExtends> List<T> getList(Class<T> tipo, String id, InputStream body) {
		// TODO Auto-generated method stub

		String thisLine = "";
		List<T> salida = new ArrayList<T>();

		BufferedReader br = getBuffer(body);
		try {
			Constructor<T> cto = tipo.getConstructor(null);

			while ((thisLine = br.readLine()) != null) {

				// Vamos a buscar la linea que contenga el id
				String absucar = "<select name=" + '"' + id;
				if (thisLine.contains(absucar)) {
					while (!(thisLine = br.readLine()).contains("</select>")) {
						if (thisLine.contains("option")) {
							String token = "", value = "", text = "";
							for (int i = 0; i < thisLine.length(); i++) {
								char aux = thisLine.charAt(i);
								if (token.contains("=")) {
									aux = thisLine.charAt(++i);
									while (aux != '"') {
										value += aux;
										aux = thisLine.charAt(++i);
									}
									aux = thisLine.charAt(++i);
									while (aux != '<') {
										if (aux != '>')
											text += aux;
										aux = thisLine.charAt(++i);
									}
									if (!value.isEmpty()) {
										T obj = cto.newInstance(null);

										obj.setText(text);
										obj.setValue(value);
										salida.add(obj);
									}
									token = "";
									value = "";
									text = "";
									break;
								}
								if (aux == '=') {
									token += aux;
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return salida;
	}

	private String getValueTextFromLabel(String label, String text) {
		String salida = "";
		int i = 0, j = 1;
		char aux = text.charAt(i);

		while (i < text.length() && aux != '>') {
			aux = text.charAt(++i);
			j++;
		}
		salida = text.substring(j, text.length()).replaceAll("</" + label, "");
		if (salida.contains("</") && !salida.isEmpty()) {
			i = salida.length() - 1;
			aux = salida.charAt(i);
			while (aux != '<') {
				aux = salida.charAt(--i);
			}

			label = salida.substring(i, salida.length());
			i = 0;
			while (aux != '>')
				aux = label.charAt(++i);
			label = label.substring(0, i).replaceAll("</", "").replaceAll(">", "");

			return getValueTextFromLabel(label, salida);
		} else {
			return salida;
		}
	}

	private boolean containList(String[] lista, String source) {
		boolean salida = false;
		for (String elemento : lista) {
			if (source.contains(elemento)) {
				salida = true;
				break;
			}
		}

		return salida;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListFromTable(Class<T> tipo, InputStream response, IHandler handlerResult, String... id) {
		// TODO Auto-generated method stub

		BufferedReader br = getBuffer(response);
		List<T> salida = new ArrayList<T>();
		String thisLine = "";
		Stack<String> pila = new Stack<String>();

		try {
			while ((thisLine = br.readLine()) != null) {
				if (thisLine.contains("<table")) {
					while (!thisLine.contains("</table>")) {
						if (thisLine.contains("<tr") && containList(id, thisLine)) {
							// Estamos en los <TR>
							if (pila != null)
								pila.clear();
							pila = new Stack<String>();
							while (!thisLine.contains("</tr>")) {
								thisLine = br.readLine();
								if (thisLine.contains("<td")) {
									String value = getValueTextFromLabel("td", thisLine);
									if (!value.isEmpty())
										pila.push(value);
								}
							}
							T obj = (T) handlerResult.getObject(pila);
							if (obj != null)
								salida.add(obj);
						}
						thisLine = br.readLine();
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salida;
	}

}
