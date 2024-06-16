package SnakeX2;


import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;



public class Snake extends JFrame {
    public boolean f=true;
    static JFrame ex;
    public Snake() {
    
        new Board().gameoveringelement=true;
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public static void m() throws IOException {
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                 ex = new Snake();
                ex.setVisible(true);                
            }
        });
    }
    void disposition()
    {
    ex.dispose();
    }
    }

    


