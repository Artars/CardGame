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
        vidaAtual = vidaAtual - dano;
        vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        System.out.println("Nova vida: " + vida);
        if (vida <= 0) 
            die();
    }
    
    public void recuperarVida(int cura) {
        if (estaVivo()) {
            vidaAtual += cura;
            if (vidaAtual > maxVida * multiplicador) {
                vidaAtual = maxVida * multiplicador;
            }
            vida = (float)vidaAtual / (float)(maxVida * multiplicador);
        }
    }
    
    public boolean estaVivo() {
        if (vida <= 0)
            die();
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
        g.setColor(new Color(255,0,0,120));
        g.fillRect(rect.x, rect.y, rect.width, newHeight);
        
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
                g.setColor (new Color (118,255,3));
                break;
            default:
                g.setColor (new Color(0,0,0,0));
        }
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
        if(selecionado) {
            g.setFont(new Font( "SansSerif", Font.TRUETYPE_FONT, 14 ));
            g.setColor(new Color(255,255,255,200));
            Rectangle popUpRect = new Rectangle(rect.x + rect.width/2, rect.y - rect.height/2, rect.width, (rect.height*3)/8);
            int lineSize = 15;
            g.fillRect(popUpRect.x - 3, popUpRect.y, popUpRect.width + 3, popUpRect.height);
            g.setColor(Color.RED);
            g.drawString("HP: " + String.valueOf(vidaAtual) + "/" + String.valueOf(maxVida),
                    popUpRect.x, popUpRect.y + lineSize);
            g.setColor(Color.BLACK);
            g.drawString("Mult.: x" + String.valueOf(multiplicador),
                    popUpRect.x, popUpRect.y + lineSize*2);
        }
        
        if (onBoard) {
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(vidaAtual) + "/" + String.valueOf(maxVida) + "x" + String.valueOf(multiplicador),
                    rect.x, rect.y);
        }
    }
    
    @Override
    public void setMultiplicador(int n) {
        multiplicador = n;
        if (multiplicador  < 0)
            multiplicador = 1;
        vidaAtual = (int)(vida * maxVida * n);
    }
    
}
