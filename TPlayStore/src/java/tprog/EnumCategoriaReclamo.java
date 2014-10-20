
package tprog;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enumCategoriaReclamo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="enumCategoriaReclamo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MAL_FUNCIONAMIENTO"/>
 *     &lt;enumeration value="CONTENIDO_INAPROPIADO"/>
 *     &lt;enumeration value="FACTURACION_ERRONEA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumCategoriaReclamo")
@XmlEnum
public enum EnumCategoriaReclamo {

    MAL_FUNCIONAMIENTO,
    CONTENIDO_INAPROPIADO,
    FACTURACION_ERRONEA;

    public String value() {
        return name();
    }

    public static EnumCategoriaReclamo fromValue(String v) {
        return valueOf(v);
    }

}
