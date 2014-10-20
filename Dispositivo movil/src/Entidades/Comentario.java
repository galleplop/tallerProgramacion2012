/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import logica.DataBaseComentario;
import tprog.DataComentario;
import tprog.DataNodoComentario;

/**
 *
 * @author tprog129
 */
@Entity
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String IdCom;
    private String texto;
    private String fecha;
    private String NickCliente;
    private Integer Calificacion;
    private boolean persistido;

    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Comentario> respuestas;

    public List<Comentario> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Comentario> respuestas) {
        this.respuestas = respuestas;
    }

    public Integer getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(Integer Calificacion) {
        this.Calificacion = Calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNickCliente() {
        return NickCliente;
    }

    public void setNickCliente(String NickCliente) {
        this.NickCliente = NickCliente;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getIdCom() {
        return IdCom;
    }

    public void setIdCom(String IdCom) {
        this.IdCom = IdCom;
    }

    public boolean isPersistido() {
        return persistido;
    }

    public void setPersistido(boolean persistido) {
        this.persistido = persistido;
    }

    @Override
    public String toString() {
        return "Entidades.Comentario[id=" + IdCom + "]";
    }
    
    public DataBaseComentario obtenerInfo(){
        DataComentario dc = new DataComentario();
        dc.setFecha(this.fecha);
        dc.setId(this.IdCom);
        dc.setNick(this.NickCliente);
        dc.setPuntaje(this.Calificacion);
        dc.setTexto(this.texto);

        DataBaseComentario dnc = new DataBaseComentario();
        dnc.setContenido(dc);
        List<DataBaseComentario> lista = new ArrayList<DataBaseComentario>(); //No puede ser solo List
        for(int i = 0; i < this.respuestas.size(); i++){
            if(this.respuestas.get(i)!=null)
                lista.add(this.respuestas.get(i).obtenerInfo());
        }
        
        dnc.setRespuestas(lista);
        return dnc;
    }

}
