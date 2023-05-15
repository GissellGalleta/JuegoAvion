package modelo;

import vista.VentanaJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BalaEnemiga extends Thread{
    private ImageIcon image2;
    int contadorIntersepciones=0;
    private final VentanaJuego vistJuego;
    private final BalaNave coor;
    public BalaEnemiga(VentanaJuego vistJuego,BalaNave coor){
        this.vistJuego=vistJuego;
        this.coor=coor;
        ImageIcon originalImage2 = new ImageIcon("src/imagen2.png");
        Image scaledImage2 = originalImage2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image2 = new ImageIcon(scaledImage2);
    }

        public void run() {
            while(!vistJuego.gameOver) {
                Random random = new Random();
                int y2 = random.nextInt(450) + 1;
                int y3 = random.nextInt(450) + 1;
                int y4 = random.nextInt(450) + 1;
                while (y2 == y3 || y2 == y4 || y3 == y4) {
                    y3 = random.nextInt(450) + 1;
                    y4 = random.nextInt(450) + 1;
                }
                int x2 = 450;  // Misil 1: inicio desde el borde derecho
                int x3 = 450;  // Misil 2: inicio desde el borde derecho
                int x4 = 450;  // Misil 3: inicio desde el borde derecho
                JLabel label2 = new JLabel(image2);
                vistJuego.panel.add(label2);
                label2.setBounds(x2, y2, 50, 50);
                JLabel label3 = new JLabel(image2);
                vistJuego.panel.add(label3);
                label3.setBounds(x3, y3, 50, 50);
                JLabel label4 = new JLabel(image2);
                vistJuego.panel.add(label4);
                label4.setBounds(x4, y4, 50, 50);
                int speed1 = random.nextInt(10) + 1;
                int speed2 = random.nextInt(10) + 1;
                int speed3 = random.nextInt(10) + 1;

                while (x2 > -50 || x3 > -50 || x4 > -50) {
                    if (x2 > -50) {
                        x2 -= speed1;
                        label2.setBounds(x2, y2, 50, 50);
                    }
                    if (x3 > -50) {
                        x3 -= speed2;
                        label3.setBounds(x3, y3, 50, 50);
                    }
                    if (x4 > -50) {
                        x4 -= speed3;
                        label4.setBounds(x4, y4, 50, 50);
                    }
                    Rectangle r1 = new Rectangle(vistJuego.x1, vistJuego.y1, 100, 100);
                    Rectangle r2 = new Rectangle(x2, y2, 50, 50);
                    Rectangle r3 = new Rectangle(x3, y3, 50, 50);
                    Rectangle r4 = new Rectangle(x4, y4, 50, 50);
                    Rectangle r5 = new Rectangle(coor.x5, coor.y5, 50, 50);
                    if (r1.intersects(r2) || r1.intersects(r3) || r1.intersects(r4)) {
                        contadorIntersepciones ++;
                        vistJuego.counterLabel.setText(String.valueOf(contadorIntersepciones));
                        System.out.println(contadorIntersepciones);
                    }if (contadorIntersepciones>=70){
                        int option = JOptionPane.showOptionDialog(null, "Fin del juego", "Juego terminado", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if (option == JOptionPane.OK_OPTION) {
                            System.exit(0);
                        }
                    }else if (r5.intersects(r2)){
                        System.out.println("HOLAAsAAA");
                        vistJuego.panel.remove(label2);
                    } else if (r5.intersects(r3)) {
                        System.out.println("ADIOSSS");
                        vistJuego.panel.remove(label3);
                    } else if (r5.intersects(r4)) {
                        vistJuego.panel.remove(label4);
                        System.out.println("SIIIIIIIIIU");
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println("uno"+r2);
                    System.out.println("dos"+r3);
                    // System.out.println("tres"+r4);
                }
            }
        }

}
