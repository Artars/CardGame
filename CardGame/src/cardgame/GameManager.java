/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import Cartas.Clicavel;
import Cartas.Renderizavel;
import Cartas.Selecionavel;
import Model.Descarte;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class GameManager {

   ArrayList<Renderizavel> renderizaveis;
   ArrayList<Selecionavel> selecionaveis;
   Descarte descarte;
   private int turno;
   private int rodada;
   
   //create an object of SingleObject
   private static GameManager instance = new GameManager();

   //make the constructor private so that this class cannot be
   //instantiated
   private GameManager(){
       renderizaveis = new ArrayList<>();
       selecionaveis = new ArrayList<>();
       turno = 1;
       rodada = 0;
   }

   //Get the only object available
   public static GameManager getInstance(){
      return instance;
   }
   
   public void adicionarRender(Renderizavel r) {
       renderizaveis.add(r);
   }
   
   public void removerRender(Renderizavel r) {
       renderizaveis.remove(r);
   }
   
   public void adicionarSelecionavel(Selecionavel s) {
       selecionaveis.add(s);
   }
   
   public void removerSelecionavel(Selecionavel s) {
       selecionaveis.remove(s);
   }

    public ArrayList<Renderizavel> getRenderizaveis() {
        return renderizaveis;
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
}