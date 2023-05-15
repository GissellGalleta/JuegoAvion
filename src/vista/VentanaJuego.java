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

        panel.addKeyListener(control);

        counterLabel = new Label("0");
        counterLabel.setForeground(Color.GREEN);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel.setBounds(60, 10, 100, 30);
        panel.add(counterLabel);
        Label counterLabel2 = new Label("Vida: ");
        counterLabel2.setForeground(Color.GREEN);
        counterLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel2.setBounds(10, 10, 100, 30);
        panel.add(counterLabel2);

        setSize(500, 500);
        setVisible(true);
    }
}