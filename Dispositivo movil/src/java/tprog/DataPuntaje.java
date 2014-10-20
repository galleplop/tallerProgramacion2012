
package tprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataPuntaje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPuntaje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nickUsr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puntaje" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPuntaje", propOrder = {
    "nickUsr",
    "puntaje"
})
public class DataPuntaje {

    protected String nickUsr;
    protected int puntaje;

    /**
     * Gets the value of the nickUsr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickUsr() {
        return nickUsr;
    }

    /**
     * Sets the value of the nickUsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickUsr(String value) {
        this.nickUsr = value;
    }

    /**
     * Gets the value of the puntaje property.
     * 
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Sets the value of the puntaje property.
     * 
     */
    public void setPuntaje(int value) {
        this.puntaje = value;
    }

}
