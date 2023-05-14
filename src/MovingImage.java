import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovingImage extends JFrame implements KeyListener {
    private JLabel label1;
    private ImageIcon image1, image2;
    private int x1, y1;
    private boolean gameOver;
    private List<JLabel> missiles;
    private boolean wait;

    public MovingImage() {
        super("Moving Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon originalImage1 = new ImageIcon("src/imagen.png");
        ImageIcon originalImage2 = new ImageIcon("src/imagen2.png");
        Image scaledImage1 = originalImage1.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        Image scaledImage2 = originalImage2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image1 = new ImageIcon(scaledImage1);
        image2 = new ImageIcon(scaledImage2);

        label1 = new JLabel(image1);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label1);
        getContentPane().add(panel);

        x1 = 0;
        y1 = 200;
        label1.setBounds(x1, y1, 100, 100);

        addKeyListener(this);

        gameOver = false;
        missiles = new ArrayList<JLabel>();
        wait = false;

        Thread t = new Thread(new Runnable() {
            public void run() {
                while(!gameOver) {
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
                    panel.add(label2);
                    label2.setBounds(x2, y2, 50, 50);
                    JLabel label3 = new JLabel(image2);
                    panel.add(label3);
                    label3.setBounds(x3, y3, 50, 50);
                    JLabel label4 = new JLabel(image2);
                    panel.add(label4);
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
                        Rectangle r1 = new Rectangle(x1, y1, 100, 100);
                        Rectangle r2 = new Rectangle(x2, y2, 50, 50);
                        Rectangle r3 = new Rectangle(x3, y3, 50, 50);
                        if (r1.intersects(r2) || r1.intersects(r3)) {
                            int option = JOptionPane.showOptionDialog(null, "Fin del juego", "Juego terminado", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                            if (option == JOptionPane.OK_OPTION) {
                                System.exit(0);
                            }
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    panel.remove(label2);
                    panel.remove(label3);
                }
            }
        });
        t.start();

        setSize(500, 500);
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            y1 -= 10;
        } else if (keyCode == KeyEvent.VK_S) {
            y1 += 10;
        } else if (keyCode == KeyEvent.VK_A) {
            x1 -= 10;
        } else if (keyCode == KeyEvent.VK_D) {
            x1 += 10;
        }
        label1.setBounds(x1, y1, 100, 100);
        getContentPane().repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new MovingImage();
    }
}
