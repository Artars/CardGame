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

    private Baralho baralho;
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
        baralho = new Baralho(52);
        descarteFixo = new ArrayList<>();
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
        maoJogador2[2] = (baralho.retirarCartas(1)).get(0);
        boardJogador1[4] = (baralho.retirarCartas(1)).get(0);
        boardJogador2[1] = (baralho.retirarCartas(1)).get(0);
        maoJogador1[2] = (baralho.retirarCartas(1)).get(0);
    }
    
    public void TesteLoucao() {
        for (int i = 0; i < 5; i++) {
            maoJogador2[i] = (baralho.retirarCartas(1)).get(0);
        }
        for (int i = 0; i < 5; i++) {
            boardJogador2[i] = (baralho.retirarCartas(1)).get(0);
        }
        for (int i = 0; i < 5; i++) {
            descarteDinamico[i] = (baralho.retirarCartas(1)).get(0);
        }
        for (int i = 0; i < 5; i++) {
            boardJogador1[i] = (baralho.retirarCartas(1)).get(0);
        }
        for (int i = 0; i < 5; i++) {
            maoJogador1[i] = (baralho.retirarCartas(1)).get(0);
        } 
    }
    
    public void draw(Graphics2D g) {
        baralho.Draw(g, 603, 251);
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
        else if (i==2) return maoJogador2[j];
        return null;
    }

    public Carta getBoard(int jog,int index) {
        if (jog==1) return boardJogador1[index];
        else if (jog==2) return boardJogador2[index];
        return null;
    }
    
    public void descarte(Carta cartaSlc){
        
    }
    
    public void pegarCartas (int jogador){
        int n=0;
        ArrayList<Carta> cards;
        
        if (jogador == 1){
            for (int i=0; i<5; i++) if (maoJogador1[i] == null) n++;
            if (n!=0 && n < 3){
                cards = baralho.retirarCartas(n);
                for (Carta c : cards) {
                    int i = 0;
                    while (maoJogador1[i] != null && i<5) i++;
                    if (i < 5)
                        maoJogador1[i] = c;
                }
            }
            else{
                cards = baralho.retirarCartas(2);
                for (Carta c : cards) {
                    int i = 0;
                    while (maoJogador1[i] != null && i<5) i++;
                    if (i < 5)
                        maoJogador1[i] = c;
                }
            }
        }
        else{
            for (int i=0; i<5; i++) if (maoJogador2[i] == null) n++;
            if (n!=0 && n<3){
                cards = baralho.retirarCartas(n);
                for (Carta c : cards) {
                    int i = 0;
                    while (maoJogador2[i] != null && i<5) i++;
                    if (i < 5)
                        maoJogador2[i] = c;
                }
            }              
            else{
                cards = baralho.retirarCartas(2);
                for (Carta c : cards) {
                    int i = 0;
                    while (maoJogador2[i] != null && i<5) i++;
                    if (i < 5)
                        maoJogador2[i] = c;
                }
            }
        }
    }
}