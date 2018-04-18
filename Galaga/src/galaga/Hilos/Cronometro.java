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
    
    public void reiniciar(){
        segundos = 60;
        puntos = 0;
        tiempo.setText(String.valueOf(segundos));
        punteo.setText(String.valueOf(puntos));
    }
    
    @Override
    public synchronized void run() {
        try {               
                while(segundos > 0 && Galaga.jugador.vivo){
                    
                        sleep(1000);
                        segundos--;
                        tiempo.setText(String.valueOf(segundos));
                        punteo.setText(String.valueOf(puntos));
                        if(Galaga.jugador.pausa) {                    
                        wait();
                    }

                }
                Galaga.jugador.vivo = false;
            
           
        } catch (InterruptedException ex) {
        }
    }
    
    
}
