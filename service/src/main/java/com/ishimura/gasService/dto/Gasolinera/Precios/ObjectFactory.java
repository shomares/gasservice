//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantacion de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderan si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.06.03 a las 08:44:37 PM CDT 
//


package com.ishimura.gasService.dto.Gasolinera.Precios;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ishimura.gasService.dto.Gasolinera.Precios package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ishimura.gasService.dto.Gasolinera.Precios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Precio }
     * 
     */
    public Precio createPlaces() {
        return new Precio();
    }

    /**
     * Create an instance of {@link Precio.Place }
     * 
     */
    public Precio.Place createPlacesPlace() {
        return new Precio.Place();
    }

    /**
     * Create an instance of {@link Precio.Place.GasPrice }
     * 
     */
    public Precio.Place.GasPrice createPlacesPlaceGasPrice() {
        return new Precio.Place.GasPrice();
    }

}
