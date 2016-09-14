/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
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
    
    public Atacante(int x, int y, int n) {
        super(x,y,n);
        maxVida = 10 - n;
        forca = n;
        vida = maxVida;
        vidaAtual = maxVida;
    }
    
    public Atacante(int n) {
        super(n);
        maxVida = 10 - n;
        forca = n;
        vida = maxVida;
        vidaAtual = maxVida;
    }
    
    @Override
    public void levarDano(int dano) {
        vidaAtual -= dano;
        vida = (float) vidaAtual/ maxVida;
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
        if (b.getJogador() == jogador && b != boardParent) {
            b.insereCarta(this);
            boardParent = b;
        }
        else if (b.getJogador() == inimigo) {
            //Ataca
        }
    }
    
    @Override
    public void onClick(BoardHolder b, Atacavel a) {
        int inimigo = (jogador == 1) ? 1:2;
        if (b.getJogador() == inimigo) {
            if ((5 - ((Carta)a).getIndex()) == this.index) {
                a.levarDano(forca * multiplicador);            
                System.out.println("Atacar!!!!!!");
            }
        }
    }

    @Override
    public void die() {
        boardParent.retiraCarta(index);
        destroy();
    }

}
