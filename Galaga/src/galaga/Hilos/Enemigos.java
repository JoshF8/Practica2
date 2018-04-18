/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;
import galaga.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Josh
 */
public class Enemigos extends Thread{
    
    JLabel graficoEnemigo;
    VentanaJuego ventana;
    int velocidad = 1;
    boolean vivo = true;
    int vida = 3, tipoEnemigo;
    
    public Enemigos(int tipoEnemigo){
        this.ventana = Galaga.ventana;
        this.tipoEnemigo = tipoEnemigo;
        graficoEnemigo = new JLabel();
        graficoEnemigo.setBounds((int)(Math.random()*241),-40,40,30);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Enemigo"+ tipoEnemigo + ".png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(graficoEnemigo.getWidth(), graficoEnemigo.getHeight(), Image.SCALE_SMOOTH));
        graficoEnemigo.setIcon(iconJugador);
        ventana.getContentPane().add(graficoEnemigo,ventana.getContentPane().getComponents().length-1);
        ventana.repaint();
        Galaga.enemigos.add(this);
        velocidad += tipoEnemigo;
        vida = vida - tipoEnemigo;
        if(tipoEnemigo == 2){
            vida = -1;
        }
    }
    
    private void Colision(){
        Area forma1 = new Area(graficoEnemigo.getBounds());
        Area forma2;
        boolean valor = false, especial = false;
        try{
            for(Disparo disparo : Galaga.disparos){
                forma2 = new Area(disparo.graficoBala.getBounds());
                if(forma1.getBounds().intersects(forma2.getBounds()) && disparo.tipoBala != 2){
                    valor = true;
                    if(disparo.tipoBala == 1){
                        especial = true;
                    }
                    disparo.destruir();
                    break;
                }
            }
            if(valor){
                if(especial && tipoEnemigo != 2){
                    vida = 0;
                }else{
                    vida--;
                }
                if(vida == 0){
                    destruir();
                    Galaga.cronometro.puntos += (tipoEnemigo + 1)*10;      
                }
            }
            forma2 = new Area(Galaga.jugador.graficoJugador.getBounds());
            if(forma1.getBounds().intersects(forma2.getBounds())){
                destruir();
                Galaga.jugador.chocar();
            }
        }catch(Exception ex){
        
        }
    }
    
    public void destruir(){
        Galaga.enemigos.remove(this);
        graficoEnemigo.setLocation(0, 600);
    }

    @Override
    public synchronized void run() {
        try {             
            while(graficoEnemigo.getLocation().y < 550 && Galaga.jugador.vivo){
                sleep(5);
                graficoEnemigo.setLocation(graficoEnemigo.getLocation().x, graficoEnemigo.getLocation().y + (1*velocidad));
                ventana.repaint();
                Colision();
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
