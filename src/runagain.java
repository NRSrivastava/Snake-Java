package SnakeX2;


import java.awt.event.KeyEvent;
import java.io.IOException;

public class runagain {
   
        public void keyPressedd(int k) throws IOException {
            

            if (k== KeyEvent.VK_ENTER) {
                
                    new Snake().m();
               
                new Snake().disposition();
                
                new Board().gameoveringelement=false;
            }
            if (k== KeyEvent.VK_CONTROL){
                
                new main().onceopen=true;
                
                
                new Snake().m();
               
                new Snake().disposition();
                
                new Board().gameoveringelement=false;
            }
            if (k== KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }
    
}
