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
public class Atacante extends Atacavel {

    private int forca;
    
    public Atacante(int x, int y, int n) {
        super(x,y,n);
        switch(this.numero) {
            case 1:
                maxVida = 11;
                forca = 11;
                break;
            case 11:
                maxVida = 10;
                forca = 10;
                break;
            case 12:
                maxVida = 15;
                forca = 10;
                break;
            case 13:
                maxVida = 10;
                forca = 15;
                break;    
            default:
                maxVida = 12 - this.numero;
                forca = this.numero;   
        }
        
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }
    
    public Atacante(int n) {
        super(n);
        maxVida = 10 - n;
        forca = n;
        vidaAtual = maxVida * multiplicador;
        vida = (float) vidaAtual / (maxVida * multiplicador);
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
            realizouAcao = true;
            onBoard = true;
        }
        //Descarte
        else if (b.getJogador() == 0 && !onBoard) {
            descartar();
        }
        //Atacar diretamente o jogador
        else if (b.getJogador() == inimigo) {
            //Ataca
        }
    }
    
    @Override
    public void onClick(BoardHolder b, Carta c) {
        int inimigo = (jogador == 1) ? 2:1;
        Atacavel a;
        
        if(c instanceof Atacavel)
            a = (Atacavel) c;
        else
            return;
        //Adicionar condicao onBoard
        if (a.getJogador() == inimigo && a.isAtacavel()) {
            System.out.println("O index e: " + a.getIndex() + "/" + this.index);
            if (a.getIndex() == this.index) {
                a.levarDano(forca * multiplicador);            
                System.out.println("Atacar!!!!!!");
                realizouAcao = true;
            }
        }
    }

}
