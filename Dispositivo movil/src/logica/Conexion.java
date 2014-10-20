/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import Entidades.Puntaje;
import Exceptions.ExceptionLoggin;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import tprog.ControladorMovilService;
import tprog.DataCliente;
import tprog.DataInfoJuego;
import tprog.DataJuego;
import tprog.DataNodoComentario;
import tprog.DataPerfil;
import tprog.DataPuntaje;
import tprog.InterfazMovil;


/**
 *
 * @author tprog127
 */
public class Conexion {
    
    private Properties p;

    public Conexion(){
        FileInputStream propFile = null;
        try {

            String userHome = System.getProperty("user.home");
            propFile = new FileInputStream(userHome + "/TPlay/config.properties");
            try {
                p = new Properties(System.getProperties());
                p.load(propFile);

            } catch (IOException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
             finally {
                try {
                    propFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
        
     public String iniciarSesion(String nickMail, String password) throws WebServiceException, ExceptionLoggin{
        
        try {
            URL url = new URL(p.getProperty("rutaServiceMovil")+"?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            String nicklogueado = iMovil.iniciarSesion(nickMail, password);
            if (!nicklogueado.equals("")){
                
                DataPerfil user;
                if (iMovil.perteneceUsuario(nickMail)||(iMovil.perteneceUsuarioMail(nickMail))){
                    
                    user = iMovil.verPerfil(nicklogueado);
                    if(user instanceof DataCliente){
                        
                        return user.getNick();
                    } else {
                        throw new ExceptionLoggin("Lo siento " + nicklogueado + ".\nNecesita una cuenta de Cliente.");
                    }
                }
            } else {
                throw new ExceptionLoggin("Usuario o contraseña incorrectos.");
            }
            
         }catch (WebServiceException e) {
             throw e;
        }catch (MalformedURLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
        return "";
      }

     public void cerrarSesion() throws WebServiceException{
        try {
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            
         }catch (WebServiceException e){
             throw e;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

     public List<DataNodoComentario> consultarComentario(String nombreJuego) throws WebServiceException{
        try {
            URL url = new URL(p.getProperty("rutaServiceMovil")+"?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            return iMovil.seleccionarJuegoPorID(1).getComentarios();
        }catch(WebServiceException e){
            throw e;
        }catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }

     public void ingresarComentario(String nombreJuego, String texto, String nickLogueado, String fecha, int estrellas, String id) throws WebServiceException{
        try {
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            String idSession = iMovil.obtenerIdAutogenerado(nickLogueado);
            iMovil.setNombreJuego(nombreJuego, idSession);
            if (iMovil.ingresarDatos(texto, fecha, nickLogueado, estrellas, idSession)){
                iMovil.agregarComentario(id, idSession);
            }
         }catch(WebServiceException e){             
             throw e;
         }catch (NullPointerException ex){
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

     public List<Puntaje> /*TreeMap<Integer, String>*/ obtenerTopTen(int idJuego) throws WebServiceException{
         try{
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            DataInfoJuego dij = iMovil.actualizarTopTen(idJuego);

            List<DataPuntaje> lis = dij.getTopTen();
            
            List <Puntaje> lisPuntos = new ArrayList<Puntaje>();
            for (int i =0; i<lis.size(); i++) {
                DataPuntaje entry = lis.get(i);
                //treeScore.put(entry.getKey(), entry.getValue());
                Puntaje puntaje = new Puntaje();
                puntaje.setId(new Integer(i+1));
                puntaje.setIdJuego(new Integer(idJuego));
                puntaje.setNick(entry.getNickUsr());
                puntaje.setPuntos(entry.getPuntaje());
                puntaje.setPersistido(true);
                lisPuntos.add(puntaje);
            }
            
            return lisPuntos;
         }catch (WebServiceException e){
            throw e;
         } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return null;
    }
     
    public boolean newScoreAtGame(int gameId, int score, String nickUser) throws WebServiceException{ 
        try{
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            
            return iMovil.agregarTopTen(gameId, score, nickUser);
         }catch (WebServiceException e){
            throw e;
         } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return false;
    }

    public boolean perteneceTopTen(int idJuego, int puntaje) throws WebServiceException{
        try {
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            return iMovil.perteneceTopTen(idJuego, puntaje);
        } catch (WebServiceException e){
            throw e;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public List<DataJuego> obtenerListaJuegosCliente(String nickCliente) throws WebServiceException{
        try{
            URL url = new URL(p.getProperty("rutaServiceMovil") + "?wsdl");
            QName qName = new QName("http://tprog/", "ControladorMovilService");
            InterfazMovil iMovil = new ControladorMovilService(url, qName).getControladorMovilPort();
            
            return iMovil.listarJuegosCliente(nickCliente).getLista();
         }catch (WebServiceException e){
            throw e;
         } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }

}
