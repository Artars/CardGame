/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import cardgame.GameManager;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Clase abstrata de carta, base para todas as cartas presentes no jogo
 * 
 * @author Arthur
 */
public abstract class Carta implements Comparable, Selecionavel, Renderizavel, Serializable {
    
    //Variaveis estáticas da carta ---------------------------------------------
    protected int numero;
    protected int naipe;
    protected int multiplicador;
    protected int jogador;
    
    //Variaveis de sistema (Dinamicas) -----------------------------------------
    protected ImageIcon sprite;
    protected ImageIcon hiddenSprite;
    private int layer = 0;
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
        String imgPath = "img/" + this.SourceNumero() + "_of_" + this.SourceNaipe() + ".png";
        if(this.sprite == null)
                sprite = new ImageIcon(imgPath);
        if(this.hiddenSprite == null)
            hiddenSprite = new ImageIcon("img/back.png");
        
        
        this.rect = new Rectangle(x,y, 74, 94);
        GameManager.getInstance().adicionarRender((Renderizavel) this,layer);
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
        String imgPath = "img/" + this.SourceNumero() + "_of_" + this.SourceNaipe() + ".png";
        if(this.sprite == null)
                sprite = new ImageIcon(imgPath);
        if(this.hiddenSprite == null)
            hiddenSprite = new ImageIcon("img/back.png");
        
        this.rect = new Rectangle(0,0,74,94);
        GameManager.getInstance().adicionarRender((Renderizavel) this, layer);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
        popUp = new PopUp(this);
    }
    
    //Funcoes de implementacao obrigatoria -------------------------------------
    /**
     * Compara a carta com outra carta
     * @param t
     * @return 
     */
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
    
    /**
     * Retorna o nome da carta
     * @return nome
     */
    @Override
    public String toString() {
        return NumeroToString() + " de " + NaipeToString();
    }
    
    @Override
    public void draw(Graphics2D g) {
        if(escondido){
            g.drawImage(hiddenSprite.getImage(), rect.x, rect.y, rect.width, rect.height, null);
            return;
        }
        
        //Desenha a imagem da carta
        g.drawImage(sprite.getImage(), rect.x, rect.y, rect.width, rect.height, null);
        popUp.draw(g);
        
        //Desenha um retangulo transparente com o estado da carta
        g.setColor (new Color(0,0,0,0));
        if (realizouAcao) {
            g.setColor (new Color(0,0,0, 120));
        }
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
        //Desenha retangulo de multiplicador
        switch(multiplicador) {
            case 2:
                g.setColor (new Color (178,235,242,120));
                break;
            case 3:
                g.setColor (new Color (33,150,243,120));
                break;
            case 4:
                g.setColor (new Color (63,81,181,120));
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
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, layer);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, layer);
    }
    
    //Funcoes privadas ---------------------------------------------------------
    /**
     * Transforma o numero da carta em String
     * @return numero
     */
    private String NumeroToString(){
        switch(this.numero){
            case 1:
                return "Ás";
            case 11:
                return "Valete";
            case 12:
                return "Dama";
            case 13:
                return "Rei";
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
                return "Paus";
            case 1:
                return "Ouros";
            case 2:
                return "Copas";
            case 3:
                return "Espadas";
            default:
                return "Paus";
        }
    }

    
    /**
     * Transforma o naipe em uma String para abrir o arquivo de imagem
     * @return naipe
     */
    private String SourceNaipe(){
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
 
    /**
     * Transforma o numero da carta em String para se buscar o arquivo
     * @return numero
     */
    private String SourceNumero(){
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
    
    //Funcoes publicas ---------------------------------------------------------
    /**
     * Descarta a carta e desativa ela
     */
    public void descartar(){
        GameManager.getInstance().log(    
                            "Descarte," + this.toString() + "," +
                            this.boardParent + "," + this.index);
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
    
    /**
     * Começa uma animação para mover a carta até um ponto destino
     * @param dest 
     */
    public void move(java.awt.Rectangle dest) {
        TransladationAnimation a = new TransladationAnimation(this);
        a.addTarget(dest, .5f);
    }
    
    /**
     * Começa uma animação para mover a carta através de vários pontos, definindo os
     * pontos onde vai passar e os intervalos de tempo desse caminho
     * @param points
     * @param intervals 
     */
    public void movePath(java.awt.Rectangle[] points, float[] intervals) {
        TransladationAnimation a = new TransladationAnimation(this);
        for (int i = 0; i < points.length; i++) {
            a.addTarget(points[i], intervals[i]);
        }
    }
    
    /**
     * Método utilizado para realizar uma animação de alteração de escala
     * @param scale
     * @param duration 
     */
    public void grow(float scale, float duration) {
        ScaleAnimation a = new ScaleAnimation(this);
        a.setScale(scale, duration);
    }
    
    /**
     * Função utilizada para mover a carta entre diferentes camadas de
     * renderização, dada a camada destino.
     * @param newLayer 
     */
    public void changeLayer(int newLayer) {
        GameManager.getInstance().removerRender(this, layer);
        layer = newLayer;
        GameManager.getInstance().adicionarRender(this, layer);
    }
    
    /**
     * Cria um novo PopUp
     */
    public void newPop() {
        popUp = new PopUp(this);
    }
    
    
    //Getters e setters --------------------------------------------------------

    /**
     * Define um novo retângulo para carta
     * @param rect 
     */
    public void setRect(Rectangle rect) {
        this.rect = new Rectangle(rect);
    }

    /**
     * Define um novo index(de tabuleiro) para a carta.
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Retorna o index(de tabuleiro) da carta
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retorna o tabuleiro que está guardando esta carta
     * @return boardParent
     */
    public BoardHolder getBoardParent() {
        return boardParent;
    }

    /**
     * Define o tabuleiro que guarda a carta
     * @param boardParent 
     */
    public void setBoardParent(BoardHolder boardParent) {
        this.boardParent = boardParent;
    }  
    
    /**
     * Retorna o numero da carta
     * @return 
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna o naipe da carta
     * @return 
     */
    public int getNaipe() {
        return naipe;
    }

    /**
     * Retorna qual o jogador da carta
     * @return 
     */
    public int getJogador() {
        return jogador;
    }

    /**
     * Retorna o multiplicador da carta
     * @return 
     */
    public int getMultiplicador() {
        return multiplicador;
    }
    
    /**
     * Define o jogador da carta
     * @param jogador 
     */
    public void setJogador(int jogador) {
        this.jogador = jogador;
    }

    /**
     * Define se a carta pode ou não ser utilizada
     * @param realizouAcao 
     */
    public void setRealizouAcao(boolean realizouAcao) {
        this.realizouAcao = realizouAcao;
    }

    /**
     * Define o multiplicador da carta
     * @param multiplicador 
     */
    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
    
    /**
     * Retorna se a carta pode está selecionada
     * @return 
     */
    public boolean getSelecionado() {
        return selecionado && !escondido;
    }

    /**
     * Faz a carta permanecer com seu conteudo escondido
     * @param escondido 
     */
    public void setEscondido(boolean escondido) {
        this.escondido = escondido;
    }
}
