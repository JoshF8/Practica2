/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;

import galaga.Hilos.*;
import java.awt.List;
import java.util.ArrayList;
/**
 *
 * @author Josh
 */
public class Galaga {
    /**
     * @param args the command line arguments
     */
    public static Jugador jugador; 
    public static ArrayList<Disparo> disparos = new ArrayList<Disparo>();
    public static ArrayList<Enemigos> enemigos = new ArrayList<Enemigos>();
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<String> nombresTop = new ArrayList<>();
    public static ArrayList<Integer> puntosTop = new ArrayList<>();
    public static VentanaJuego ventana;
    public static Cronometro cronometro;
    public static CreadorEnemigos creadorEnemigos;
    public static CreadorItems creadorItems;
    public static boolean iniciado = false;
    
    public static void main(String[] args) {
        ventana = new VentanaJuego();
    }
    
}
