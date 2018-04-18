/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga.Hilos;
import javax.swing.*;
import java.awt.*;
import galaga.*;
import java.awt.geom.Area;
/**
 *
 * @author Josh
 */
public class Item extends Thread{
    int velocidad = 1, tipoItem;
    JLabel graficoItem;
    VentanaJuego ventana;
    boolean vivo = true;
    
    public Item(int tipoItem){
        this.ventana = Galaga.ventana;
        this.tipoItem = tipoItem;
        graficoItem = new JLabel();
        graficoItem.setBounds((int)(Math.random()*241),-40,30,30);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Item"+ tipoItem + ".png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(graficoItem.getWidth(), graficoItem.getHeight(), Image.SCALE_SMOOTH));
        graficoItem.setIcon(iconJugador);
        ventana.getContentPane().add(graficoItem,ventana.getContentPane().getComponents().length-1);
        ventana.repaint();
        Galaga.items.add(this);
    }
    
    public void Colision(){
        Area forma1 = new Area(graficoItem.getBounds());
        Area forma2= new Area(Galaga.jugador.graficoJugador.getBounds());
        if(forma1.getBounds().intersects(forma2.getBounds())){
            destruir();
            if(tipoItem == 0){
                Galaga.jugador.tiroEspecial += 5;
            }else{
                Galaga.jugador.activarEscudo();
            }
        }
    }
    
    public void destruir(){
        try {
            Galaga.items.remove(this);
        graficoItem.setLocation(0, 600);
        } catch (Exception e) {
        }
        
    }
    
    @Override
    public synchronized void run() {
        try {             
            while(graficoItem.getLocation().y < 550 && Galaga.jugador.vivo){
                sleep(5);
                graficoItem.setLocation(graficoItem.getLocation().x, graficoItem.getLocation().y + (1*velocidad));
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
