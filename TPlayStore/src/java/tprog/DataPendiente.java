
package tprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataPendiente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPendiente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomJuego" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nickDesarrollador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idJuego" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPendiente", propOrder = {
    "nomJuego",
    "nickDesarrollador",
    "numVersion",
    "idJuego"
})
public class DataPendiente {

    protected String nomJuego;
    protected String nickDesarrollador;
    protected String numVersion;
    protected int idJuego;

    /**
     * Gets the value of the nomJuego property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomJuego() {
        return nomJuego;
    }

    /**
     * Sets the value of the nomJuego property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomJuego(String value) {
        this.nomJuego = value;
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
     * Gets the value of the numVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumVersion() {
        return numVersion;
    }

    /**
     * Sets the value of the numVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumVersion(String value) {
        this.numVersion = value;
    }

    /**
     * Gets the value of the idJuego property.
     * 
     */
    public int getIdJuego() {
        return idJuego;
    }

    /**
     * Sets the value of the idJuego property.
     * 
     */
    public void setIdJuego(int value) {
        this.idJuego = value;
    }

}
