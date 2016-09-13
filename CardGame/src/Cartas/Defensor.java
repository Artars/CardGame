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
public class Defensor extends Carta implements Atacavel {

    private float vida;
    private int vidaAtual;
    private int maxVida;

    public Defensor(int n) {
        super(n);
        vida = maxVida = 2*n;
        vidaAtual = maxVida * multiplicador;
    }
   
    @Override
    public void LevarDano(int dano) {
        vidaAtual -= dano;
        vida = (float) vidaAtual / maxVida;
    }

    @Override
    public void RecuperarVida(int cura) {
        if (EstaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vida = maxVida;
                vidaAtual = maxVida *multiplicador;
            } 
        }
    }


    @Override
    public boolean EstaVivo() {
        if (vidaAtual <= 0)
            disable();
        return (vidaAtual > 0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }
    
    
    public void onClick(BoardHolder b, Point p) {
        int inimigo = (jogador == 1) ? 1:2;
        if (b.getJogador() == jogador) {
            b.insereCarta(this);
            boardParent = b;
        }
        else if (b.getJogador() == inimigo) {
            //Ataca
        }
    }
    
    public void onClick(BoardHolder b, Atacavel a) {
        int inimigo = (jogador == 1) ? 1:2;
        if (b.getJogador() == jogador) {
            int otherIndex = ((Carta)a).getIndex();
            if (otherIndex != this.index) {
                b.retiraCarta(otherIndex);
                
            }
                           
        }
    }
    
}
