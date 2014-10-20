
package tprog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataVersion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataVersion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tamanio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataVersion", propOrder = {
    "numV",
    "tamanio"
})
public class DataVersion {

    protected String numV;
    protected Double tamanio;

    /**
     * Gets the value of the numV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumV() {
        return numV;
    }

    /**
     * Sets the value of the numV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumV(String value) {
        this.numV = value;
    }

    /**
     * Gets the value of the tamanio property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTamanio() {
        return tamanio;
    }

    /**
     * Sets the value of the tamanio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTamanio(Double value) {
        this.tamanio = value;
    }

}
