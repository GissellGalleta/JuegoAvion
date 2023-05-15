import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MovingImage extends JFrame implements KeyListener {

    int contadorIntersepciones=0;
    private Thread myThread;
    private JLabel label1;
    private ImageIcon image1, image2, image3;
    private int x1, y1;
    private int x5,y5;
    private boolean gameOver;
    private JLabel label5;
    private JLabel counterLabel3;
    private int counter;

    private int counter2;

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

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label1);
        getContentPane().add(panel);
        getContentPane().setBackground(Color.BLUE);
        panel.setBackground(Color.BLUE);

        counterLabel3 = new JLabel("0");
        counterLabel3.setForeground(Color.GREEN);
        counterLabel3.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel3.setBounds(420, 10, 100, 30);
        panel.add(counterLabel3);
        JLabel counterLabel4 = new JLabel("Puntos: ");
        counterLabel4.setForeground(Color.GREEN);
        counterLabel4.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel4.setBounds(340, 10, 100, 30);
        panel.add(counterLabel4);

        x1 = 50;
        y1 = 200;

        label1.setBounds(x1, y1, 100, 100);
        label5=new JLabel(image3);
        panel.add(label5);

        Label counterLabel=new Label("0");
        counterLabel.setForeground(Color.RED);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel.setBounds(75, 10, 100, 30);
        panel.add(counterLabel);
        Label counterLabel2=new Label("Daño: ");
        counterLabel2.setForeground(Color.RED);
        counterLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        counterLabel2.setBounds(10, 10, 100, 30);
        panel.add(counterLabel2);

        addKeyListener(this);

        gameOver = false;
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
                        Rectangle r4 = new Rectangle(x4, y4, 50, 50);
                        Rectangle r5 = new Rectangle(x5, y5, 50, 50);
                        if (r1.intersects(r2) || r1.intersects(r3) || r1.intersects(r4)) {
                            contadorIntersepciones ++;
                            counterLabel.setText(String.valueOf(contadorIntersepciones));
                            System.out.println(contadorIntersepciones);
                        }if (contadorIntersepciones>=70){
                            int option = JOptionPane.showOptionDialog(null, "Fin del juego", "Juego terminado", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                            if (option == JOptionPane.OK_OPTION) {
                                System.exit(0);
                            }
                        }else if (r5.intersects(r2)){
                            System.out.println("HOLAAsAAA");
                            panel.remove(label2);
                            incrementCounter();
                        } else if (r5.intersects(r3)) {
                            System.out.println("ADIOSSS");
                            panel.remove(label3);
                            incrementCounter();
                        } else if (r5.intersects(r4)) {
                            panel.remove(label4);
                            incrementCounter();
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
        });
        t.start();

        setSize(500, 500);
        setVisible(true);
    }
    public void incrementCounter() {
        counter++;
        counterLabel3.setText(String.valueOf(counter));
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
            myThread =new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!gameOver) {
                        Random random = new Random();
                        y5 = y1 + 30;
                        x5 = x1 + 2;  // Misil 4: inicio desde el avion
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
            });
            myThread.start();
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
