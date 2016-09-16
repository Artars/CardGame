/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Atacante;
import Cartas.Carta;
import Cartas.Defensor;
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
    
    private BoardHolder[] boards;
    
    //Board Location Reference
    private Point[] socketLocation;
    private int socketWidth;
    
    private Carta teste;
    
    public CardModel (){
        baralho = new Baralho(52);
        descarteFixo = new ArrayList<>();
        boards = new BoardHolder[5];
        boards[0] = new BoardHolder(3);
        boards[1] = new BoardHolder(2);
        boards[2] = new Descarte(0);
        boards[3] = new BoardHolder(1);
        boards[4] = new BoardHolder(3);
        socketLocation = new Point[7];
        for (int i =0; i<7; i++){
            socketLocation[i] = new Point(i*10,i*10);
            socketWidth = 10;
        }
        TesteLoucao();
        ColocarCartas();
    }
    
    public void ColocarCartas(){

    }
    
    public void TesteLoucao() {
        ArrayList<Carta> teste =  new ArrayList<>();
        teste.add(new Atacante(50));
        teste.add(new Atacante(49));
        teste.add(new Atacante(48));
        teste.add(new Defensor(5));
        teste.add(new Defensor(2));
        teste.add(new Atacante(48));
        teste.add(new Atacante(48));
        teste.add(new Atacante(48));
        teste.add(new Atacante(48));
        teste.get(0).setJogador(2);
        teste.get(1).setJogador(2);
        teste.get(2).setJogador(1);
        teste.get(3).setJogador(2);
        teste.get(4).setJogador(1);
        teste.get(5).setJogador(1);
        teste.get(6).setJogador(1);
        teste.get(7).setJogador(1);
        teste.get(8).setJogador(1);
        System.out.println(boards[0].insereCarta(teste.get(0), 2));
        boards[0].insereCarta(teste.get(1), 0);
        boards[2].insereCarta(teste.get(2), 0);
        boards[1].insereCarta(teste.get(3), 4);
        boards[4].insereCarta(teste.get(4), 3);
        boards[4].insereCarta(teste.get(5), 1);
        boards[4].insereCarta(teste.get(6), 2);
        boards[4].insereCarta(teste.get(7), 4);
        boards[4].insereCarta(teste.get(8), 0);
        Carta a = new Atacante(100,100,5);
    }
    
    /*public void TesteLoucao() {
        for (int i = 0; i < 0; i++)
            baralho.remove(0);
>>>>>>> Singleton
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
    */
    
    public void draw(Graphics2D g) {
        //teste.Draw(g);
    }
    
    /*
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
    */
    
    private void CreateBaralho(ArrayList<Integer> list){
        for (int i = 0; i < 52; i++)
            list.add(new Integer(i));
        Collections.shuffle(list);
    }
    
    public void UpdateBoardLocations(Point[] locations, int socketWidth, int socketHeight) {
        int i = 0;
        for (Point p :locations){
            if (i < 5)
                boards[i].setRect((int)p.getX(), (int)p.getY(), 5 * socketWidth, socketHeight);
            i++;
        }
        for (BoardHolder b: boards)
            b.setPocketDimensions(socketWidth - 6, socketHeight - 6);
        this.socketWidth = socketWidth;
        
        baralho.setRect((int) locations[5].getX(), (int) locations[5].getY());
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        draw((Graphics2D) arg);
    }

    public Carta getMao(int i, int j) {
        //if (i==1) return maoJogador1[j];
        //else if (i==2) return maoJogador2[j];
        return null;
    }

    public Carta getBoard(int jog,int index) {
        //if (jog==1) return boardJogador1[index];
        //else if (jog==2) return boardJogador2[index];
        return null;
    }
    
    public void descarte(Carta cartaSlc){
        
    }
    /*
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
    */
}