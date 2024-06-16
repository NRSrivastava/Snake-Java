package SnakeX2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    public final int B_WIDTH = 1000;
    public final int B_HEIGHT = 700;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 7000;
    private final int RAND_POS_height = 68;
    private final int RAND_POS_width = 96;
    private int DELAY = 200;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image rhead;
    private Image lhead;
    
    private Image uhead;
    private Image dhead;
    private Image back;
    private int key;
    private int score=0;
    static boolean gameoveringelement = true;
    static int openpagetimer;
    String skip ="Press ESC to Skip";

    public Board() {
        openpagetimer=70;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages(){

        ImageIcon iid = new ImageIcon(getClass().getResource("/dot.png"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(getClass().getResource("/apple.gif"));
        apple = iia.getImage();

        ImageIcon iilh = new ImageIcon(getClass().getResource("/lhead.png"));
        lhead = iilh.getImage();
        
        ImageIcon iirh = new ImageIcon(getClass().getResource("/rhead.png"));
        rhead = iirh.getImage();
        
        ImageIcon iiuh = new ImageIcon(getClass().getResource("/uhead.png"));
        uhead = iiuh.getImage();
        
        ImageIcon iidh = new ImageIcon(getClass().getResource("/dhead.png"));
        dhead = iidh.getImage();
        
        back = new ImageIcon(getClass().getResource("/background.gif")).getImage();
        
        head = rhead;
    }
    

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();
        timerint();
        
    }
    void timerstop(){
        timer.stop();
    }
    void timerint(){
        timer = new Timer(DELAY, this);
        timer.start();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background(g);
        try {
            doDrawing(g);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void background(Graphics o)
    {
            o.drawImage(back,0,0,this);
            
    }
    public void openpage(Graphics g)
    {
        String op1="Welcome to SnakeX2";
        String op2="By Neelesh Ranjan Srivastava";
        Font small = new Font("Helvetica", Font.BOLD, 25);
        Font small2 = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = g.getFontMetrics(small);
        FontMetrics metr2 = g.getFontMetrics(small2);
        g.setColor(Color.DARK_GRAY);
        g.setFont(small);   
        
        g.drawString(op1, (B_WIDTH - metr.stringWidth(op1)) / 2, B_HEIGHT / 3);
        g.setFont(small2);
        g.drawString(op2, (B_WIDTH - metr2.stringWidth(op2)) / 2, 3*B_HEIGHT / 4);
        g.setColor(Color.WHITE);
        g.drawString(skip, B_WIDTH - metr2.stringWidth(skip),metr2.getHeight());
    }
    public void tutorials(Graphics g){
        String op1="Use Arrow keys to control the Snake";
        String op2="Your Score will increase as soon as the Snake eats food";
        String op3="The Speed and the Length of the Snake will increase as soon as it eats food ";
        Font small = new Font("Helvetica", Font.BOLD, 25);
        FontMetrics metr = g.getFontMetrics(small);
        g.setColor(Color.DARK_GRAY);
        g.setFont(small);
        g.drawString(op1, (B_WIDTH - metr.stringWidth(op1)) / 2, B_HEIGHT / 4);
        g.drawString(op2, (B_WIDTH - metr.stringWidth(op2)) / 2, B_HEIGHT / 2);
        g.drawString(op3, (B_WIDTH - metr.stringWidth(op3)) / 2, B_HEIGHT*3 / 4);
        Font small2 = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr2 = g.getFontMetrics(small2);
        g.setFont(small2);
        g.setColor(Color.WHITE);
        g.drawString(skip, B_WIDTH - metr2.stringWidth(skip), metr2.getHeight());
    }
    private void doDrawing(Graphics g) throws IOException {
        if(openpagetimer>=0&&new main().onceopen)
        {
            new skip().keyPressedd(key);
            if(openpagetimer>=30)
                openpage(g);
            else if(openpagetimer>=0)
                tutorials(g);
            openpagetimer--;
        }
        else
        {
            new main().onceopen=false;
        if (inGame) {
            Font small = new Font("Helvetica", Font.BOLD, 21);
            FontMetrics metr = getFontMetrics(small);
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString("Score "+score, B_WIDTH - metr.stringWidth("Score "+score),metr.getHeight());
            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
           
        }
    }
    }
    void gameovering() throws IOException{
        if(gameoveringelement){
        new runagain().keyPressedd(key);
        }
    }

    private void gameOver(Graphics g) throws IOException {
        String msg = "Game Over";
        String msg2 = "Score "+score;
        String msg3 ="Press ENTER to play again!!";
        String msg4="Press CTRL for tutorials";
        String msg5="Press ESC to exit";
        Font small = new Font("Helvetica", Font.BOLD, 21);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.DARK_GRAY);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.drawString(msg2, (B_WIDTH - metr.stringWidth(msg2)) / 2, B_HEIGHT / 2+metr.getHeight());
        g.drawString(msg3,(B_WIDTH - metr.stringWidth(msg3)) / 2, B_HEIGHT / 2+2*metr.getHeight());
        g.drawString(msg4,(B_WIDTH - metr.stringWidth(msg4)) / 2, B_HEIGHT / 2+3*metr.getHeight());
        g.drawString(msg5,(B_WIDTH - metr.stringWidth(msg5)) / 2, B_HEIGHT / 2+4*metr.getHeight());
        gameovering();
    }

    private void checkApple() {

        if (((x[0]>=apple_x) && (x[0]<=(apple_x+20))) && (y[0]>=apple_y&&y[0]<=apple_y+20)) {
            score+=10;
            dots++;
            locateApple();
            DELAY-=DELAY*0.1;
            timerstop();
            timerint();
            
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS_width);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS_height);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

           key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                head=lhead;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                head=rhead;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                head=uhead;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                head=dhead;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
