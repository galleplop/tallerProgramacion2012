
package tprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataJuego complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataJuego">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iconName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imagenData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="iconoData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="eliminado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataJuego", propOrder = {
    "nombre",
    "dsc",
    "imageName",
    "iconName",
    "precio",
    "id",
    "imagenData",
    "iconoData",
    "eliminado"
})
public class DataJuego {

    protected String nombre;
    protected String dsc;
    protected String imageName;
    protected String iconName;
    protected double precio;
    protected int id;
    protected byte[] imagenData;
    protected byte[] iconoData;
    protected boolean eliminado;

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
     * Gets the value of the dsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDsc() {
        return dsc;
    }

    /**
     * Sets the value of the dsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDsc(String value) {
        this.dsc = value;
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
     * Gets the value of the eliminado property.
     * 
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * Sets the value of the eliminado property.
     * 
     */
    public void setEliminado(boolean value) {
        this.eliminado = value;
    }

}
