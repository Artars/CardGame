/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
/**
 *
 * @author Arthur
 */

import Cartas.Renderizavel;
import cardgame.GameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class Table extends JPanel {
    
    private ArrayList<Observer> observers;

    public Table() {
        observers = new ArrayList<Observer>();
    }
    
    public void registerObserver(Observer ob){
        observers.add(ob);
    }

    /*
    public void drawRetangulo (Graphics2D g) {
        int width =  getWidth()/2;
        int height = getHeight() /6;
        int margem = height /6;
        
        g.setColor (Color.DARK_GRAY);
     
        for (int i = 0; i < 5; i++) {
            if (i != 2) g.fillRect(width /2, margem * (i+1) + height * i, width, height);
            else g.fillRect((width /2)-(width/5), margem * (i+1) + height * i, width+(2*width/5), height);
        }
        
    }

    
    public void drawRetangulo2 (Graphics2D g) {
        int width =  getWidth() /10;
        int height = getHeight() /6;
        int margem = height /6;
        
        g.setColor (new Color(51,105,30));
     
        for (int i = 0; i < 5; i++) {
            if (i != 2){ 
                for (int j = 0; j < 5; j++)
                    g.fillRect((5*width /2)+(j*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            }
            else{ 
                for (int j = 0; j < 7; j++)
                    g.fillRect((5*width /2)+((j-1)*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            }
        }
        
    }
    */
    
/*    public void redraw (){
        paintComponent();
    }*/
    
    @Override //sobrescrita do metodo paintComponent da classe JPanel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        //drawRetangulo ((Graphics2D) g);
        
        Graphics2D g2 = (Graphics2D)g;
        //drawRetangulo2 (g2);
        
        g2.setFont(new Font( "SansSerif", Font.BOLD, 12 ));
        for(Observer ob : observers){
            ob.update(null, g);
        }
        
        for(ArrayList<Renderizavel> a: GameManager.getInstance().getRenderizaveis()){ 
            for(Renderizavel r: a)
                r.draw((Graphics2D) g2);
        }
    }

    

    
}