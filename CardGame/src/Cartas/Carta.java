/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import cardgame.GameManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arthur
 */
public abstract class Carta implements Comparable, Selecionavel, Renderizavel {
    
    //Variaveis estáticas da carta
    protected int numero;
    protected int naipe;
    protected int multiplicador;
    protected int jogador;
    
    //Variaveis de sistema (Dinamicas)
    protected Image sprite;
    protected Rectangle rect;
    protected boolean selecionado;
    protected BoardHolder boardParent;
    protected int index;
    
    protected boolean realizouAcao = false;

    @Override
    public int compareTo(Object t) {
        if (t instanceof Carta){
            if (this.numero == ((Carta) t).numero){
                if (this.naipe == ((Carta) t).naipe)
                    return 0;
                return (this.naipe > ((Carta) t).naipe) ? 1:-1;
            }
            return (this.numero > ((Carta) t).numero) ? 1:-1;
        }
        return 0;
    }
    
    private String NumeroToString(){
        switch(this.numero){
            case 1:
                return "ace";
            case 11:
                return "jack";
            case 12:
                return "queen";
            case 13:
                return "king";
            default:
                return String.valueOf(this.numero);
        }
    }
    
    private String NaipeToString(){
        switch(this.naipe){
            case 0:
                return "clubs";
            case 1:
                return "diamonds";
            case 2:
                return "hearts";
            case 3:
                return "spades";
            default:
                return "clubs";
        }
    }
    
    
    public Carta (int x, int y, int n) {
        this.numero = (n % 13) + 1;
        this.naipe = n / 13;
        this.multiplicador = 1;
        this.selecionado = false;
        this.index = -1;
        
        String imgPath = "img/" + this.NumeroToString() + "_of_" + this.NaipeToString() + ".png";
        //imgPatch = "img/2_of_clubs"
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.rect = new Rectangle(x,y, 74, 94);
        GameManager.getInstance().adicionarRender((Renderizavel) this);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
    }
    
    public Carta (int n) {
        this.numero = (n % 13) + 1;
        this.naipe = n / 13;
        this.multiplicador = 1;
        this.index = -1;
        
        String imgPath = "img/" + this.NumeroToString() + "_of_" + this.NaipeToString() + ".png";
        //imgPatch = "img/2_of_clubs"
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.rect = new Rectangle(0,0,74,94);
        GameManager.getInstance().adicionarRender(this);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
    }
    
    public void setRect(int x, int y) {
        rect.x = x;
        rect.y = y;
    }
    
    public void setRect(int x, int y, int width, int height) {
        rect = new Rectangle(x,y,width,height);
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
    public void setDimension(int width, int height) {
        rect.width = width;
        rect.height = height;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    
    
    
    public void draw(Graphics2D g) {
        //int sizeHeight = g.getClip().getBounds().height / 6 - 6;
        //int sizeWidth = g.getClip().getBounds().width / 10 - 6;
        
        g.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
        
        g.setColor (new Color(0,0,0,0));
        if (realizouAcao) {
            g.setColor (new Color(0.1f,0.1f,0.1f,0.3f));
        }
        else if (selecionado)
            g.setColor(new Color(0,1,1,.2f));
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
    }
    
    public boolean isInside(int x, int y) {
        return rect.inside(x, y);
    }
    
    public boolean isInside(Point p) {
        return rect.inside((int) p.getX(), (int) p.getY());
    }
    
    public abstract void onClick();
    
    public void onHover(int x, int y) {
        System.out.println("Passou em mim");
        selecionado = true;
    }
    public void onHover(Point p) {
        selecionado = true;
    }
    
    public void onLeave() {
        selecionado = false;
    }
    
    public int getNumero() {
        return numero;
    }

    public int getNaipe() {
        return naipe;
    }

    public void disable() {
        GameManager.getInstance().removerSelecionavel(this);
    }

    public int getJogador() {
        return jogador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
    
    public boolean isClicavel(int jogador){
        if (!realizouAcao && jogador == this.jogador) {
            return true;
        }
        return false;
    }
    
}
