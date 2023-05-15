package modelo;

import vista.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BalaNave extends Thread{

    int x5,y5;
    private ImageIcon  image3;
    private JLabel label5;
    private final VentanaJuego vistJuego;
    public BalaNave(VentanaJuego vistJuego){
        this.vistJuego=vistJuego;
        ImageIcon originalImage3 = new ImageIcon("src/imagen3.png");
        Image scaledImage3 = originalImage3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image3 = new ImageIcon(scaledImage3);
        label5=new JLabel(image3);
        vistJuego.panel.add(label5);
    }

        @Override
        public void run() {
                Random random = new Random();
                y5 = vistJuego.y1 + 30;
                x5 = vistJuego.x1 + 2;  // Misil 4: inicio desde el avion
                label5.setBounds(x5, y5, 50, 50);
                int speed4 = random.nextInt(10) + 1;
                while (x5 > -50) {
                    x5 += speed4;
                    label5.setBounds(x5, y5, 50, 50);
                    Rectangle r5 = new Rectangle(x5, y5, 50, 50);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }

}
