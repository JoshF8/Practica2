/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaga;
import galaga.Hilos.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author Josh
 */
public class VentanaJuego extends JFrame implements ActionListener{
    
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
        tiempo = new JLabel("60");
        tiempo.setFont(new Font("Arial", Font.BOLD, 20));
        tiempo.setForeground(Color.WHITE);
        tiempo.setBounds(250, 40, 30, 30);
        punteo = new JLabel("0");
        punteo.setFont(new Font("Arial", Font.BOLD, 20));
        punteo.setForeground(Color.WHITE);
        punteo.setBounds(260, 70, 50, 30);
        JMenuBar menu = new JMenuBar();
        JMenuItem juegoNuevo = new JMenuItem("Juego nuevo"), pausar = new JMenuItem("Pausa"), renuadar = new JMenuItem("Renuadar");
        juegoNuevo.addActionListener(this);
        pausar.addActionListener(this);
        renuadar.addActionListener(this);
        menu.add(juegoNuevo);
        menu.add(pausar);
        menu.add(renuadar);
        menu.setBounds(0,0,300,20);
        getContentPane().add(tiempo);
        getContentPane().add(punteo);
        getContentPane().add(menu);
        getContentPane().add(fondo, -1);
        setJMenuBar(menu);
        setVisible(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    jugador.keyPressed(e);
                } catch (Exception ex) {
                }
                
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    jugador.keyPressed(e);
                } catch (Exception ex) {
                }
                
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
        Galaga.creadorEnemigos = creadorEnemigos;
        Cronometro cronometro = new Cronometro(tiempo, punteo);
        Galaga.cronometro = cronometro;
        cronometro.start();
    }
    
    public void renuadar(){
        try {
            Galaga.jugador.pausa = false;
            for(Enemigos enemigo:Galaga.enemigos){
                synchronized (enemigo){
                    enemigo.notify();
                }
            }
            for(Disparo disparo : Galaga.disparos){
                synchronized (disparo){
                    disparo.notify();
                }
            }
            synchronized (Galaga.creadorEnemigos){
                Galaga.creadorEnemigos.notify();
            }
            synchronized(Galaga.cronometro){
                Galaga.cronometro.notify();
            }
            
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Juego nuevo":
                iniciar();
                break;
            case "Pausa":
                Galaga.jugador.pausa = true;
                break;
            case "Renuadar":
                renuadar();
                break;
        }
    }
}
