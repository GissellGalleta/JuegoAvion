package vista;

import controlador.ControladorJuego;
import modelo.BalaNave;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {
    private ImageIcon image1;
    public JLabel label1;
    public int x1, y1;
    public JPanel panel;
    public Label counterLabel;
    public Label counterLabel3;
    public boolean gameOver;
   ControladorJuego control;
    public VentanaJuego() {
        super("Juego disparos");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon originalImage1 = new ImageIcon("src/imagen.png");

        Image scaledImage1 = originalImage1.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);

        image1 = new ImageIcon(scaledImage1);


        label1 = new JLabel(image1);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(label1);
        getContentPane().add(panel);
        getContentPane().setBackground(Color.BLUE);
        panel.setBackground(Color.BLUE);

        x1 = 50;
        y1 = 200;

        label1.setBounds(x1, y1, 100, 100);


        control = new ControladorJuego(this);
        addKeyListener(control);

        counterLabel = new Label("0");
        counterLabel.setForeground(Color.RED);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel.setBounds(70, 10, 100, 30);
        panel.add(counterLabel);
        Label counterLabel2 = new Label("Daño: ");
        counterLabel2.setForeground(Color.RED);
        counterLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel2.setBounds(10, 10, 100, 30);
        panel.add(counterLabel2);

        counterLabel3 = new Label("0");
        counterLabel3.setForeground(Color.GREEN);
        counterLabel3.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel3.setBounds(410, 10, 100, 30);
        panel.add(counterLabel3);
        Label counterLabel4 = new Label("Puntos: ");
        counterLabel4.setForeground(Color.GREEN);
        counterLabel4.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel4.setBounds(330, 10, 100, 30);
        panel.add(counterLabel4);

        setSize(500, 500);
        setVisible(true);
    }
}