/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

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
    public boolean Acao(Object o) {
        if(!realizouAcao) {
            if(isEnabled()) {
                if (o instanceof Atacavel) {
                    if (((Carta)o).isEnabled()) {
                       ((Atacavel) o).LevarDano(forca * multiplicador);
                       realizouAcao = true;
                        return true;
                    }
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
        vida = (float) vidaAtual/ maxVida;
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
        if (vida <= 0)
            Disable();
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
