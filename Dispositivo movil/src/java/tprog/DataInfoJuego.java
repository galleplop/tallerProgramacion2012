
package tprog;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataInfoJuego complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataInfoJuego">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nickDesarrollador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numUltimaVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jarName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iconName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tamanio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jarData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="imagenData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="iconoData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="listaCategorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="listaCompradores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comentarios" type="{http://tprog/}dataNodoComentario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="listaVersionAprobadas" type="{http://tprog/}dataVersion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="topTen" type="{http://tprog/}dataPuntaje" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInfoJuego", propOrder = {
    "nombre",
    "descripcion",
    "nickDesarrollador",
    "numUltimaVersion",
    "jarName",
    "imageName",
    "iconName",
    "tamanio",
    "precio",
    "id",
    "jarData",
    "imagenData",
    "iconoData",
    "listaCategorias",
    "listaCompradores",
    "comentarios",
    "listaVersionAprobadas",
    "topTen"
})
public class DataInfoJuego {

    protected String nombre;
    protected String descripcion;
    protected String nickDesarrollador;
    protected String numUltimaVersion;
    protected String jarName;
    protected String imageName;
    protected String iconName;
    protected double tamanio;
    protected double precio;
    protected int id;
    protected byte[] jarData;
    protected byte[] imagenData;
    protected byte[] iconoData;
    @XmlElement(nillable = true)
    protected List<String> listaCategorias;
    @XmlElement(nillable = true)
    protected List<String> listaCompradores;
    @XmlElement(nillable = true)
    protected List<DataNodoComentario> comentarios;
    @XmlElement(nillable = true)
    protected List<DataVersion> listaVersionAprobadas;
    @XmlElement(nillable = true)
    protected List<DataPuntaje> topTen;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the nickDesarrollador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickDesarrollador() {
        return nickDesarrollador;
    }

    /**
     * Sets the value of the nickDesarrollador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickDesarrollador(String value) {
        this.nickDesarrollador = value;
    }

    /**
     * Gets the value of the numUltimaVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumUltimaVersion() {
        return numUltimaVersion;
    }

    /**
     * Sets the value of the numUltimaVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumUltimaVersion(String value) {
        this.numUltimaVersion = value;
    }

    /**
     * Gets the value of the jarName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJarName() {
        return jarName;
    }

    /**
     * Sets the value of the jarName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJarName(String value) {
        this.jarName = value;
    }

    /**
     * Gets the value of the imageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the value of the imageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageName(String value) {
        this.imageName = value;
    }

    /**
     * Gets the value of the iconName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconName() {
        return iconName;
    }

    /**
     * Sets the value of the iconName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconName(String value) {
        this.iconName = value;
    }

    /**
     * Gets the value of the tamanio property.
     * 
     */
    public double getTamanio() {
        return tamanio;
    }

    /**
     * Sets the value of the tamanio property.
     * 
     */
    public void setTamanio(double value) {
        this.tamanio = value;
    }

    /**
     * Gets the value of the precio property.
     * 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * 
     */
    public void setPrecio(double value) {
        this.precio = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the jarData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getJarData() {
        return jarData;
    }

    /**
     * Sets the value of the jarData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setJarData(byte[] value) {
        this.jarData = ((byte[]) value);
    }

    /**
     * Gets the value of the imagenData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagenData() {
        return imagenData;
    }

    /**
     * Sets the value of the imagenData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagenData(byte[] value) {
        this.imagenData = ((byte[]) value);
    }

    /**
     * Gets the value of the iconoData property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIconoData() {
        return iconoData;
    }

    /**
     * Sets the value of the iconoData property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIconoData(byte[] value) {
        this.iconoData = ((byte[]) value);
    }

    /**
     * Gets the value of the listaCategorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCategorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListaCategorias() {
        if (listaCategorias == null) {
            listaCategorias = new ArrayList<String>();
        }
        return this.listaCategorias;
    }

    /**
     * Gets the value of the listaCompradores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCompradores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCompradores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListaCompradores() {
        if (listaCompradores == null) {
            listaCompradores = new ArrayList<String>();
        }
        return this.listaCompradores;
    }

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataNodoComentario }
     * 
     * 
     */
    public List<DataNodoComentario> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<DataNodoComentario>();
        }
        return this.comentarios;
    }

    /**
     * Gets the value of the listaVersionAprobadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaVersionAprobadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaVersionAprobadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataVersion }
     * 
     * 
     */
    public List<DataVersion> getListaVersionAprobadas() {
        if (listaVersionAprobadas == null) {
            listaVersionAprobadas = new ArrayList<DataVersion>();
        }
        return this.listaVersionAprobadas;
    }

    /**
     * Gets the value of the topTen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topTen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopTen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPuntaje }
     * 
     * 
     */
    public List<DataPuntaje> getTopTen() {
        if (topTen == null) {
            topTen = new ArrayList<DataPuntaje>();
        }
        return this.topTen;
    }

}
