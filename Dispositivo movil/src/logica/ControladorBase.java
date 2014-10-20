
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import Entidades.Comentario;
import Entidades.Puntaje;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tprog129
 */
public class ControladorBase {

    private ControladorBase() {
    }

    public static ControladorBase getInstance() {
        return ControladorBaseHolder.INSTANCE;
    }

    private static class ControladorBaseHolder {
        private static final ControladorBase INSTANCE = new ControladorBase();
    }


     public List<Comentario> cargarComentarios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
        EntityManager em = emf.createEntityManager();
        List<Comentario> listaAnidada = null;
        try {
            
            String consulta = "SELECT m FROM Comentario m";
            List lstCom = (List)em.createQuery(consulta).getResultList();
            
            listaAnidada = new ArrayList<Comentario>();
            
            for (Iterator it = lstCom.iterator(); it.hasNext();) {
                
                Comentario object = (Comentario) it.next();
                String idComentario = object.getIdCom();
                
                List<Comentario> aux = listaAnidada;
                String[] parser = new String[idComentario.length()+1];
                parser = object.getIdCom().split("\\.");
                Comentario auxComentario;
                
                for (int i = 0; i<parser.length-1;i++){
                    auxComentario = aux.get(Integer.parseInt(parser[i]));
                    aux = auxComentario.getRespuestas();
                }
                if (!aux.contains(object))
                    aux.add(object);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
            emf.close();
        }
        return listaAnidada;
    }

     

     public void guardarComentarios(List<DataBaseComentario> lista){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
        EntityManager em = emf.createEntityManager();
         try {

             em.getTransaction().begin();
             String consulta = "DELETE FROM Comentario";
             em.createQuery(consulta).executeUpdate();
             em.getTransaction().commit();

            //Se solicita una transaccion al entity manager
            em.getTransaction().begin();
            ControladorDispositivo c = ControladorDispositivo.getInstance();           

            Iterator it = lista.iterator();

            while (it.hasNext()){
                DataBaseComentario dnc = (DataBaseComentario) it.next();
                Comentario com = new Comentario();
                com.setCalificacion(dnc.getContenido().getPuntaje());
                com.setFecha(dnc.getContenido().getFecha());
                com.setIdCom(dnc.getContenido().getId());
                com.setNickCliente(dnc.getContenido().getNick());
                com.setTexto(dnc.getContenido().getTexto());                
                com.setRespuestas(c.generarListaDataNodoCom(dnc.getRespuestas()));

                em.persist(com);
            }

            consulta = "UPDATE Comentario c SET c.persistido = 1 ";
            em.createQuery(consulta).executeUpdate();
            
            em.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();

            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
     }
     
     public List<Puntaje> cargarPuntajes(){
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
        EntityManager em = emf.createEntityManager();
        List<Puntaje> listaAnidada = null;
        try {
            
            String consulta = "SELECT m FROM Puntaje m ORDER BY m.puntos ASC";
            List lstCom = (List)em.createQuery(consulta).getResultList();
            
            listaAnidada = new ArrayList<Puntaje>();
            
            for (Iterator it = lstCom.iterator(); it.hasNext();) {
                
                Puntaje object = (Puntaje)it.next();
                listaAnidada.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
            emf.close();
        }
        
        return listaAnidada;
     }

     public void guardarPuntajes(List<Puntaje> lista){

         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             String consulta = "DELETE FROM Puntaje";
             em.createQuery(consulta).executeUpdate();
             em.getTransaction().commit();

              em.getTransaction().begin();

              Iterator it = lista.iterator();

              while (it.hasNext()){              
                 
                  Puntaje p = (Puntaje) it.next();
                  em.persist(p);
                  //em.flush();
              }
              
              em.getTransaction().commit();
            
         } catch (Exception e){
             e.printStackTrace();

         }

     }


    public void guardarPuntajeEnBase(int idJuego, String nick, int puntaje){

         EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
         EntityManager em = emf.createEntityManager();
         try {

                em.getTransaction().begin();

                Puntaje p = new Puntaje();
                p.setIdJuego(idJuego);
                p.setNick(nick);
                p.setPersistido(false);
                p.setPuntos(puntaje);

                em.persist(p);

                String consulta = "SELECT COUNT(m) FROM Puntaje m";
                Number cantidad = (Number) em.createQuery(consulta).getSingleResult();   

                em.getTransaction().commit();                

                if (cantidad.intValue()>10){
                    em.getTransaction().begin();

                    String sql = "SELECT m FROM Puntaje m ORDER BY m.puntos DESC";
                    Query query = em.createQuery(sql);
                    query.setFirstResult(0);
                    query.setMaxResults(10);
                    List topTen = query.getResultList();               
                
                    this.guardarPuntajes(topTen);
                }

        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
        public void guardarComentarioEnBase(String nick, String texto, String fecha, int estrellas, String idComentario){

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
             EntityManager em = emf.createEntityManager();
             try {
                if (idComentario != null && !idComentario.equals("")){
                     em.getTransaction().begin();
                     String consulta = "SELECT c FROM Comentario c WHERE c.IdCom ='"+ idComentario +"'";
                     Comentario c = (Comentario) em.createQuery(consulta).getSingleResult();
                     em.getTransaction().commit();

                     int i = c.getRespuestas().size();
                     idComentario = idComentario +"."+ Integer.toString(i);

                     /*********************************************************/
                        em.getTransaction().begin();

                        Comentario com = new Comentario();
                        com.setCalificacion(estrellas);
                        com.setFecha(fecha);
                        com.setIdCom(idComentario);
                        com.setNickCliente(nick);
                        com.setPersistido(false);
                        com.setRespuestas(null);
                        com.setTexto(texto);
                        em.persist(com);
                        List<Comentario> resp = c.getRespuestas();
                        resp.add(com);
                        c.setRespuestas(resp);

                        em.getTransaction().commit();
                     /*********************************************************/

                }
                else{
                    em.getTransaction().begin();
                    String consulta = "SELECT c FROM Comentario c";
                    List c = (List) em.createQuery(consulta).getResultList();
                    int cantidad = 0;
                    for (Iterator it = c.iterator(); it.hasNext();) {
                        Comentario o = (Comentario) it.next();
                        cantidad += o.getRespuestas().size();
                    }

                    em.getTransaction().commit();
                    idComentario = Integer.toString(c.size() - cantidad);

                    /*********************************************************/
                        em.getTransaction().begin();

                        Comentario com = new Comentario();
                        com.setCalificacion(estrellas);
                        com.setFecha(fecha);
                        com.setIdCom(idComentario);
                        com.setNickCliente(nick);
                        com.setPersistido(false);
                        com.setRespuestas(null);
                        com.setTexto(texto);
                        em.persist(com);

                        em.getTransaction().commit();
                     /*********************************************************/
                }
               
             }catch(Exception e2) {
                    e2.printStackTrace();
             }
        }



        public List<Puntaje> MandarPuntajesAServidor(){

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
            EntityManager em = emf.createEntityManager();
            try {

                String consulta = "SELECT m FROM Puntaje m WHERE m.persistido = 0";
                List lstpuntajes = (List)em.createQuery(consulta).getResultList();

                em.getTransaction().begin();

                consulta = "DELETE FROM Puntaje m WHERE m.persistido = 0";
                em.createQuery(consulta).executeUpdate();

                em.getTransaction().commit();
                
                return lstpuntajes;

            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }


        }


        public List<Comentario> MandarComentariosAServidor(){

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
            EntityManager em = emf.createEntityManager();
            try {

                String consulta = "SELECT m FROM Comentario m WHERE m.persistido = 0";
                List lstCom = (List)em.createQuery(consulta).getResultList();

                em.getTransaction().begin();

                consulta = "DELETE FROM Puntaje m WHERE m.persistido = 0";
                em.createQuery(consulta).executeUpdate();

                em.getTransaction().commit();
                
                return lstCom;

            } catch(Exception e) {
                return null;
            }


        }
 }
