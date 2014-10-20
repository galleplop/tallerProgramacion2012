
package tprog;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataNodoComentario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataNodoComentario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenido" type="{http://tprog/}dataComentario" minOccurs="0"/>
 *         &lt;element name="respuestas" type="{http://tprog/}dataNodoComentario" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataNodoComentario", propOrder = {
    "contenido",
    "respuestas"
})
public class DataNodoComentario {

    protected DataComentario contenido;
    @XmlElement(nillable = true)
    protected List<DataNodoComentario> respuestas;

    /**
     * Gets the value of the contenido property.
     * 
     * @return
     *     possible object is
     *     {@link DataComentario }
     *     
     */
    public DataComentario getContenido() {
        return contenido;
    }

    /**
     * Sets the value of the contenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataComentario }
     *     
     */
    public void setContenido(DataComentario value) {
        this.contenido = value;
    }

    /**
     * Gets the value of the respuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the respuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRespuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataNodoComentario }
     * 
     * 
     */
    public List<DataNodoComentario> getRespuestas() {
        if (respuestas == null) {
            respuestas = new ArrayList<DataNodoComentario>();
        }
        return this.respuestas;
    }

}
