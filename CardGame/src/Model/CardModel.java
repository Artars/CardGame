/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import cardgame.GameManager;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class CardModel implements Serializable {

    private Baralho baralho;
    private BoardHolder[] boards;
    private int turno;
    
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
        turno = 1;
    }
    
    //Realiza a troca de turno para o jogador "turno"
    public void trocarTurno(int turno) {      
        
        this.turno = turno;
        boolean esconderTudo = (turno == 0);
        for(BoardHolder b:boards)
            b.cardVisibility(esconderTudo);
        
        if(!esconderTudo) {
            ComprarDeck(turno);
            boards[3].resetarAcoes();
            boards[1].resetarAcoes();
            
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
    }
    
    public int getTurno() {
        return turno;
    }
    
    public void ComprarDeck(int turno){
        int maoJogador = (turno == 1)? 4:0;
        
        int numCartasMao = boards[maoJogador].cardCount();
        int numCartasMesa = boards[(turno == 1)? 3:1].cardCount();
        
        int desiredCard;
        if(numCartasMesa > 4)
            desiredCard = 0;
        else if (numCartasMesa > 2)
            desiredCard = 1;
        else
            desiredCard = 2;
        numCartasMao = 5 - numCartasMao;
        
        int saque = Math.min(numCartasMao, desiredCard);
        if(saque > 0) {
            ArrayList<Carta> comprado = baralho.retirarCartas(saque);
            for(int i = 0; i < 5 && comprado.size() > 0; i++) {
                if (boards[maoJogador].getCarta(i) == null) {
                    boards[maoJogador].insereCarta(comprado.get(0), i);
                    boards[maoJogador].getCarta(i).setJogador(turno);
                    comprado.remove(0);
                }
            }
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
    
    public void updateGameManager(){
        GameManager g = GameManager.getInstance();
        for (BoardHolder b : boards) {
            g.adicionarRender(b, 0);
            g.adicionarSelecionavel(b);
        }
        for (BoardHolder b : boards) {
            for (int i = 0 ; i < 5; i++) {
                if(b.getCarta(i) != null) {
                    g.adicionarRender(b.getCarta(i), 0);
                    g.adicionarSelecionavel(b.getCarta(i));
                    b.getCarta(i).newPop();
                }
            }
        }
        g.trocarTurno(turno);
        g.setDescarte((Descarte) boards[2]);
        g.setBar(boards[1].getPlayer(), 1);
        g.setBar(boards[3].getPlayer(), 2);
        
        
    }
    
}