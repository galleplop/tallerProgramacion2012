package Entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Test {

    /*private void inicializarArticulos(EntityManager em) {
        List articulos = em.createQuery("SELECT a FROM Articulo a").
                getResultList();
        if (articulos.isEmpty()) {
            Articulo art1 = new Articulo(),
                    art2 = new Articulo(), art3 = new Articulo();
            art1.setNombre("Cable fibra optica");
            art1.setPrecio(150);
            art2.setNombre("Cable trenzado");
            art2.setPrecio(50);
            art3.setNombre("Cable coaxil");
            art3.setPrecio(100.5);
            em.getTransaction().begin();
            em.persist(art1);
            em.persist(art2);
            em.persist(art3);
            em.getTransaction().commit();
        }else{
            for (Object object : articulos) {
                System.out.println("Articulo: " + object);
            }
        }
    }*/

    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dispositivo_movilPU");
        EntityManager em = emf.createEntityManager();
        try {
            //Se inicializan si es necesario los articulos.
            //inicializarArticulos(em);

            //Se solicita una transaccion al entity manager

            em.getTransaction().begin();           

//            Comentario com = new Comentario();
//            com.setCalificacion(1);
//            com.setFecha("2012/12/12");
//            com.setIdCom("1");
//            com.setNickCliente("ElGroso");
//            com.setPersistido(true);
//            com.setTexto("que cagada");
//
//            em.persist(com);
//
//            Comentario com2 = new Comentario();
//            com2.setCalificacion(1);
//            com2.setFecha("2012/12/12");
//            com2.setIdCom("1.1");
//            com2.setNickCliente("Elpepe");
//            com2.setPersistido(true);
//            com2.setRespuestas(null);
//            if (com.getRespuestas()== null){
//                List l = new ArrayList();
//                l.add(com2);
//                com.setRespuestas(l);
//            }
//            com2.setTexto("que cagada nooo");
//
//            em.persist(com2);
//
//            Comentario com3= new Comentario();
//            com3.setCalificacion(1);
//            com3.setFecha("2012/12/12");
//            com3.setIdCom("2");
//            com3.setNickCliente("Eltoto");
//            com3.setPersistido(true);
//            com3.setTexto("que cagada nooo");
//
//            em.persist(com3);

            Comentario com4= new Comentario();
            com4.setCalificacion(345);
            com4.setFecha("2012/12/12");
            com4.setIdCom("0.1");
            com4.setNickCliente("ElSeba");
            com4.setPersistido(true);
            com4.setTexto("Me cago en todos!");

            em.persist(com4);


            em.getTransaction().commit();


            System.out.println("Encontre el juego ");

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
