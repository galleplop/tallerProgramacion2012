
package tprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataRechazada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataRechazada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomJuego" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dataRechazada", propOrder = {
    "nomJuego",
    "numVersion",
    "motivo",
    "idJuego"
})
public class DataRechazada {

    protected String nomJuego;
    protected String numVersion;
    protected String motivo;
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
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
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
