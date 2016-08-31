/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import java.awt.geom.Rectangle2D;
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
    private ArrayList<Integer> descarteDinamico;
    
    private ArrayList<Carta> maoJogador1;
    private ArrayList<Carta> maoJogador2;
    private Carta[] boardJog1;
    private Carta[] boardJog2;
    private Carta[] descarteFixo;
    
    
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
    
    private void CreateWorkspaces(Rectangle2D[] workspace) {
        /*
            Sendo a estrutura do menu dada por
        0 - Mão Jogador Outro
        1 - Mesa Jogador Outro
        2 - Mesa Descarte
        3 - Mesa Jogador Atual
        4 - Mão Jogador Atual
        */
    }
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
