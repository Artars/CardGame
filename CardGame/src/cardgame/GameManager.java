/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import Cartas.Renderizavel;
import Cartas.Selecionavel;
import Controller.CardController;
import Model.CardModel;
import Model.Descarte;
import Model.Player;
import View.BoardFrame;
import View.Table;
import View.TelaInicial;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class GameManager {

   //Variaveis------------------------------------------------------------------
   private ArrayList<ArrayList<Renderizavel>> camadasRenderizaveis;
   private ArrayList<Selecionavel> selecionaveis;
   private Descarte descarte;
   private Player[] players;
   private int turno;
   private int rodada;
   private SceneManager sceneManager;
   private Logger logger;
   
   //create an object of SingleObject
   private static GameManager instance = new GameManager();

   //Construtor-----------------------------------------------------------------
   
   //make the constructor private so that this class cannot be
   //instantiated
    private GameManager(){
       sceneManager = new SceneManager();
   }

    
    //Funcos Publicas ----------------------------------------------------------
   //Get the only object available
    public static GameManager getInstance(){
      return instance;
    }
   
    /*
    Adiciona um renderizavel à uma camada de renderização. Camadas superiores
    sao desenhadas por cima
    */
    public void adicionarRender(Renderizavel r, int camada) {
       while ( (camada + 1) > camadasRenderizaveis.size())
           camadasRenderizaveis.add(new ArrayList<Renderizavel>());
       camadasRenderizaveis.get(camada).add(r);
    }
   
    //Remove um renderizavel de uma camada
    public void removerRender(Renderizavel r, int camada) {
       camadasRenderizaveis.get(camada).remove(r);
    }
   
    //Adiciona um selecionavel à lista de objetos que podem ser clicados
    public void adicionarSelecionavel(Selecionavel s) {
       selecionaveis.add(s);
    }
   
    //Remove um selecionavel da lista, tornando-o não interagivel
    public void removerSelecionavel(Selecionavel s) {
       selecionaveis.remove(s);
    }

    //Retorna a lista com as diferentes camadas de renderizacao
    public ArrayList<ArrayList<Renderizavel>> getRenderizaveis() {
        return camadasRenderizaveis;
    }
   
    //Procura nos selecionaveis o objeto desejado, dado um x e um y
    public ArrayList<Selecionavel> findSelecionavel(int x, int y) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(x,y))
               selecao.add(s);
       }
       return selecao;
    }
    
    //Procura nos selecionaveis o objeto desejado, dado um ponto p
    public ArrayList<Selecionavel> findSelecionavel(Point p) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(p))
               selecao.add(s);
       }
       return selecao;
    }
   
    //Realiza a troca de turno do jogador 1 para 2 e vice-versa  
    public void trocarTurno(int turno) {
        this.turno = turno;
        if (turno != 0) rodada++;
    }
    
    //Retorna o turno (1 ou 2)
    public int getTurno(){
        return turno;
    }
    
    //Retorna que rodada se encontra o jogo
    public int getRodada(){
        return rodada;
    }

    //Retorna o descarte
    public Descarte getDescarte() {
        return descarte;
    }

    //Define o descarte
    public void setDescarte(Descarte descarte) {
        this.descarte = descarte;
    }
    
    //Adiciona a barra de vida ao jogador (1 ou 2)
    public void setBar(int jogador, javax.swing.JProgressBar barra) {
        if (jogador == 1 || jogador == 2) {
            if(players[jogador-1] == null) 
                players[jogador - 1] = new Player(jogador, 100);
            players[jogador - 1].setBarra(barra);
        }
    }
    
    public void setLog(javax.swing.JTextArea textArea) {
        logger = new Logger(textArea);
    }
    
    //Retorna o jogador desejado (1 ou 2)
    public Player getPlayer(int jogador) {
        if (jogador == 1 || jogador == 2) {
            if(players[jogador-1] == null) 
                players[jogador - 1] = new Player(jogador, 100);
            return players[jogador - 1];
        }
        else
            return null;
    }
    
    //Funcao que inicia o fim dos tempos
    public void gameOver(int jogador) {
        System.out.println("Jogador " + String.valueOf(jogador) + " perdeu!");
        players = new Player[2];
        startMenu();
    }
    
    public void startMenu(){
        sceneManager.startMenu();
    }
    
    public void startGame(){
        camadasRenderizaveis = new ArrayList<>();
        selecionaveis = new ArrayList<>();
        turno = 1;
        rodada = 0;
        players = new Player[2];
        logger = null;
        sceneManager.startGame();
    }
    
    public void log(String s) {
        logger.addLog(s);
    }
}