/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import java.awt.Color;
import java.util.ArrayList;

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

    //Funcoes publicas ---------------------------------------------------------
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
    
    @Override
    public ArrayList<String> getAtributos() {
        ArrayList<String> atributos = new ArrayList<>();
        
        atributos.add("Defensor");
        atributos.add("HP: " + String.valueOf(vidaAtual) + "/" + String.valueOf(maxVida * multiplicador));
        atributos.add("Multi.: x" + String.valueOf(multiplicador));
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.DARK_GRAY);
        colors.add(Color.RED);
        colors.add(Color.DARK_GRAY);

        return colors;
    }
}
