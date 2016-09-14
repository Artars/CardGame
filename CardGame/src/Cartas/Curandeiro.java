/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;

/**
 *
 * @author Arthur
 */
public class Curandeiro extends Carta {

    private int cura;

    public Curandeiro(int n) {
        super(n);
        this.cura = n;
    }

    @Override
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }

    @Override
    public void onClick(BoardHolder b, Atacavel a) {
        if (b.getJogador() == jogador) {
                a.recuperarVida(cura * multiplicador);
                realizouAcao = true;
                //Se descarta
        }
    }

    @Override
    public void onClick(BoardHolder b) {
        
    }
    
}
