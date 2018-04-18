/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;

import galaga.Galaga;
import static java.lang.Thread.sleep;

/**
 *
 * @author Josh
 */
public class CreadorItems extends Thread{
    static int ID;
    int id2;
    
    public CreadorItems(){
        id2 = ID++;
    }
    
     @Override
    public synchronized void run() {
        try {
            while(Galaga.jugador.vivo){
                    sleep((long)(Math.random()*3)*1000 + 5000);
                    int tipo = 0;
                    double valor = Math.random()*10;
                    if(valor > 7.5){
                        tipo = 1;
                    }
                    Item item = new Item(tipo);
                    item.start();
                    if(Galaga.jugador.pausa) {                    
                        wait();
                    }
            }
        } catch (InterruptedException ex) {
        }
    }
}
