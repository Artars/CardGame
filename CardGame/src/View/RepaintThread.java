/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;

/**
 *
 * @author Arthur
 */
public class RepaintThread implements Runnable{

    JFrame frame;

    public RepaintThread(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        //while(true)
        //    frame.repaint();
    }
    
}
