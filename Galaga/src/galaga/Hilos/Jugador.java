/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import galaga.*;
import java.awt.event.KeyEvent;
/**
 *
 * @author Josh
 */
public class Jugador extends Thread{
    
    JLabel graficoJugador;
    VentanaJuego ventana;
    private int velocidad = 20, tiroEspecial = 5; 
    private boolean  escudo = false;
    public boolean vivo = true, pausa = false;
    
    public Jugador(VentanaJuego ventanaJuego){
        ventana = ventanaJuego;
        graficoJugador = new JLabel();
        graficoJugador.setBounds(120,450,40,30);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Jugador.png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(graficoJugador.getWidth(), graficoJugador.getHeight(), Image.SCALE_SMOOTH));
        graficoJugador.setIcon(iconJugador);
        ventanaJuego.getContentPane().add(graficoJugador,ventanaJuego.getContentPane().getComponents().length-1);
        ventanaJuego.repaint();

    }
    
    public void keyPressed(KeyEvent e){
        if(vivo && !pausa){
            if(e.getKeyCode() == 39 && graficoJugador.getBounds().x < 270){
                graficoJugador.setLocation(graficoJugador.getLocation().x + (1*velocidad), graficoJugador.getLocation().y);
                ventana.escudo.setLocation(graficoJugador.getLocation().x-5, graficoJugador.getLocation().y - 10);
            }
            if(e.getKeyCode() == 37 && graficoJugador.getBounds().x > 0){
                graficoJugador.setLocation(graficoJugador.getLocation().x - (1*velocidad), graficoJugador.getLocation().y);
                ventana.escudo.setLocation(graficoJugador.getLocation().x-5, graficoJugador.getLocation().y - 10);
            }
            try {
                if(e.getKeyChar() == 106 && ((Galaga.disparos.isEmpty())?true : Galaga.disparos.get(Galaga.disparos.size() - 1).tiempoUltimo > 300 )&& Galaga.disparos.size() < 5){
                    Disparo disparo = new Disparo(graficoJugador.getBounds().x, ventana, ((tiroEspecial > 0) ? Disparo.BalaEspecial : Disparo.BalaNormal));
                    disparo.start();
                    if(tiroEspecial != 0){
                        tiroEspecial--;
                    }
                }
            }catch (Exception ex) {
            }
        
        }
    }
    
    public void chocar(){
        if(!escudo){
            Galaga.jugador.vivo = false;
        }else{
            escudo = false;
            ventana.escudo(false);
        }
    }
    
    public void reiniciar(){
        graficoJugador.setLocation(120,450);
        ventana.escudo.setLocation(graficoJugador.getLocation().x-5, graficoJugador.getLocation().y - 10);
        tiroEspecial = 0;
        escudo = false;
        vivo = true;
    }

    @Override
    public synchronized void run() {
        try{
            while(vivo){
                Thread.sleep(5);
                ventana.repaint();
                if(pausa){
                    wait();
                }
            }
        }catch(InterruptedException ex){
           
        }
        
    }
    
    
}
