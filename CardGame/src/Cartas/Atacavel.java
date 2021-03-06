/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Main.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *  Tipo de carta que pode ser colocada no tabuleiro. Possui vida e todas as
 *  funcoes relacionadas à ela.
 * @author Arthur
 */
public abstract class Atacavel extends Carta {
    //Variaveis ----------------------------------------------------------------
    protected int vidaAtual;
    protected int maxVida;
    protected float vida;
    protected boolean onBoard;

    //Construtores -------------------------------------------------------------
    /**
     * Contrutor padrao com variaveis para as coordenadas x, y e numero da carta n
     * @param x
     * @param y
     * @param n 
     */
    public Atacavel(int x, int y, int n) {
        super(x, y, n);
    }
    
    /**
     * Construtor para um numero da carta n
     * @param n 
     */
    public Atacavel(int n) {
        super(n);
    }

    //Funcoes substituidas (Override)-------------------------------------------
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        //Desenha um retangulo com o tanto que a carta perdeu de vida
        int newHeight = (int) ((1 - vida) * rect.height);
        g.setColor(new Color(255,0,0,120));
        g.fillRect(rect.x, rect.y, rect.width, newHeight);
    }
    
    @Override
    public void setMultiplicador(int n) {
        multiplicador = n;
        if (multiplicador  < 0)
            multiplicador = 1;
        vidaAtual = (int)(vida * maxVida * n);
    }
    
    //Funcoes publicas ---------------------------------------------------------
    /**
     * Realiza dano na carta.
     * @param dano 
     */
    public void levarDano(int dano) {
        vidaAtual = vidaAtual - dano;
        vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        System.out.println("Nova vida: " + vida);
        if (vida <= 0) 
            die();
        else {
            ScaleAnimation a = new ScaleAnimation(this);
            a.setDelay(0.375f);
            a.setScale(0.5f, 0.25f);
            
            //grow(0.5f, 0.25f);
        }
    }
    
    /**
     * Recupera vida da carta
     * @param cura 
     */
    public void recuperarVida(int cura) {
        if (estaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vidaAtual = maxVida * multiplicador;
            }
            vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        }
    }
    
    /**
     * Retorna se a carta esta viva
     * @return viva
     */
    public boolean estaVivo() {
        if (vida <= 0)
            die();
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Faz a carta morrer
     */
    public void die() {
        vida = 1;
        descartar();
    }
    
    /**
     * Retorna se a carta pode ser atacada
     * @return atacavel
     */
    public boolean isAtacavel(){
        return onBoard;
    }
    
    /**
     * Move a carta para o tabuleiro b na posição n
     * @param b
     * @param n 
     */
    public void mover (Model.BoardHolder b, int n) {
        try {
            GameManager.getInstance().log(
                "Mover" + "," + this.toString() + "," + n 
                + "," + this.boardParent + "," + this.index
                + "," + b + "," + n);
            boardParent.retiraCarta(index);
        }
        catch(java.lang.IndexOutOfBoundsException e) {
            System.out.println (e.toString ());
            System.out.println("Posicao invalida");
        }
        b.insereCarta(this, n);
        boardParent = b;
        if(onBoard)
            realizouAcao = true;
        onBoard = true;
    }
    
}
