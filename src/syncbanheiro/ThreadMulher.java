/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncbanheiro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronnypetsonss
 */
public class ThreadMulher extends Thread {
    Banheiro b;
    
    public ThreadMulher(Banheiro b){
        this.b = b;
    }
    
    @Override
    public void run(){
        try {
            // System.out.println("Thread n. " + this.getId() + "\n");
            b.mulherUsa();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadMulher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
