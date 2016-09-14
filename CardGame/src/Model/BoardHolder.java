/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import Cartas.Renderizavel;
import Cartas.Selecionavel;
import cardgame.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Arthur
 */
public class BoardHolder implements Renderizavel, Selecionavel {
    
    protected Rectangle rect;
    protected Carta[] cartas;
    protected int jogador;
    private int destaque;
    private int pocketWidth;
    private int pocketHeight;

    public BoardHolder(Rectangle rect, int player) {
        this.rect = rect;
        this.jogador = player;
        this.cartas = new Carta[5];
        this.destaque = -1;
        
        GameManager.getInstance().adicionarRender(this);
        GameManager.getInstance().adicionarSelecionavel(this);
    }
    
    public BoardHolder(int player) {
        this.rect = new Rectangle(0,0,0,0);
        this.jogador = player;
        this.cartas = new Carta[5];
        this.destaque = -1;
        
        GameManager.getInstance().adicionarRender(this);
        GameManager.getInstance().adicionarSelecionavel(this);
    }
    
    public boolean insereCarta(Carta c, int n) {
        if(cartas[n] == null) {
            cartas[n] = c;
            cartas[n].setRect(getCardRect(n));
            cartas[n].setIndex(n);
            System.out.println("Adicionado carta em " + getCardRect(n));
            return true;
        }
        return false;
    }
    
    public boolean insereCarta(Carta c) {
        int n = destaque;
        if(destaque != -1 && cartas[n] == null) {
            cartas[n] = c;
            cartas[n].setRect(getCardRect(n));
            cartas[n].setIndex(n);
            System.out.println("Adicionado carta em " + getCardRect(n));
            return true;
        }
        return false;
    }
    
    public int getIndex(int x, int y) {
        for(int i = 0; i < 5; i++) {
            Rectangle frame = getCardRect(i);
            if (frame.inside(x,y))
                return i;
        }
        return -1;
    }
    
    public void retiraCarta(int n) {
        cartas[n] = null;
    }
    
    public Carta getCarta(int n) {
        return cartas[n];
    }
    
    private void ajustCard () {
        for (Carta c : cartas) {
            if (c != null) {
                c.setRect(getCardRect(c.getIndex()));
                c.setDimension(pocketWidth, pocketHeight);
            }
        }
    }
    
    public void setRect (Rectangle r) {
        this.rect = r;
        ajustCard();
    }
    
    public void setRect (int x, int y) {
        this.rect.x = x;
        this.rect.y = y;
        ajustCard();
    }
    
    public void setRect (int x, int y, int width, int height) {
        this.rect = new Rectangle(x,y,width,height);
        ajustCard();
    }
    
    public void setRect (Point p) {
        this.rect.x = (int) p.getX();
        this.rect.y = (int) p.getY();
        ajustCard();
    }
    
    public void setPocketDimensions (int width, int height) {
        this.pocketHeight = height;
        this.pocketWidth = width;
        ajustCard();
    }
    
    public Rectangle getCardRect(int n) {
        return new Rectangle(rect.x + n * (pocketWidth + 6) + 3,
                rect.y + 3, pocketWidth, pocketHeight);
    }
    
    protected Rectangle getFrame(int n) {
        return new Rectangle(rect.x + n *(pocketWidth + 6), rect.y,
                pocketWidth + 6, pocketHeight + 6);
    }
    
    public void draw(Graphics2D g) {
        Color background = new Color(51,105,30);
        for (int i = 0; i < 5; i++) {
            Rectangle frame = getFrame(i);
            Color fillColor = (i != destaque) ? Color.DARK_GRAY : Color.YELLOW;
            g.setColor(fillColor);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
            g.setColor(background);
            frame = getCardRect(i);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
        }
    }

    @Override
    public boolean isInside(int x, int y) {
        return rect.inside(x, y);
    }

    @Override
    public boolean isInside(Point p) {
        return rect.inside((int) p.getX(), (int) p.getY());
    }

    @Override
    public void onHover(int x, int y) {
        for (int i = 0; i < 5; i++) {
            Rectangle cardZone = getCardRect(i);
            if(cardZone.inside(x,y)){
                destaque = i;
                break;
            }
        }
    }

    @Override
    public void onHover(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        for (int i = 0; i < 5; i++) {
            Rectangle cardZone = getCardRect(i);
            if(cardZone.inside(x,y)){
                destaque = i;
                break;
            }
        }
    }

    @Override
    public void onLeave() {
        destaque = -1;
    }

    public int getJogador() {
        return jogador;
    }

    @Override
    public void onClick() {
    
    }
}
