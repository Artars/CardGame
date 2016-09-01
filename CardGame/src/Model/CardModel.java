/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Atacante;
import Cartas.Carta;
import java.awt.Graphics2D;
import java.awt.Point;
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
    private ArrayList<Integer> descarteFixo;
    
    private Carta[] maoJogador1;
    private Carta[] maoJogador2;
    private Carta[] boardJogador1;
    private Carta[] boardJogador2;
    private Carta[] descarteDinamico;
    
    //Board Location Reference
    private Point[] socketLocation;
    private int socketWidth;
    
    
    public CardModel (){
        baralho = new ArrayList<>();
        descarteFixo = new ArrayList<>();
        this.CreateBaralho (baralho);
        maoJogador1 = new Carta[5];
        maoJogador2 = new Carta[5];
        boardJogador1 = new Carta[5];
        boardJogador2 = new Carta[5];
        descarteDinamico = new Carta[5];
        socketLocation = new Point[7];
        for (int i =0; i<7; i++){
            socketLocation[i] = new Point(i*10,i*10);
            socketWidth = 10;
        }
        //TesteLoucao();
        ColocarCartas();
    }
    
    public void ColocarCartas(){
        maoJogador2[2] = new Atacante(baralho.get(0));
        baralho.remove(0);
        boardJogador1[4] = new Atacante(baralho.get(0));
        baralho.remove(0);
        boardJogador2[1] = new Atacante(baralho.get(0));
        baralho.remove(0);
    }
    
    public void TesteLoucao() {
        for (int i = 0; i < 0; i++)
            baralho.remove(0);
        for (int i = 0; i < 5; i++) {
            maoJogador2[i] = new Atacante(baralho.get(0));
            baralho.remove(0);
        }
        for (int i = 0; i < 5; i++) {
            boardJogador2[i] = new Atacante(baralho.get(0));
            baralho.remove(0);
        }
        for (int i = 0; i < 5; i++) {
            descarteDinamico[i] = new Atacante(baralho.get(0));
            baralho.remove(0);
        }
        for (int i = 0; i < 5; i++) {
            boardJogador1[i] = new Atacante(baralho.get(0));
            baralho.remove(0);
        }
        for (int i = 0; i < 5; i++) {
            maoJogador1[i] = new Atacante(baralho.get(0));
            baralho.remove(0);
        } 
    }
    
    public void draw(Graphics2D g) {
        int section = 0;
        for(int i = 0; i < 5; i++) {
            if (maoJogador2[i] != null)
                maoJogador2[i].Draw(g, (int) socketLocation[section].getX() + (socketWidth + 6) * i, (int) socketLocation[section].getY());
        }
        
        section++;
        for(int i = 0; i < 5; i++) {
            if (boardJogador2[i] != null)
                boardJogador2[i].Draw(g, (int) socketLocation[section].getX() + (socketWidth + 6) * i, (int) socketLocation[section].getY());
        }
        
        section++;
        for(int i = 0; i < 5; i++) {
            if (descarteDinamico[i] != null)
                descarteDinamico[i].Draw(g, (int) socketLocation[section].getX() + (socketWidth + 6) * i, (int) socketLocation[section].getY());
        }
        
        section++;
        for(int i = 0; i < 5; i++) {
            if (boardJogador1[i] != null)
                boardJogador1[i].Draw(g, (int) socketLocation[section].getX() + (socketWidth + 6) * i, (int) socketLocation[section].getY());
        }
        
        section++;
        for(int i = 0; i < 5; i++) {
            if (maoJogador1[i] != null)
                maoJogador1[i].Draw(g, (int) socketLocation[section].getX() + (socketWidth + 6) * i, (int) socketLocation[section].getY());
        }
       
    }
    
    private void CreateBaralho(ArrayList<Integer> list){
        for (int i = 0; i < 52; i++)
            list.add(new Integer(i));
        Collections.shuffle(list);
    }
    
    public void UpdateBoardLocations(Point[] locations, int socketWidth) {
        int i = 0;
        for (Point p :locations){
            socketLocation[i] = p;
            i++;
        }
        this.socketWidth = socketWidth;
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }

    public Carta getMao(int i, int j) {
        if (i==1) return maoJogador1[j];
        else  return maoJogador2[j];
    }

    public Carta getBoard(int jog,int index) {
        if (jog==1) return boardJogador1[index];
        else if (jog==2) return boardJogador2[index];
        return null;
    }
    
    public void descarte(Carta cartaSlc){
        
    }
}
