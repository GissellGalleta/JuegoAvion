import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovingImage extends JFrame implements KeyListener {
    private JLabel label1;
    private JLabel label2; // Declarar label2 como variable miembro
    private JLabel label3; // Declarar label3 como variable miembro
    private ImageIcon image1, image2, image3;
    private int x1, y1;
    private boolean gameOver;
    private List<JLabel> missiles;
    private boolean wait;
    private JPanel panel; // Declarar el panel como variable miembro
    private int x2, y2; // Variables para la posición de imagen2.png

    public MovingImage() {
        super("Moving Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon originalImage1 = new ImageIcon("src/imagen.png");
        ImageIcon originalImage2 = new ImageIcon("src/imagen2.png");
        ImageIcon originalImage3 = new ImageIcon("src/imagen3.png");
        Image scaledImage1 = originalImage1.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        Image scaledImage2 = originalImage2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image scaledImage3 = originalImage3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image1 = new ImageIcon(scaledImage1);
        image2 = new ImageIcon(scaledImage2);
        image3 = new ImageIcon(scaledImage3);

        label1 = new JLabel(image1);

        panel = new JPanel(); // Inicializar el panel
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
                    int y3 = random.nextInt(450) + 1;
                    while (y2 == y3 || y1 == y3) {
                        y3 = random.nextInt(450) + 1;
                    }
                    int x3 = 450;  // Misil 2: inicio desde el borde derecho
                    label3 = new JLabel(image2);
                    panel.add(label3);
                    label3.setBounds(x3, y3, 50, 50);
                    int speed3 = random.nextInt(10) + 1;
                    while (x3 > -50) {
                        x3 -= speed3;
                        label3.setBounds(x3, y3, 50, 50);
                        Rectangle r1 = new Rectangle(x1, y1, 100, 100);
                        Rectangle r3 = new Rectangle(x3, y3, 50, 50);
                        if (r1.intersects(r3)) {
                            panel.remove(label3);
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    // Resto del código...

                    panel.remove(label3);
                }
            }
        });
        t.start();

            setSize(500, 500);
            setVisible(true);
        }

    @Override
    public void keyTyped(KeyEvent e) {

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
            } else if (keyCode == KeyEvent.VK_SPACE) {
                int x3 = x1;
                int y3 = y1;
                label3 = new JLabel(image3);
                panel.add(label3);
                label3.setBounds(x3, y3, 50, 50);
                int speed3 = 5;
                while (x3 < 450) {
                    x3 += speed3;
                    label3.setBounds(x3, y3, 50, 50);
                    Rectangle r3 = new Rectangle(x3, y3, 50, 50);
                    Rectangle r2 = new Rectangle(x2, y2, 50, 50);
                    if (r3.intersects(r2)) {
                        panel.remove(label2);
                        panel.remove(label3);
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                panel.remove(label3);
            }
            label1.setBounds(x1, y1, 100, 100);
            getContentPane().repaint();
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }

        public static void main(String[] args) {
            new MovingImage();
        }
    }

