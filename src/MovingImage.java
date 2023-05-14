import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingImage extends JFrame implements KeyListener {
    private JLabel label1, label2;
    private ImageIcon image1, image2;
    private int x1, y1, x2, y2;
    private boolean gameOver;

    public MovingImage() {
        super("Moving Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        ImageIcon originalImage1 = new ImageIcon("src/imagen.png");
        ImageIcon originalImage2 = new ImageIcon("src/imagen2.png");
        Image scaledImage1 = originalImage1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image scaledImage2 = originalImage2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image1 = new ImageIcon(scaledImage1);
        image2 = new ImageIcon(scaledImage2);


        label1 = new JLabel(image1);
        label2 = new JLabel(image2);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label1);
        panel.add(label2);
        getContentPane().add(panel);


        x1 = 200;
        y1 = 200;
        x2 = 0;
        y2 = 0;
        label1.setBounds(x1, y1, 100, 100);
        label2.setBounds(x2, y2, 50, 50);


        addKeyListener(this);


        gameOver = false;
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(!gameOver) {

                    x2 += 10;
                    label2.setBounds(x2, y2, 50, 50);
                    panel.repaint();


                    Rectangle r1 = new Rectangle(x1, y1, 100, 100);
                    Rectangle r2 = new Rectangle(x2, y2, 50, 50);
                    if (r1.intersects(r2)) {
                        JOptionPane.showMessageDialog(null, "Fin del juego");
                        gameOver = true;
                    }


                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
