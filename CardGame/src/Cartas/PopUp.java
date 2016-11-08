/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import cardgame.GameManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Arthur
 * Classe que mostra status da carta sobre ela
 */



public class PopUp implements Renderizavel, Serializable{
    //Define ponto inferior equerdo do Retangulo
    private Rectangle invertedRect;
    //Referencia a carta mae
    private Carta cartaMae;

    public PopUp(Carta cartaMae) {
        this.cartaMae = cartaMae;
        invertedRect = new Rectangle();
        GameManager.getInstance().adicionarRender(this, 1);
    }
    
    
    
    @Override
    public void draw(Graphics2D g) {
        int i=0;
        if(cartaMae.getSelecionado()) {
            g.setFont(new Font( "SansSerif", Font.BOLD, 16 ));
            
            Rectangle cardRect = cartaMae.getRect();
            Rectangle bounds = g.getClipBounds();
            
            ArrayList<String> atributos = cartaMae.getAtributos();
            ArrayList<Color> colors = cartaMae.getAtributosColor();
            
            int fontSize = 17;
            int desirableHeight = (atributos.size() + 1) * fontSize;
            int cardDistance = (cardRect.height) /8;
            
            invertedRect.height = desirableHeight;
            
            if((cardRect.y - cardDistance - desirableHeight) < bounds.getY()) {
                invertedRect.y = cardRect.y + cardRect.height + cardDistance + desirableHeight;
            } else {
                invertedRect.y = cardRect.y - cardDistance;
            }
            
            invertedRect.width = (cardRect.width * 3) /2;
            
            if((cardRect.x + cardRect.width/2) > bounds.getWidth()) {
                invertedRect.x = cardRect.x - cardRect.width / 2;
            } else {
                invertedRect.x = cardRect.x + cardRect.width/2;
            }
            
            g.setColor(new Color(207,216,220));
            g.fillRoundRect(invertedRect.x, invertedRect.y - invertedRect.height,
                    invertedRect.width, invertedRect.height, 10, 10);
            
            while (i < atributos.size()){
                Rectangle popUpRect = new Rectangle(invertedRect.x + 5, 
                        invertedRect.y - invertedRect.height + fontSize/3 + (fontSize*(i+1)), 
                        invertedRect.width, fontSize);
                g.setColor(colors.get(i));
                g.drawString(atributos.get(i), popUpRect.x, popUpRect.y);
                
                i++;
            }
        }
    }

    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 1);
    }
    
}
