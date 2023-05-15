package controlador;

import modelo.BalaNave;
import vista.VentanaJuego;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class ControladorJuego implements KeyListener {
    private final VentanaJuego vistJuego;
    public ControladorJuego(VentanaJuego vistJuego){
        this.vistJuego=vistJuego;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            vistJuego.y1 -= 10;
        } else if (keyCode == KeyEvent.VK_S) {
            vistJuego.y1 += 10;
        } else if (keyCode == KeyEvent.VK_A) {
            vistJuego.x1 -= 10;
        } else if (keyCode == KeyEvent.VK_D) {
            vistJuego.x1 += 10;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            //BalaNave b1=new BalaNave(v1);
            //b1.start();

        }
        vistJuego.label1.setBounds(vistJuego.x1, vistJuego.y1, 100, 100);
        vistJuego.getContentPane().repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {}
}
