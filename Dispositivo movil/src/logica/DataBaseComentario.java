
package logica;

import Entidades.Comentario;
import java.util.ArrayList;
import java.util.List;
import tprog.DataComentario;


public class DataBaseComentario {
        private DataComentario contenido;
        private List<DataBaseComentario> respuestas;

        public DataBaseComentario() {
        }

        public DataComentario getContenido() {
            return contenido;
        }

        public List<DataBaseComentario> getRespuestas() {
            return respuestas;
        }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(DataComentario contenido) {
        this.contenido = contenido;
    }

    /**
     * @param respuestas the respuestas to set
     */
    public void setRespuestas(List<DataBaseComentario> respuestas) {
        this.respuestas = respuestas;
    }
        
   public Comentario ObtenerInfo(){
        Comentario com = new Comentario();

        com.setCalificacion(this.contenido.getPuntaje());
        com.setFecha(this.contenido.getFecha());
        com.setIdCom(this.contenido.getId());
        com.setNickCliente(this.contenido.getNick());
        com.setTexto(this.contenido.getTexto());

        List<Comentario> resp = new ArrayList<Comentario>(); //No puede ser solo List
        for(int i = 0; i < this.respuestas.size(); i++){
            if(this.respuestas.get(i)!=null)
                resp.add(this.respuestas.get(i).ObtenerInfo());
        }

        com.setRespuestas(resp);
        return com;
   }
}

