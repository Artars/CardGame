/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Arthur
 */
public class Atacante extends Carta implements Atacavel {

    private int maxVida;
    private float vida;
    private int forca;
    private int vidaAtual;
    private boolean onBoard;
    
    public Atacante(int x, int y, int n) {
        super(x,y,n);
        maxVida = 10 - n;
        forca = n;
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }
    
    public Atacante(int n) {
        super(n);
        maxVida = 10 - n;
        forca = 1;
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }
    
    @Override
    public void levarDano(int dano) {
        vidaAtual -= dano;
        vida = (float)vidaAtual / (maxVida * multiplicador);
        System.out.println("Nova vida: " + vida);
        if (vida <= 0) 
            die();
    }

    @Override
    public void recuperarVida(int cura) {
        if (estaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vida = maxVida;
                vidaAtual = maxVida *multiplicador;
            } 
        }
    }

    @Override
    public boolean estaVivo() {
        if (vida <= 0)
            disable();
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }
    
    @Override
    public void onClick(BoardHolder b) {
        int inimigo = (jogador == 1) ? 1:2;
        //Se colocar em campo
        if ((b.getJogador() == jogador) && !onBoard) {
            boardParent.retiraCarta(index);
            b.insereCarta(this);
            boardParent = b;
            //realizouAcao = true;
            onBoard = true;
        }
        //Descarte
        else if (b.getJogador() == 0 && !onBoard) {
            boardParent.retiraCarta(index);
            b.insereCarta(this);
            boardParent = b;
        }
        //Atacar diretamente o jogador
        else if (b.getJogador() == inimigo) {
            //Ataca
        }
    }
    
    @Override
    public void onClick(BoardHolder b, Atacavel a) {
        int inimigo = (jogador == 1) ? 2:1;
        
        //Adicionar condicao onBoard
        if (((Carta) a).getJogador() == inimigo) {
            System.out.println("O index e: " + (((Carta)a).getIndex()) + "/" + this.index);
            if ((((Carta)a).getIndex()) == this.index) {
                a.levarDano(forca * multiplicador);            
                System.out.println("Atacar!!!!!!");
                //realizouAcao = true;
            }
        }
    }

    @Override
    public void die() {
        vida = 1;
        descartar();
    }
    
    @Override
    public void draw(Graphics2D g) {
        //int sizeHeight = g.getClip().getBounds().height / 6 - 6;
        //int sizeWidth = g.getClip().getBounds().width / 10 - 6;
        int newHeight = (int) ((1 - vida) * rect.height);
        
        g.setColor(Color.RED);
        
        g.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
        g.fillRect(rect.x, rect.y, rect.width, newHeight);
        
        g.setColor (new Color(0,0,0,0));
        if (realizouAcao) {
            g.setColor (new Color(96,125,139, 120));
        }
        else if (selecionado)
            g.setColor(new Color(0,1,1,.2f));
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
    }

}
