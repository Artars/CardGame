/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import cardgame.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Descarte extends BoardHolder {
    
    private boolean over;
    private int lastIndex;
    private ArrayList<Integer> descarteFixo;
    
    
    public Descarte(Rectangle rect, int player) {
        super(rect, player);
        over = false;
        lastIndex = -1;
        descarteFixo = new ArrayList<>();
        GameManager.getInstance().setDescarte((Descarte)this);
    }

    public Descarte(int player) {
        super(player);
        over = false;
        lastIndex = -1;
        descarteFixo = new ArrayList<>();
        GameManager.getInstance().setDescarte((Descarte)this);
    }
    
    @Override
    public void draw(Graphics2D g) {
        Color background = new Color(51,105,30);
        for (int i = 0; i < 5; i++) {
            Rectangle frame = getFrame(i);
            Color fillColor = Color.DARK_GRAY;
            g.setColor(fillColor);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
            g.setColor(background);
            frame = getCardRect(i);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
        }
        if(over) {
            Rectangle frame = getCardRect(0);
            g.setColor(Color.YELLOW);
            g.fillRect(rect.x, rect.y, rect.width, 3);
            g.fillRect(rect.x, rect.y + rect.height - 3, rect.width, 3);
            g.fillRect(rect.x, rect.y, 3, rect.height);
            g.fillRect(rect.x + rect.width - 3, rect.y, 3, rect.height);
        }
    }
    
   @Override
    public void onHover(int x, int y) {
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onHover(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onLeave() {
        over = false;
    }
    
    private void adicionarCarta(Carta c, int n) {
        cartas[n] = c;
        c.setIndex(n);
        c.setRect(getCardRect(n));
        c.setRealizouAcao(false);
    }
    
    public void descartaCarta(Carta c) {
        if (lastIndex < 4) {
            lastIndex++;
            adicionarCarta(c, lastIndex);
        } 
        //Para o caso do descarte da mesa estar cheio
        else {
            //Esvazia a mesa
            for (int i = 0; i < 5; i++){
                descarteFixo.add(cartaToInteger(cartas[i]));
                cartas[i].removeRenderer();
                cartas[i] = null;
            }
            lastIndex = 0;
            adicionarCarta(c, lastIndex);
        }
    }
    
    private Integer cartaToInteger(Carta c) {
        //this.numero = (n % 13) + 1;
        //this.naipe = n / 13;
        int result = c.getNaipe() * 13 + c.getNumero() - 1;
        return result;
    }
    
    public ArrayList<Integer> getDescarte() {
        ArrayList<Integer> antigas = descarteFixo;
        descarteFixo = new ArrayList<>();
        return antigas;
    }
    
}
