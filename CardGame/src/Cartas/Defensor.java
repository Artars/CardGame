/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;

/**
 *
 * @author Arthur
 */
public class Defensor extends Carta implements Atacavel {

    private float vida;
    private int vidaAtual;
    private int maxVida;
    private boolean realizouAcao = false;

    public Defensor(int n) {
        super(n);
        vida = maxVida = 2*n;
        vidaAtual = maxVida * multiplicador;
    }
   
    
    @Override
    public boolean Acao(Object o) {
        if(!realizouAcao) {
            if (isEnabled()) {
                if (o instanceof Point) {
                    realizouAcao = true;
                    return true;
                }
                return false;
            } else {
                realizouAcao = true;
                Enable();
                return true;
            }
        }
        return false;
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
            Disable();
        return (vidaAtual > 0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
