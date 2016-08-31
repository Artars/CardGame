/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CardModel;
import View.BoardFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class CardController implements MouseListener, MouseMotionListener, ActionListener {

    private BoardFrame view;
    private CardModel model;
    
    private int turno = 0;
    private int jogadorAtual;
    
    Rectangle[] workspaces;

    public CardController() {
        workspaces = new Rectangle[25];
    }
    
    /*public int getWorkspace (int x, int y){
        int width = view.getBoardPanel().getWidth()/4;   
        if (x >= width && x <= width*3){
            int height = view.getBoardPanel().getHeight()/36;
            i
        }
        
    }*/
    
    public void addView(Observer view){
        this.view = (BoardFrame)view;
        this.CreateWorkspaces(workspaces);
    }
    
    public void addModel(Observer model) {
        this.model = (CardModel) model;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int i = 0;
        int j = 0;
        int n = 0;
        int width = 0;
        int height = 0;
        width =  view.getBoardPanel().getBounds().width /10;
        height = view.getBoardPanel().getBounds().height /6;
        int margem = height /6;
        
        for(Rectangle reckt : workspaces) {
            reckt.reshape((5*width /2)+(n*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            if (reckt.inside(me.getX(), me.getY())) {
                System.out.println("Clicaram no retangulo " + j);
                break;
            }
            j++;
            n = j%5;
            i = j/5;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void startMainWindow(){
        view.setMinimumSize(new Dimension(600, 400));
        view.setVisible(true);
    }

    private void CreateWorkspaces(Rectangle[] workspaces) {
        int width = 0;
        int height = 0;
        width =  view.getBoardPanel().getBounds().width /10;
        height = view.getBoardPanel().getBounds().height /6;
        int margem = height /6;
     
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                workspaces[i*5+j] = new Rectangle((5*width /2)+(j*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            }
        }
    }
    
}
