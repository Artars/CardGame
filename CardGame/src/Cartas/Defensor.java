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

/*
    Tipo de carta que pode trocar de posição no tabuleiro. Pode trocar de 
posicao com outra carta.
*/

public class Defensor extends Atacavel {

    public Defensor(int n) {
        super(n);
        maxVida = 2 * this.numero;
        vidaAtual = maxVida;
        vida = 1;
    }

    @Override
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }
       
    @Override
    public void onClick(BoardHolder b) {
        //Se colocou ou moveu dentro do tabuleiro
        if (b.getJogador() == jogador) {
            boardParent.retiraCarta(index);
            b.insereCarta(this);
            boardParent = b;
            realizouAcao = true;
            onBoard = true;
        }
        //Foi pra pilha de descarte
        else if (b.getJogador() == 0) {
            descartar();
        }
    }
    
    @Override
    public void onClick(BoardHolder b, Carta c) {
        Atacavel a;
        if(c instanceof Atacavel)
            a = (Atacavel) c;
        else {
            System.out.println("Deu Ruim!");
            return;
        }
            
        //Troca de posicao com outra carta
        if (!realizouAcao && onBoard) {
            if (b.getJogador() == jogador && a.isAtacavel()) {
                int otherIndex = ((Carta)a).getIndex();
                if (otherIndex != this.index) {
                    b.retiraCarta(otherIndex);
                    b.retiraCarta(index);
                    b.insereCarta((Carta)a, index);
                    b.insereCarta(this, otherIndex);
                    realizouAcao = true;
                }
            }
        }
    }

    
}
