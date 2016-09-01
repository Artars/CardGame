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

    private int vida;
    private int maxVida;
    private boolean realizouAcao = false;

    public Defensor(int n) {
        super(n);
        vida = 2*n;
    }
   
    
    @Override
    public boolean Acao(Object o) {
        if(!realizouAcao) {
            if (o instanceof Point) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void LevarDano(int dano) {
        vida = vida - dano;
    }

    @Override
    public void RecurarVida(int cura) {
        if (EstaVivo()) {
            vida += cura;
            if (vida > maxVida) vida = maxVida;
        }
    }

    @Override
    public boolean EstaVivo() {
        return (vida > 0); //To change body of generated methods, choose Tools | Templates.
    }
    
}
