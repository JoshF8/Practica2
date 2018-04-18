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
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Josh
 */
public class VentanaJuego extends JFrame implements ActionListener{
    
    int ancho = 300, alto = 550;
    Jugador jugador;
    JLabel punteo, tiempo;
    JMenuItem juegoNuevo = new JMenuItem("Juego nuevo"), pausar = new JMenuItem("Pausa"), renuadar = new JMenuItem("Renuadar");
    JMenu top3 = new JMenu("Top 3");
    public JLabel escudo;
    public VentanaJuego() {
        super("Mini-Gálaga");
        setResizable(false);
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src//graficos//FondoR.jpg"), icon1 = new ImageIcon("src//graficos//Escudo.png");
        JLabel fondo = new JLabel();
        escudo = new JLabel();
        fondo.setBounds(0, 0, ancho, alto);
        escudo.setBounds(115,440,50,40);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)), icono1 = new ImageIcon(icon1.getImage().getScaledInstance(escudo.getWidth(), escudo.getHeight(), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        escudo.setIcon(icono1);
        tiempo = new JLabel("60");
        tiempo.setFont(new Font("Arial", Font.BOLD, 20));
        tiempo.setForeground(Color.WHITE);
        tiempo.setBounds(250, 40, 30, 30);
        punteo = new JLabel("0");
        punteo.setFont(new Font("Arial", Font.BOLD, 20));
        punteo.setForeground(Color.WHITE);
        punteo.setBounds(250, 70, 50, 30);
        JMenuBar menu = new JMenuBar();       
        juegoNuevo.addActionListener(this);
        pausar.addActionListener(this);
        renuadar.addActionListener(this);
        pausar.setEnabled(false);
        renuadar.setEnabled(false);
        menu.add(juegoNuevo);
        menu.add(pausar);
        menu.add(renuadar);
        menu.add(top3);
        menu.setBounds(0,0,300,20);
        getContentPane().add(tiempo);
        getContentPane().add(punteo);
        getContentPane().add(menu);
        //getContentPane().add(escudo);
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
        if(!Galaga.iniciado){
            this.jugador = new Jugador(this);
            Galaga.jugador = jugador;
            this.jugador.start();
            CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
            creadorEnemigos.start();
            Galaga.creadorEnemigos = creadorEnemigos;
            CreadorItems creadorItems = new CreadorItems();
            creadorItems.start();
            Galaga.creadorItems = creadorItems;
            Cronometro cronometro = new Cronometro(tiempo, punteo);
            Galaga.cronometro = cronometro;
            cronometro.start();
            Galaga.iniciado = true;
        }else{
            if(Galaga.jugador.vivo){
                Galaga.jugador.reiniciar();
                Galaga.cronometro.reiniciar();
                ArrayList<Disparo> disparosAux = new ArrayList<Disparo>();
                for(Disparo disparo : Galaga.disparos){
                    disparosAux.add(disparo);
                }
                for(Disparo disparo : disparosAux){
                    disparo.destruir();
                }
                ArrayList<Enemigos> enemigosAux = new ArrayList<Enemigos>();
                for(Enemigos enemigo : Galaga.enemigos){
                    enemigosAux.add(enemigo);
                }
                for(Enemigos enemigo : enemigosAux){
                    enemigo.destruir();
                }
            }else if(!Galaga.creadorEnemigos.isAlive() && !Galaga.creadorItems.isAlive()){
                Galaga.jugador.reiniciar();
                Galaga.cronometro = new Cronometro(tiempo, punteo);
                Galaga.cronometro.start();
                Galaga.creadorEnemigos = new CreadorEnemigos();
                Galaga.creadorEnemigos.start();
                Galaga.creadorItems = new CreadorItems();
                Galaga.creadorItems.start();
            }
        }
    }
    
    public void finalizar(){
        if(Galaga.jugador.vivo){
            Galaga.jugador.vivo = false;
            pausar.setEnabled(false);
            int contador = 0;
            top3.removeAll();
            Galaga.nombresTop.add("Nombre:" + JOptionPane.showInputDialog(this, "Ingrese su nombre:") +" Puntos:" + punteo.getText());
            Galaga.puntosTop.add(Integer.valueOf(punteo.getText()));
            Collections.sort(Galaga.puntosTop);
            Collections.reverse(Galaga.puntosTop);
            for(Integer valor : Galaga.puntosTop){
                for(String texto : Galaga.nombresTop){
                    if(texto.contains("Puntos:"+valor)){
                        top3.add(texto);
                        break;
                    }
                }
                contador++;
                if(contador == 3){
                    break;
                }
            }
            top3.repaint();
        }
    }
    
    public void escudo(boolean valor){
        if(valor){
            getContentPane().add(escudo, getContentPane().getComponents().length-1);
        }else{
            getContentPane().remove(escudo);
        }
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
            for(Item item : Galaga.items){
                synchronized (item){
                    item.notify();
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
                pausar.setEnabled(true);
                renuadar.setEnabled(false);
                break;
            case "Pausa":
                Galaga.jugador.pausa = true;
                renuadar.setEnabled(true);
                pausar.setEnabled(false);
                break;
            case "Renuadar":
                renuadar();
                pausar.setEnabled(true);
                renuadar.setEnabled(false);
                break;
        }
    }
}
