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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Clase abstrata de carta, base para todas as cartas presentes no jogo
 * 
 * @author Arthur
 */
public abstract class Carta implements Comparable, Selecionavel, Renderizavel {
    
    //Variaveis estáticas da carta ---------------------------------------------
    protected int numero;
    protected int naipe;
    protected int multiplicador;
    protected int jogador;
    
    //Variaveis de sistema (Dinamicas) -----------------------------------------
    protected Image sprite;
    protected Image hiddenSprite;
    protected Rectangle rect;
    protected boolean selecionado;
    protected BoardHolder boardParent;
    protected int index;
    protected boolean realizouAcao = false;
    protected boolean escondido = false;
    protected PopUp popUp;

    //Funcoes abstratas --------------------------------------------------------
    /**
     * Retorna os atributos a serem mostrados no PopUp
     * @return atributos
     */
    public abstract ArrayList<String> getAtributos();
    /**
     * Retorna as cores dos atributos a serem mostrados no PopUp
     * @return coresAtributos
     */
    public abstract ArrayList<Color> getAtributosColor();
    
    //Construtores -------------------------------------------------------------
    /**
     * Inicializa a carta, onde n é a carta do baralho e x e y as coordenadas da carta
     * @param x
     * @param y
     * @param n 
     */
    public Carta (int x, int y, int n) {
        this.multiplicador = 1;
        this.index = -1;
        this.selecionado = false;
        this.naipe = (n + 1) / 13;
        this.numero = (n + 1) % 13;

        //O caminho é da forma "img/2_of_clubs"
        String imgPath = "img/" + this.NumeroToString() + "_of_" + this.NaipeToString() + ".png";
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File(imgPath));
                hiddenSprite = ImageIO.read(new File("img/back.png"));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.rect = new Rectangle(x,y, 74, 94);
        GameManager.getInstance().adicionarRender((Renderizavel) this,0);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
        popUp = new PopUp(this);
    }
    
    /**
     * Inicializa a carta, onde n é a carta do baralho
     * @param n 
     */
    public Carta (int n) {
        this.multiplicador = 1;
        this.index = -1;
        this.selecionado = false;
        this.naipe = n / 13;
        this.numero = (n % 13) + 1;
        
        //O caminho é da forma "img/2_of_clubs"
        String imgPath = "img/" + this.NumeroToString() + "_of_" + this.NaipeToString() + ".png";
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File(imgPath));
                hiddenSprite = ImageIO.read(new File("img/back.png"));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.rect = new Rectangle(0,0,74,94);
        GameManager.getInstance().adicionarRender((Renderizavel) this, 0);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
        popUp = new PopUp(this);
    }
    
    //Funcoes de implementacao obrigatoria -------------------------------------
    @Override
    public int compareTo(Object t) {
        try {
            Carta c = (Carta) t;
            return compareTo(c);
        }
        catch(ClassCastException e) {
            return 0;
        }
    }

    /**
     * Compara a carta com outra carta
     * @param c
     * @return 
     */
    public int compareTo(Carta c) {
        if (this.numero == c.numero){
            if (this.naipe == c.naipe)
                return 0;
            return (this.naipe > c.naipe) ? 1:-1;
            }
            return (this.numero > c.numero) ? 1:-1;
    }
    
    @Override
    public String toString() {
        return NumeroToString() + " of " + NaipeToString();
    }
    
    @Override
    public void draw(Graphics2D g) {
        if(escondido){
            g.drawImage(hiddenSprite, rect.x, rect.y, rect.width, rect.height, null);
            return;
        }
        
        //Desenha a imagem da carta
        g.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
        popUp.draw(g);
        
        //Desenha um retangulo transparente com o estado da carta
        g.setColor (new Color(0,0,0,0));
        if (realizouAcao) {
            g.setColor (new Color(96,125,139, 120));
        }
        else if (selecionado)
            g.setColor(new Color(0,1,1,.2f));
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
        //Desenha retangulo de multiplicador
        switch(multiplicador) {
            case 2:
                g.setColor (new Color (198,255,0,120));
                break;
            case 3:
                g.setColor (new Color (139,195,74,120));
                break;
            case 4:
                g.setColor (new Color (118,255,3,120));
                break;
            default:
                g.setColor (new Color(0,0,0,0));
        }
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
    
    @Override
    public boolean isInside(int x, int y) {
        return rect.contains(x, y);
    }
    
    @Override
    public boolean isInside(Point p) {
        return rect.contains(p);
    }
    
    @Override
    public void onHover(int x, int y) {
        selecionado = true;
    }
    @Override
    public void onHover(Point p) {
        selecionado = true;
    }
    
    @Override
    public void onLeave() {
        selecionado = false;
    }
    
    @Override
    public Rectangle getRect() {
        return rect;
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 0);
    }
    
    //Funcoes privadas ---------------------------------------------------------
    /**
     * Transforma o numero da carta em String
     * @return numero
     */
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
    
    /**
     * Transforma o naipe em uma String
     * @return naipe
     */
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
    
    //Funcoes publicas ---------------------------------------------------------
    /**
     * Descarta a carta e desativa ela
     */
    public void descartar(){
        GameManager.getInstance().removerSelecionavel(this);
        boardParent.retiraCarta(index);
        jogador = -1;
        GameManager.getInstance().getDescarte().insereCarta(this);
        realizouAcao = false;
        boardParent = (BoardHolder) GameManager.getInstance().getDescarte();
    }
    
    /**
     * Retorna se a carta pode ser clicada ou nao, dado um jogador
     * @param jogador
     * @return clicavel
     */
    public boolean isClicavel(int jogador){
        return !realizouAcao && jogador == this.jogador;
    }
    
    public void move(java.awt.Rectangle dest) {
        TransladationTween t = new TransladationTween(this);
        t.setTarget(dest, .5f);
        Thread thread = new Thread(t);
        thread.start();
    }
    
    public void grow(float scale) {
        ScaleTween t = new ScaleTween(this);
        t.setScale(scale, 0.5f);
        Thread thread = new Thread(t);
        thread.start();
    }
    
    //Getters e setters --------------------------------------------------------
    /**
     * Seta Rect
     * @param x
     * @param y 
     */
    public void setRect(int x, int y) {
        rect.x = x;
        rect.y = y;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void setRect(int x, int y, int width, int height) {
        rect = new Rectangle(x,y,width,height);
    }

    /**
     * 
     * @param rect 
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
    /**
     * 
     * @param width
     * @param height 
     */
    public void setDimension(int width, int height) {
        rect.width = width;
        rect.height = height;
    }

    /**
     * 
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @return boardParent
     */
    public BoardHolder getBoardParent() {
        return boardParent;
    }

    /**
     * 
     * @param boardParent 
     */
    public void setBoardParent(BoardHolder boardParent) {
        this.boardParent = boardParent;
    }  
    
    /**
     * 
     * @return 
     */
    public int getNumero() {
        return numero;
    }

    /**
     * 
     * @return 
     */
    public int getNaipe() {
        return naipe;
    }

    /**
     * 
     * @return 
     */
    public int getJogador() {
        return jogador;
    }

    /**
     * 
     * @return 
     */
    public int getMultiplicador() {
        return multiplicador;
    }
    
    /**
     * 
     * @param jogador 
     */
    public void setJogador(int jogador) {
        this.jogador = jogador;
    }

    /**
     * 
     * @param realizouAcao 
     */
    public void setRealizouAcao(boolean realizouAcao) {
        this.realizouAcao = realizouAcao;
    }

    /**
     * 
     * @param multiplicador 
     */
    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getSelecionado() {
        return selecionado && !escondido;
    }

    /**
     * 
     * @param escondido 
     */
    public void setEscondido(boolean escondido) {
        this.escondido = escondido;
    }
    
    
    
}
