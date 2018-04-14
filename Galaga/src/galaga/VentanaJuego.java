/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Josh
 */
public class VentanaJuego extends JFrame{
    
    int ancho = 300, alto = 450;
    
    public VentanaJuego() {
        super("Mini-Gálaga");
        setResizable(false);
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setLayout(null);
        ImageIcon icon = new ImageIcon("src//graficos//FondoR.jpg");
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, ancho, alto);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        getContentPane().add(fondo, -1);
        setVisible(true);
    }
    
    public void iniciar(){
        JLabel Jugador = new JLabel();
        Jugador.setBounds(120,300,50,50);
        ImageIcon imagJugador = new ImageIcon("src//graficos//Jugador.png");
        Icon iconJugador = new ImageIcon(imagJugador.getImage().getScaledInstance(Jugador.getWidth(), Jugador.getHeight(), Image.SCALE_SMOOTH));
        Jugador.setIcon(iconJugador);
        getContentPane().add(Jugador,0);
        repaint();
        validate();
    }
}
