/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur
 */
public class Delay implements Runnable {

    double delay;

    public Delay(double delay) {
        this.delay = delay;
    }
    
    
    
    @Override
    public void run() {
        
        try {
            Thread.sleep((long) (delay * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Delay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
