/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import galaga.VentanaJuego;
import java.awt.event.KeyEvent;
/**
 *
 * @author Josh
 */
public class Jugador extends Thread{
    
    JLabel graficoJugador;
    VentanaJuego ventana;
    int velocidad = 15;
    boolean vivo = true, movimiento = true;
    
    public Jugador(VentanaJuego ventanaJuego){
        ventana = ventanaJuego;
        graficoJugador = new JLabel();
        graficoJugador.setBounds(120,320,60,50);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Jugador.png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(graficoJugador.getWidth(), graficoJugador.getHeight(), Image.SCALE_SMOOTH));
        graficoJugador.setIcon(iconJugador);
        ventanaJuego.getContentPane().add(graficoJugador,ventanaJuego.getContentPane().getComponents().length-1);
        ventanaJuego.repaint();

    }
    
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == 39 && graficoJugador.getBounds().x < 240){
            graficoJugador.setLocation(graficoJugador.getLocation().x + (1*velocidad), graficoJugador.getLocation().y);
        }
        if(e.getKeyCode() == 37 && graficoJugador.getBounds().x > 0){
            graficoJugador.setLocation(graficoJugador.getLocation().x - (1*velocidad), graficoJugador.getLocation().y);
        }
    }

    @Override
    public void run() {
        try{
            while(vivo){
                Thread.sleep(5);
                ventana.repaint();
            }
        }catch(InterruptedException ex){
            
        }
        
    }
    
    
}
