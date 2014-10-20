/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import Entidades.Comentario;
import Entidades.Puntaje;
import Exceptions.ExceptionLoggin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.WebServiceException;
import tprog.DataJuego;
import tprog.DataNodoComentario;

/**
 *
 * @author tprog127
 */
public class ControladorDispositivo {    
    private String nickLogueado;
    private int puntajeNoLogueado;
    private String IdComentario;
    private int idJuegoActual = 1; //Aca le metemos el id de simon. Que aun no no existe en la servidor :(

    private ControladorDispositivo() {
    }

    //Singleton Implementation
    public static ControladorDispositivo getInstance() {
        return ControladorDispositivoHolder.INSTANCE;
    }

    private static class ControladorDispositivoHolder {
        private static final ControladorDispositivo INSTANCE = new ControladorDispositivo();
    }
    
    //Metodos
    public String iniciarSesion(String nickmail, String password) throws WebServiceException, ExceptionLoggin{
        try{            
            Conexion conection = new Conexion();
            nickLogueado = conection.iniciarSesion(nickmail, password);
            
            //Controlo que compro de juego => No se si hacerlo aca o en metodo a parte.
            List<DataJuego> lstJuego = conection.obtenerListaJuegosCliente(nickLogueado);
            boolean comproJuego = false;
            if (lstJuego != null){
                
                for (Iterator<DataJuego> it = lstJuego.iterator(); it.hasNext();) {
                    DataJuego dataJuego = it.next();
                    if (this.idJuegoActual == dataJuego.getId()){
                        comproJuego = true;
                        break;
                    }
                }
            }
            
            if (!comproJuego){
                this.nickLogueado = null;
                throw new ExceptionLoggin("Usted no ha comprado el juego.");
            }
            
            return nickLogueado;
        } catch (ExceptionLoggin ex) {
            this.nickLogueado = null;
            throw ex;
        } catch (WebServiceException e){
            this.nickLogueado = null;
            throw e;        
        }        
    }

    public void cerrarSesion() throws WebServiceException{
        try{
            Conexion conection = new Conexion();
            conection.cerrarSesion();
            nickLogueado = null;
        }catch (WebServiceException e){
            throw e;
        }
        
    }

    public List<DataBaseComentario> consultarComentario(String nombreJuego) throws WebServiceException{
        try{
            Conexion conection = new Conexion();
            List<DataNodoComentario> l = conection.consultarComentario(nombreJuego);
            ControladorBase c = ControladorBase.getInstance();
            List<DataBaseComentario> listaGenerada = NodoToBase(l);
            c.guardarComentarios(listaGenerada);
            return listaGenerada;
        }catch (WebServiceException e){            
            throw e;          //return this.generarArbolComentarios(c.cargarComentarios());
        }
    }

    private List<DataBaseComentario> NodoToBase(List<DataNodoComentario> l){
        List<DataBaseComentario> theList = new ArrayList<DataBaseComentario>();

        for (Iterator<DataNodoComentario> it = l.iterator(); it.hasNext();) {
            DataNodoComentario nodo = it.next();
            DataBaseComentario dbc = new DataBaseComentario();
            dbc.setContenido(nodo.getContenido());
            dbc.setRespuestas(NodoToBase(nodo.getRespuestas()));
            theList.add(dbc);
        }

        return theList;
    }



    public void ingresarComentario(String nombreJuego, String texto, String fecha, int estrellas, String idComentario) throws WebServiceException {
        try{
            Conexion conection = new Conexion();
            conection.ingresarComentario(nombreJuego, texto, nickLogueado, fecha, estrellas, idComentario);
        }catch (WebServiceException e){
            ControladorBase c = ControladorBase.getInstance();
            c.guardarComentarioEnBase(nickLogueado, texto, fecha, estrellas, idComentario);
            throw e;
        }        
    }
    
    public List<DataBaseComentario> generarArbolComentarios(List<Comentario> listCom){
        
        List<DataBaseComentario> theList = new ArrayList<DataBaseComentario>();
        
        for (Iterator<Comentario> it = listCom.iterator(); it.hasNext();) {
            Comentario comentario = it.next();
            theList.add(comentario.obtenerInfo());
        }
        
        return theList;
    }

     public List<Comentario> generarListaDataNodoCom(List<DataBaseComentario> listCom){

        List<Comentario> theList = new ArrayList<Comentario>();

        for (Iterator<DataBaseComentario> it = listCom.iterator(); it.hasNext();) {
            DataBaseComentario dbc = it.next();            
            theList.add(dbc.ObtenerInfo());
        }

        return theList;
    }

    public List<Puntaje> obtenerTopTen(int idJuego) throws WebServiceException{
        ControladorBase c = ControladorBase.getInstance();
        try{
            Conexion conection = new Conexion();
            List<Puntaje> l = conection.obtenerTopTen(idJuego);
            c.guardarPuntajes(l);
            return conection.obtenerTopTen(idJuego);
        }catch (WebServiceException e){
            return c.cargarPuntajes();
        }
    }

    public boolean perteneceTopTen(int idJuego, int puntaje) throws WebServiceException{
        try{
            Conexion conection = new Conexion();
            puntajeNoLogueado = puntaje;
            return conection.perteneceTopTen(idJuego, puntaje);
        }catch (WebServiceException e){
            throw e;
        }
    }
    
    public boolean agregarPuntajeTopTen(int idJuego, int puntaje){
      try{
        Conexion conection = new Conexion();
        return conection.newScoreAtGame(idJuego, puntaje, nickLogueado);
      } catch(WebServiceException e){
        ControladorBase c = ControladorBase.getInstance();
        c.guardarPuntajeEnBase(idJuego, nickLogueado, puntaje);
        return true;
      }
    }

    public String obtenerNickLogueado(){
        return this.nickLogueado;
    }

    public int ObtenerPuntajeNoLogueado(){
        return this.puntajeNoLogueado;
    }
    public String getIdComentario() {
        return IdComentario;
    }

    public void setIdComentario(String IdComentario) {
        this.IdComentario = IdComentario;
    }

 }
