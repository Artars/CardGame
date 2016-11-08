/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Arthur
 */
public class ScreenRefresher implements ActionListener {

    javax.swing.JFrame view;
    
    public ScreenRefresher (javax.swing.JFrame frame) {
        view = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        view.repaint();
    }   
    
}
