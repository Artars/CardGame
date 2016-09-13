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

    Baralho baralho;
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
        boards[0] = new BoardHolder(0);
        boards[1] = new BoardHolder(2);
        boards[2] = new BoardHolder(0);
        boards[3] = new BoardHolder(1);
        boards[4] = new BoardHolder(0);
        socketLocation = new Point[7];
        for (int i =0; i<7; i++){
            socketLocation[i] = new Point(i*10,i*10);
            socketWidth = 10;
        }
        //TesteLoucao();
        ColocarCartas();
    }
    
    public void ColocarCartas(){
        ArrayList<Carta> teste =  baralho.retirarCartas(5);
        System.out.println(boards[0].insereCarta(teste.get(0), 2));
        boards[0].insereCarta(teste.get(1), 0);
        boards[3].insereCarta(teste.get(2), 4);
        boards[1].insereCarta(teste.get(3), 4);
        boards[4].insereCarta(teste.get(4), 3);
        Carta a = new Atacante(100,100,5);
    }
    
    /*public void TesteLoucao() {
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
}
