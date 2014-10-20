/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author tprog129
 */
@Entity
public class Juego implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    
    private Integer id;
    private String nombreJuego;
    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="Juego_Comentario",
                joinColumns= @JoinColumn(name="IDJuego"),
                inverseJoinColumns= @JoinColumn(name="IDCOMENTARIO"))
    private List<Comentario> colComentarios;

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comentario> getColComentarios() {
        return colComentarios;
    }

    public void setColComentarios(List<Comentario> colComentarios) {
        this.colComentarios = colComentarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Juego[id=" + id + "nombreJuego="+ nombreJuego +"]";
    }

}
