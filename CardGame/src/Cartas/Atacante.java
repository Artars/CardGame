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
    private boolean realizouAcao = false;
    
    public Atacante(int n) {
        super(n);
        maxVida = 10 - n;
        forca = n;
        vida = maxVida;
    }

    @Override
    public boolean Acao(Object o) {
        if(!realizouAcao) {
            if (o instanceof Atacavel) {
                if (((Carta)o).enable) {
                    ((Atacavel) o).LevarDano(forca * multiplicador);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void LevarDano(int dano) {
        vida = vida - dano; //To change body of generated methods, choose Tools | Templates.
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
        return (vida > 0);
    }
    
}
