/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import Cartas.Renderizavel;
import Cartas.Selecionavel;
import Model.Descarte;
import Model.Player;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class GameManager {

   private ArrayList<ArrayList<Renderizavel>> camadasRenderizaveis;
   private ArrayList<Selecionavel> selecionaveis;
   private Descarte descarte;
   private Player[] players;
   private int turno;
   private int rodada;
   
   
   //create an object of SingleObject
   private static GameManager instance = new GameManager();

   //make the constructor private so that this class cannot be
   //instantiated
   private GameManager(){
       camadasRenderizaveis = new ArrayList<>();
       selecionaveis = new ArrayList<>();
       turno = 1;
       rodada = 0;
       players = new Player[2];
   }

   //Get the only object available
   public static GameManager getInstance(){
      return instance;
   }
   
   public void adicionarRender(Renderizavel r, int camada) {
       while ( (camada + 1) > camadasRenderizaveis.size())
           camadasRenderizaveis.add(new ArrayList<Renderizavel>());
       camadasRenderizaveis.get(camada).add(r);
   }
   
   public void removerRender(Renderizavel r, int camada) {
       camadasRenderizaveis.get(camada).remove(r);
   }
   
   public void adicionarSelecionavel(Selecionavel s) {
       selecionaveis.add(s);
   }
   
   public void removerSelecionavel(Selecionavel s) {
       selecionaveis.remove(s);
   }

    public ArrayList<ArrayList<Renderizavel>> getRenderizaveis() {
        return camadasRenderizaveis;
    }
   
    public ArrayList<Selecionavel> findSelecionavel(int x, int y) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(x,y))
               selecao.add(s);
       }
       return selecao;
   }
    
    public ArrayList<Selecionavel> findSelecionavel(Point p) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(p))
               selecao.add(s);
       }
       return selecao;
   }
   
    public void trocarTurno() {
        if (turno == 1) turno = 2;
        else turno = 1;
        rodada++;
    }
    
    public int getTurno(){
        return turno;
    }
    
    public int getRodada(){
        return rodada;
    }

    public Descarte getDescarte() {
        return descarte;
    }

    public void setDescarte(Descarte descarte) {
        this.descarte = descarte;
    }
    
    public void setBar(int jogador, javax.swing.JProgressBar barra) {
        if (jogador < 3 && jogador > 0) {
            if(players[jogador-1] == null) 
                players[jogador - 1] = new Player(jogador, 100);
            players[jogador - 1].setBarra(barra);
        }
    }
    
    public Player getPlayer(int jogador) {
        if (jogador < 3 && jogador > 0) {
            if(players[jogador-1] == null) 
                players[jogador - 1] = new Player(jogador, 100);
            return players[jogador - 1];
        }
        else
            return null;
    }
    
    public void gameOver(int jogador) {
        System.out.println("Jogador " + String.valueOf(jogador) + " perdeu!");
    }
}