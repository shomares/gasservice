package com.ishimura.gasService.HTTP;

import java.io.InputStream;
import java.util.List;



public interface IAnalizador {

	<T extends  HttpExtends >List<T> getList(Class<T> tipo, String id, InputStream response);

	<T> List<T> getListFromTable(Class<T> tipo, InputStream response, IHandler handlerResult, String... id);

}
