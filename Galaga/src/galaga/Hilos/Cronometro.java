/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;

import galaga.Galaga;
import javax.swing.JLabel;

/**
 *
 * @author Josh
 */
public class Cronometro extends Thread{
    private int segundos = 60;
    public int puntos = 0;
    JLabel tiempo, punteo;
    public Cronometro(JLabel tiempo, JLabel punteo){
        this.tiempo = tiempo;
        this.punteo = punteo;
    }
    
    @Override
    public void run() {
        try {
            while(segundos > 0 && Galaga.jugador.vivo){
                tiempo.setText(String.valueOf(segundos));
                punteo.setText(String.valueOf(puntos));
                sleep(1000);
                segundos--;
            }
            Galaga.jugador.vivo = false;
        } catch (InterruptedException ex) {
        }
    }
    
    
}