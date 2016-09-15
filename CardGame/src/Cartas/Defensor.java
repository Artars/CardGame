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
public class Defensor extends Carta implements Atacavel {

    private float vida;
    private int vidaAtual;
    private int maxVida;
    private boolean onBoard;

    public Defensor(int n) {
        super(n);
        maxVida = 2*n;
        vidaAtual = maxVida * multiplicador;
        vida = (float)(vidaAtual / (maxVida * multiplicador));
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
        if (vidaAtual <= 0)
            disable();
        return (vidaAtual > 0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }
    
    
    public void onClick(BoardHolder b) {
        int inimigo = (jogador == 1) ? 1:2;
        //Se colocou ou moveu dentro do tabuleiro
        if (b.getJogador() == jogador) {
            boardParent.retiraCarta(index);
            b.insereCarta(this);
            boardParent = b;
            //realizouAcao = true;
            onBoard = true;
        }
        //Foi pra pilha de descarte
        else if (b.getJogador() == 0) {
            boardParent.retiraCarta(index);
            b.insereCarta(this);
            boardParent = b;
        }
    }
    
    public void onClick(BoardHolder b, Atacavel a) {
        if (!realizouAcao && onBoard) {
            if (onBoard && b.getJogador() == jogador) {
                int otherIndex = ((Carta)a).getIndex();
                if (otherIndex != this.index) {
                    b.retiraCarta(otherIndex);
                    b.retiraCarta(index);
                    b.insereCarta((Carta)a, index);
                    b.insereCarta(this, otherIndex);
                    realizouAcao = true;
                }

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
