/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import cardgame.GameManager;
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
        switch(numero) {
            case 1:
                maxVida = 11;
                break;
            case 11:
                maxVida = 8;
                break;
            case 12:
                maxVida = 8;
                break;
            case 13:
                maxVida = 8;
                break;    
            default:
                maxVida = this.numero;
        }
        maxVida *= 2;
        vidaAtual = maxVida;
        vida = 1;
    }

    //Funcoes publicas ---------------------------------------------------------
    @Override
    public void onClick(Object[] args) {
        int size = 0;
        if(args != null)
            size = args.length;
        
        try {
            switch(size) {
                case 0:
                    if (!realizouAcao)
                        selecionado = true;
                    grow(1.25f, 0.25f);
                    break;
                
                case 1:
                    //Se colocou ou moveu dentro do tabuleiro
                    BoardHolder b1 = (BoardHolder) args[0];
                    if (b1.getJogador() == jogador && b1.getIndex() != -1) {
                        mover(b1, b1.getIndex());
                    }
                    //Foi pra pilha de descarte
                    else if (b1.getJogador() == 0 && !onBoard) {      
                        descartar();
                    }
                    break;
                
                default:
                    BoardHolder b = (BoardHolder) args[0];
                    Atacavel a = (Atacavel) args[1];
                    
                    //Troca de posicao com outra carta
                    if (!realizouAcao && onBoard) {
                        if (b.getJogador() == jogador && a.isAtacavel()) {
                            trocar(b, a);
                        }
                    }
                    
            }
        
        }
        catch(ClassCastException e) {
        
        }
    }
    
    @Override
    public ArrayList<String> getAtributos() {
        ArrayList<String> atributos = new ArrayList<>();
        
        atributos.add("Defensor");
        atributos.add("HP: " + String.valueOf(vidaAtual) + "/" + String.valueOf(maxVida * multiplicador));
        atributos.add("Multi.: x" + String.valueOf(multiplicador));
        if(realizouAcao)
            atributos.add("Realizou ação");
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.DARK_GRAY);
        colors.add(Color.RED);
        colors.add(Color.DARK_GRAY);
        if(realizouAcao)
            colors.add(Color.BLACK);

        return colors;
    }
    
    public void trocar(BoardHolder b, Atacavel a) {
        int otherIndex = a.getIndex();
        if (otherIndex != this.index) {
            GameManager.getInstance().log(
        "Trocou" + "," + this.toString() + "," + a
        + "," + this.boardParent + "," + this.index
        + "," + a + "," + a.getIndex());
            b.retiraCarta(otherIndex);
            b.retiraCarta(index);
            b.insereCarta(a, index);
            b.insereCarta(this, otherIndex);
            realizouAcao = true;
        }
    }
}
