/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class CardModel implements Observer {

    private ArrayList<Integer> baralho;
    
    private ArrayList<Carta> maoJogador1;
    private ArrayList<Carta> maoJogador2;
    private Carta[] boardJog1;
    private Carta[] boardJog2;
    
    public CardModel (){
        baralho = new ArrayList<>();
        this.CreateBaralho (baralho);
        maoJogador1 = new ArrayList<>();
        maoJogador2 = new ArrayList<>();
        boardJog1 = new Carta[5];
        boardJog2 = new Carta[5];
    }
    
    private void CreateBaralho(ArrayList<Integer> list){
        for (int i = 0; i < 56; i++)
            list.add(new Integer(i));
        Collections.shuffle(list);
    }
    
    
    @Override
    public void update(Observable o, Object o1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
