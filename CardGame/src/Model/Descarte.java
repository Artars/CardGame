/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import Main.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Classe responsável por manejar os descartes
 * @author Arthur
 */
public class Descarte extends BoardHolder {
    
    //Variaveis ----------------------------------------------------------------
    private boolean over;
    private int lastIndex;
    private ArrayList<Integer> descarteFixo;
    private BoardHolder[] boardJogadores;
    Carta lastCard;
    
    //Construtor ---------------------------------------------------------------
    /**
     * Construtor para o descarte, posicionando o mesmo no retangulo desejado
     * @param rect
     * @param player 
     */
    public Descarte(Rectangle rect, int player, int index) {
        super(rect, player, index);
        over = false;
        lastIndex = -1;
        descarteFixo = new ArrayList<>();
        boardJogadores = new BoardHolder[2];
        GameManager.getInstance().setDescarte((Descarte)this);
    }

    /**
     * Construtor para o descarte dado um player
     * @param player 
     */
    public Descarte(int player, int index) {
        super(player, index);
        over = false;
        lastIndex = -1;
        descarteFixo = new ArrayList<>();
        boardJogadores = new BoardHolder[2];
        GameManager.getInstance().setDescarte((Descarte)this);
    }
    
    //Funcoes Privadas ---------------------------------------------------------
    /**
     * Notifica os boards de alterações realizadas no descarte
     */
    private void updateBoards() {
        for (BoardHolder b: boardJogadores)
            b.atualizarMultiplicadores();
    }
    
    /**
     * Adiciona a carta na posicao indicada
     * @param c
     * @param n 
     */
    private void adicionarCarta(Carta c, int n) {
        cartas[n] = c;
        c.setIndex(n);
        c.move(getCardRect(n));
        c.setRealizouAcao(false);
        
    }
    
    //Funcoes Publicas ---------------------------------------------------------
    /**
     * Define os boards que o descarte notificará, dado o board e o jogador a
     * quem pertence
     * @param b
     * @param jogador 
     */
    public void setBoardJogadores(BoardHolder b, int jogador) {
        if(jogador == 1 || jogador == 2) {
            boardJogadores[jogador - 1] = b;
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        Color background = new Color(51,105,30);
        for (int i = 0; i < 5; i++) {
            Rectangle frame = getFrame(i);
            Color fillColor = Color.DARK_GRAY;
            g.setColor(fillColor);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
            g.setColor(background);
            frame = getCardRect(i);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
        }
        if(over) {
            Rectangle frame = getCardRect(0);
            g.setColor(Color.YELLOW);
            g.fillRect(rect.x, rect.y, rect.width, 3);
            g.fillRect(rect.x, rect.y + rect.height - 3, rect.width, 3);
            g.fillRect(rect.x, rect.y, 3, rect.height);
            g.fillRect(rect.x + rect.width - 3, rect.y, 3, rect.height);
        }
    }
    
   @Override
    public void onHover(int x, int y) {
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onHover(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onLeave() {
        over = false;
    }
    
    @Override
    public boolean insereCarta(Carta c) {
        if (lastIndex < 4) {
            lastIndex++;
            adicionarCarta(c, lastIndex);
        } 
        //Para o caso do descarte da mesa estar cheio
        else {
            //Esvazia a mesa
            for (int i = 0; i < 5; i++){
                descarteFixo.add(cartaToInteger(cartas[i]));
                java.awt.Rectangle destino = new java.awt.Rectangle(rect);
                destino.x -= pocketWidth + 6;
                destino.y += 3;
                destino.width = pocketWidth;
                destino.height = pocketHeight;
                cartas[i].move(destino);
                if(i==4) {
                    if (lastCard != null)
                        lastCard.removeRenderer();
                    lastCard = cartas[4];
                    cartas[4].changeLayer(1);
                }
            }
            for (int i = 0; i < 5; i++){
                //cartas[i].removeRenderer();
                cartas[i] = null;
            }
            lastIndex = 0;
            adicionarCarta(c, lastIndex);
        }
        updateBoards();
        return true;
    }
    
    @Override
    public boolean insereCarta(Carta c, int n) {
        if (lastIndex < 4) {
            lastIndex++;
            adicionarCarta(c, lastIndex);
        } 
        //Para o caso do descarte da mesa estar cheio
        else {
            //Esvazia a mesa
            for (int i = 0; i < 5; i++){
                descarteFixo.add(cartaToInteger(cartas[i]));
                cartas[i].removeRenderer();
                cartas[i] = null;
            }
            lastIndex = 0;
            adicionarCarta(c, lastIndex);
        }
        return true;
    }
    
    /**
     * Transforma a carta em um int que vai ser armazenado pelo descarte
     * @param c
     * @return integer
     */
    private Integer cartaToInteger(Carta c) {
        int result = c.getNaipe() * 13 + c.getNumero() - 1;
        return result;
    }
    
    /**
     * Retorna o vetor com as cartas
     * @return cartas
     */
    public Carta[] getCartas() {
        return cartas;
    }
    
    /**
     * Retorna todas as cartas armazenadas no descarte fixo e o limpa
     * @return descarte
     */
    public ArrayList<Integer> getDescarteList() {
        ArrayList<Integer> antigas = descarteFixo;
        descarteFixo = new ArrayList<>();
        lastCard.removeRenderer();
        lastCard = null;
        return antigas;
    }
    
}
