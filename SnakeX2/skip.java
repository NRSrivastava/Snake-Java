package SnakeX2;


import java.awt.event.KeyEvent;
import java.io.IOException;

public class skip {
   
        public void keyPressedd(int k) throws IOException {
            if(k==KeyEvent.VK_ESCAPE)
            Board.openpagetimer=-1;
        }
    
}
