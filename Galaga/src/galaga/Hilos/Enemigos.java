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
public class Enemigos extends Thread{
    
    JLabel graficoEnemigo;
    VentanaJuego ventana;
    int velocidad = 1;
    boolean vivo = true;
    
    public Enemigos(int tipoEnemigo){
        this.ventana = Galaga.ventana;
        graficoEnemigo = new JLabel();
        graficoEnemigo.setBounds((int)(Math.random()*241),0,60,50);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Enemigo"+ tipoEnemigo + ".png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(graficoEnemigo.getWidth(), graficoEnemigo.getHeight(), Image.SCALE_SMOOTH));
        graficoEnemigo.setIcon(iconJugador);
        ventana.getContentPane().add(graficoEnemigo,ventana.getContentPane().getComponents().length-1);
        ventana.repaint();
        Galaga.enemigos.add(this);
    }

    @Override
    public void run() {
        try {
            while (graficoEnemigo.getLocation().y > - 20) {                    
                sleep(5);
                graficoEnemigo.setLocation(graficoEnemigo.getLocation().x, graficoEnemigo.getLocation().y + (1*velocidad));
                ventana.repaint();
            }
            Galaga.enemigos.remove(this);
        } catch (InterruptedException ex) {
            System.out.println("aca");
        }
    }
    
    
}
