/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;

import galaga.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
/**
 *
 * @author Josh
 */
public class Disparo extends Thread{
    
    public static final int BalaNormal = 0, BalaEspecial = 1, BalaEnemigo = 2;
    private String balas[] = {"BalaNormalPlayer.png", "BalaEspecialPlayer.png", "BalaEnemigo.png"};
    JLabel graficoBala;
    VentanaJuego ventana;
    int velocidad = 2, tipoBala;
    public double tiempoUltimo = 751;
    
    public Disparo(int x, int y,VentanaJuego ventanaJuego, int tipoBala){
        ventana = ventanaJuego;
        this.tipoBala = tipoBala;
        graficoBala = new JLabel();
        graficoBala.setBounds(x + 17, y, 6, 20);
        ImageIcon imagBala = new ImageIcon("src//graficos//" + balas[tipoBala]);
        Icon iconJugador = new ImageIcon(imagBala.getImage().getScaledInstance(graficoBala.getWidth(), graficoBala.getHeight(), Image.SCALE_SMOOTH));
        graficoBala.setIcon(iconJugador);
        ventanaJuego.getContentPane().add(graficoBala,ventanaJuego.getContentPane().getComponents().length-1);
        tiempoUltimo = 0;
        ventanaJuego.repaint();
        Galaga.disparos.add(this);
    }
    
    public void Colision(){
        Area forma1 = new Area(graficoBala.getBounds());
        Area forma2= new Area(Galaga.jugador.graficoJugador.getBounds());
        if(forma1.getBounds().intersects(forma2.getBounds())){
            destruir();
            Galaga.jugador.chocar();
        }
    }
    
    public void destruir(){
        try {
            graficoBala.setLocation(0, -30);
            Galaga.disparos.remove(this);
        } catch (Exception e) {
        }
        
    }
    
    @Override
    public synchronized void run() {
        try { 
            while(((graficoBala.getLocation().y > - 20 && tipoBala != 2)|| (tipoBala == 2 && graficoBala.getLocation().y < 550)) && Galaga.jugador.vivo){                 
                sleep(5);
                tiempoUltimo += 5; 
                graficoBala.setLocation(graficoBala.getLocation().x, graficoBala.getLocation().y + ((tipoBala == 2)?1:-1)*(1*velocidad*((tipoBala ==2)?2:1)));
                ventana.repaint();
                if(tipoBala == 2){
                    Colision();
                }
                if(Galaga.jugador.pausa) {                    
                    wait();
                }
            }
            destruir();
            
        } catch (InterruptedException ex) {
            System.out.println("aca");
        }
    }
    
}
