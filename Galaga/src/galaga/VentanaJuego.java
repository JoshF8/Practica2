/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;
import galaga.Hilos.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author Josh
 */
public class VentanaJuego extends JFrame{
    
    int ancho = 300, alto = 550;
    Jugador jugador;
    JLabel punteo, tiempo;
    
    public VentanaJuego() {
        super("Mini-Gálaga");
        setResizable(false);
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src//graficos//FondoR.jpg");
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, ancho, alto);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        tiempo = new JLabel();
        tiempo.setFont(new Font("Arial", Font.BOLD, 20));
        tiempo.setForeground(Color.WHITE);
        tiempo.setBounds(250, 10, 30, 30);
        punteo = new JLabel();
        punteo.setFont(new Font("Arial", Font.BOLD, 20));
        punteo.setForeground(Color.WHITE);
        punteo.setBounds(260, 40, 50, 30);
        getContentPane().add(tiempo);
        getContentPane().add(punteo);
        getContentPane().add(fondo, -1);
        setVisible(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                jugador.keyPressed(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                jugador.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
    }
    
    public void iniciar(){
        this.jugador = new Jugador(this);
        Galaga.jugador = jugador;
        this.jugador.start();
        CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
        creadorEnemigos.start();
        Cronometro cronometro = new Cronometro(tiempo, punteo);
        Galaga.cronometro = cronometro;
        cronometro.start();
    }
}
