/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Arthur
 */

/*
    Tipo de carta que pode ser colocada no tabuleiro. Possui vida e todas as
funcoes relacionadas à ela.
*/

public abstract class Atacavel extends Carta {
    //Variaveis ----------------------------------------------------------------
    protected int vidaAtual;
    protected int maxVida;
    protected float vida;
    protected boolean onBoard;

    //Construtores -------------------------------------------------------------
    public Atacavel(int x, int y, int n) {
        super(x, y, n);
    }
    
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
    //Realiza dano na carta
    public void levarDano(int dano) {
        vidaAtual = vidaAtual - dano;
        vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        System.out.println("Nova vida: " + vida);
        if (vida <= 0) 
            die();
        else
            grow(0.5f);
    }
    
    //Recupera vida da carta
    public void recuperarVida(int cura) {
        if (estaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vidaAtual = maxVida * multiplicador;
            }
            vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        }
    }
    
    //Retorna se a carta esta viva
    public boolean estaVivo() {
        if (vida <= 0)
            die();
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Faz a carta morrer
    public void die() {
        vida = 1;
        descartar();
    }
    
    //Retorna se a carta pode ser atacada
    public boolean isAtacavel(){
        return onBoard;
    }
    
}
