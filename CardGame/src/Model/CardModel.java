/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Atacante;
import Cartas.Carta;
import Cartas.Defensor;
import cardgame.GameManager;
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
public class CardModel {

    private Baralho baralho;
    private BoardHolder[] boards;

    
    public CardModel (){
        baralho = new Baralho(52);
        boards = new BoardHolder[5];
        boards[0] = new BoardHolder(3);
        boards[1] = new BoardHolder(2);
        boards[2] = new Descarte(0);
        boards[3] = new BoardHolder(1);
        boards[4] = new BoardHolder(3);
        ((Descarte) boards[2]).setBoardJogadores(boards[3], 1);
        ((Descarte) boards[2]).setBoardJogadores(boards[1], 2);

        ComprarDeck(1);
    }
    
    //Realiza a troca de turno para o jogador "turno"
    public void trocarTurno(int turno) {      
        ComprarDeck(turno);
        
        boolean hidePlayer1 = (turno == 2);
        boards[4].cardVisibility(hidePlayer1);
        boards[0].cardVisibility(!hidePlayer1);
        boards[1].inverter();
        boards[3].inverter();
        
        java.awt.Rectangle aux;
        aux = boards[1].getRect();
        boards[1].setRect(boards[3].getRect());
        boards[3].setRect(aux);
    }
    
    //Compra cartas pra mao do jogador "turno"
    public void ComprarDeck(int turno){
        int i = 0;
        int vazio = 0;
        ArrayList <Carta> Comprado = new ArrayList <> ();
        
        if (turno == 1){
            while (i < 5 && vazio < 2){
                if (boards[4].getCarta(i) == null) vazio++;
                i++;
            }
            if (vazio > 0) Comprado = baralho.retirarCartas(vazio);

            i = 0;
            while (i < 5 && !Comprado.isEmpty()){
                if (boards[4].getCarta(i) == null){
                    boards[4].insereCarta(Comprado.get(0), i);
                    boards[4].getCarta(i).setJogador(1);
                    Comprado.remove(0);
                }
                i++;
            }
            boards[1].resetarAcoes();
        }
        else{
            while (i < 5 && vazio < 2){
                if (boards[0].getCarta(i) == null) vazio++;
                i++;
            }
            if (vazio > 0) Comprado = baralho.retirarCartas(vazio);

            i = 0;
            while (i < 5 && !Comprado.isEmpty()){
                if (boards[0].getCarta(i) == null){
                    boards[0].insereCarta(Comprado.get(0), i);
                    boards[0].getCarta(i).setJogador(2);
                    Comprado.remove(0);
                }
                i++;
            }    
            boards[3].resetarAcoes();
        }
    }
    
    //Ajusta e posiciona os boards
    public void UpdateBoardLocations(Point[] locations, int socketWidth, int socketHeight) {
        int i = 0;
        for (Point p :locations){
            if (i < 5)
                boards[i].setRect((int)p.getX(), (int)p.getY(), 5 * socketWidth, socketHeight);
            i++;
        }
        
        for (BoardHolder b: boards)
            b.setPocketDimensions(socketWidth - 6, socketHeight - 6);
        
        baralho.setRect((int) locations[5].getX() + (6 * socketWidth) + 3, 
                (int) locations[5].getY() + 3, socketWidth - 6, socketHeight - 6);
    }
    
}