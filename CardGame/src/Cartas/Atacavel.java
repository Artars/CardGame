/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Arthur
 */

/*
    Tipo de carta que pode ser colocada no tabuleiro. Possui vida e todas as
funcoes relacionadas à ela.
*/

public abstract class Atacavel extends Carta {
    
    protected int vidaAtual;
    protected int maxVida;
    protected float vida;
    protected boolean onBoard;

    public Atacavel(int x, int y, int n) {
        super(x, y, n);
    }
    
    public Atacavel(int n) {
        super(n);
    }

    public void levarDano(int dano) {
        vidaAtual -= dano;
        vida = (float)vidaAtual / (maxVida * multiplicador);
        System.out.println("Nova vida: " + vida);
        if (vida <= 0) 
            die();
    }
    
    public void recuperarVida(int cura) {
        if (estaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vida = maxVida;
                vidaAtual = maxVida *multiplicador;
            } 
        }
    }
    
    public boolean estaVivo() {
        if (vida <= 0)
            disable();
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void die() {
        vida = 1;
        descartar();
    }
    
    public boolean isAtacavel(){
        return onBoard;
    }

    @Override
    public void draw(Graphics2D g) {
        //Desenha a imagem da carta
        g.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);
        
        //Desenha um retangulo com o tanto que a carta perdeu de vida
        int newHeight = (int) ((1 - vida) * rect.height);
        g.setColor(new Color(255,0,120));
        g.fillRect(rect.x, rect.y, rect.width, newHeight);
        
        //Desenha um retangulo transparente com o estado da carta
        g.setColor (new Color(0,0,0,0));
        if (realizouAcao) {
            g.setColor (new Color(96,125,139, 120));
        }
        else if (selecionado)
            g.setColor(new Color(0,1,1,.2f));
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
    }
    
    @Override
    public void setMultiplicador(int n) {
        multiplicador = n;
        
        vidaAtual = (int)(vida * maxVida * n);
    }
    
}
