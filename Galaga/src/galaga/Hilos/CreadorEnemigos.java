/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;

import galaga.Galaga;

/**
 *
 * @author Josh
 */
public class CreadorEnemigos extends Thread{
    
    static int ID;
    int id2;
    
    public CreadorEnemigos(){
        id2 = ID++;
    }

    @Override
    public synchronized void run() {
        try {
            while(Galaga.jugador.vivo){
                    sleep((long)(Math.random()*3)*1000 + 1000);
                    Enemigos enemigo = new Enemigos((int)(Math.random()*3));
                    enemigo.start();
                    if(Galaga.jugador.pausa) {                    
                        wait();
                    }
            }
        } catch (InterruptedException ex) {
        }
    }
    
}
