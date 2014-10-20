/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tprog126
 */
public class Motor {
    private List<Integer> secuenciaJuego;

    public Motor(){
        this.secuenciaJuego = new ArrayList<Integer>();
    }
    public boolean comparar(int indice, int valor){
        if(this.secuenciaJuego.get(indice)==valor){
            return true;
        }else{
            return false;
        }

    }
    public List<Integer> aumentarSecuencia(){
        Random randomGenerator = new Random();
        int i = randomGenerator.nextInt(4);
        this.secuenciaJuego.add(new Integer(i));
        return this.secuenciaJuego;
    }

    public boolean llegoAlFinal(int indice){
        return indice == secuenciaJuego.size()-1;
    }

    public int obtenerPuntaje(){
        return secuenciaJuego.size()-1;
    }
}
