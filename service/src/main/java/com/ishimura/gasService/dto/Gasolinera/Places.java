//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantacion de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderan si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.06.03 a las 08:45:25 PM CDT 
//


package com.ishimura.gasService.dto.Gasolinera;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="place" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="brad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cre_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="location">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="address_street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                             &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="place_id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "place"
})
@XmlRootElement(name = "places")
public class Places {

    protected List<Places.Place> place;

    /**
     * Gets the value of the place property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the place property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Places.Place }
     * 
     * 
     */
    public List<Places.Place> getPlace() {
        if (place == null) {
            place = new ArrayList<Places.Place>();
        }
        return this.place;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="brad" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="cre_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="location">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="address_street" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                   &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="place_id" type="{http://www.w3.org/2001/XMLSchema}short" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "brad",
        "creId",
        "category",
        "location"
    })
    public static class Place {

        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String brad;
        @XmlElement(name = "cre_id", required = true)
        protected String creId;
        @XmlElement(required = true)
        protected String category;
        @XmlElement(required = true)
        protected Places.Place.Location location;
        @XmlAttribute(name = "place_id")
        protected Short placeId;

        /**
         * Obtiene el valor de la propiedad name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Define el valor de la propiedad name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Obtiene el valor de la propiedad brad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrad() {
            return brad;
        }

        /**
         * Define el valor de la propiedad brad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrad(String value) {
            this.brad = value;
        }

        /**
         * Obtiene el valor de la propiedad creId.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreId() {
            return creId;
        }

        /**
         * Define el valor de la propiedad creId.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreId(String value) {
            this.creId = value;
        }

        /**
         * Obtiene el valor de la propiedad category.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCategory() {
            return category;
        }

        /**
         * Define el valor de la propiedad category.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCategory(String value) {
            this.category = value;
        }

        /**
         * Obtiene el valor de la propiedad location.
         * 
         * @return
         *     possible object is
         *     {@link Places.Place.Location }
         *     
         */
        public Places.Place.Location getLocation() {
            return location;
        }

        /**
         * Define el valor de la propiedad location.
         * 
         * @param value
         *     allowed object is
         *     {@link Places.Place.Location }
         *     
         */
        public void setLocation(Places.Place.Location value) {
            this.location = value;
        }

        /**
         * Obtiene el valor de la propiedad placeId.
         * 
         * @return
         *     possible object is
         *     {@link Short }
         *     
         */
        public Short getPlaceId() {
            return placeId;
        }

        /**
         * Define el valor de la propiedad placeId.
         * 
         * @param value
         *     allowed object is
         *     {@link Short }
         *     
         */
        public void setPlaceId(Short value) {
            this.placeId = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="address_street" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}float"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "addressStreet",
            "x",
            "y"
        })
        public static class Location {

            @XmlElement(name = "address_street", required = true)
            protected String addressStreet;
            protected float x;
            protected float y;

            /**
             * Obtiene el valor de la propiedad addressStreet.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAddressStreet() {
                return addressStreet;
            }

            /**
             * Define el valor de la propiedad addressStreet.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAddressStreet(String value) {
                this.addressStreet = value;
            }

            /**
             * Obtiene el valor de la propiedad x.
             * 
             */
            public float getX() {
                return x;
            }

            /**
             * Define el valor de la propiedad x.
             * 
             */
            public void setX(float value) {
                this.x = value;
            }

            /**
             * Obtiene el valor de la propiedad y.
             * 
             */
            public float getY() {
                return y;
            }

            /**
             * Define el valor de la propiedad y.
             * 
             */
            public void setY(float value) {
                this.y = value;
            }

        }

    }

}
