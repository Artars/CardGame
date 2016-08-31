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
    private int vida;
    private int forca;
    private boolean atacou = false;
    
    public Atacante(int n) {
        super(n);
        maxVida = 10 - n;
        forca = n;
        vida = maxVida;
    }

    public void Atacar (Atacavel a) {
    }
    
    @Override
    public void ColocarCarta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void LevarDano(int dano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
