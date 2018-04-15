/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;

import galaga.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Josh
 */
public class Disparo extends Thread{
    
    public static final int BalaNormal = 0, BalaEspecial = 1, BalaEnemigo = 2;
    private String balas[] = {"BalaNormalPlayer.png"};
    JLabel graficoBala;
    VentanaJuego ventana;
    int velocidad = 2;
    public double tiempoUltimo = 751;
    
    public Disparo(int x, VentanaJuego ventanaJuego, int tipoBala){
        ventana = ventanaJuego;
        graficoBala = new JLabel();
        graficoBala.setBounds(x + 27, 300, 6, 20);
        ImageIcon imagBala = new ImageIcon("src//graficos//" + balas[tipoBala]);
        Icon iconJugador = new ImageIcon(imagBala.getImage().getScaledInstance(graficoBala.getWidth(), graficoBala.getHeight(), Image.SCALE_SMOOTH));
        graficoBala.setIcon(iconJugador);
        ventanaJuego.getContentPane().add(graficoBala,ventanaJuego.getContentPane().getComponents().length-1);
        tiempoUltimo = 0;
        ventanaJuego.repaint();
        Galaga.disparos.add(this);
    }
    
    @Override
    public void run() {
        try {
            while (graficoBala.getLocation().y > - 20) {                    
                sleep(5);
                tiempoUltimo += 5; 
                graficoBala.setLocation(graficoBala.getLocation().x, graficoBala.getLocation().y - (1*velocidad));
                ventana.repaint();
            }
            Galaga.disparos.remove(this);
        } catch (InterruptedException ex) {
            System.out.println("aca");
        }
    }
    
}
