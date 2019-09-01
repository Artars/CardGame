/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsável por redesenhar a tela
 * @author Arthur
 */
public class ScreenRefresher implements ActionListener {

    javax.swing.JFrame view;
    
    /**
     * Contrutor que toma como parâmetro a tela que ele irá redesenhar
     * @param frame 
     */
    public ScreenRefresher (javax.swing.JFrame frame) {
        view = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        view.repaint();
    }   
    
}
