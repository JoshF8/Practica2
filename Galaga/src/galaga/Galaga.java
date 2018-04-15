/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import galaga.Hilos.*;
/**
 *
 * @author Josh
 */
public class Galaga {
    /**
     * @param args the command line arguments
     */
    public static Jugador jugador; 
    
    
    public static void main(String[] args) {
        VentanaJuego ventanaJuego = new VentanaJuego();
        ventanaJuego.iniciar();
    }
    
}
