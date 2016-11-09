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
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class GameManager implements Serializable{

   //Variaveis------------------------------------------------------------------
   private ArrayList<ArrayList<Renderizavel>> camadasRenderizaveis;
   private ArrayList<Selecionavel> selecionaveis;
   private Descarte descarte;
   private Player[] players;
   private int turno;
   private int rodada;
   private SceneManager sceneManager;
   private Logger logger;
   private ArrayList<javax.swing.JProgressBar> barras = null;
   
   //create an object of SingleObject
   private static GameManager instance = new GameManager();

   //Construtor-----------------------------------------------------------------
   
   /**
    * Construtor privado
    */
    private GameManager(){
       sceneManager = new SceneManager();
   }

    
    //Funcos Publicas ----------------------------------------------------------
   //Get the only object available
    /**
     * Retorna a instância única do Game Manager
     * @return 
     */
    public static GameManager getInstance(){
        return instance;
    }
   
    /**
     * Adiciona um renderizavel à uma camada de renderização. Camadas superiores
     * sao desenhadas por cima
     * @param r
     * @param camada 
     */
    public void adicionarRender(Renderizavel r, int camada) {
       while ( (camada + 1) > camadasRenderizaveis.size())
           camadasRenderizaveis.add(new ArrayList<Renderizavel>());
       camadasRenderizaveis.get(camada).add(r);
    }
   
    /**
     * Remove um renderizavel r de uma camada
     * @param r
     * @param camada 
     */
    public void removerRender(Renderizavel r, int camada) {
       camadasRenderizaveis.get(camada).remove(r);
    }
   
    /**
     * Adiciona um selecionavel à lista de objetos que podem ser clicados
     * @param s 
     */
    public void adicionarSelecionavel(Selecionavel s) {
       selecionaveis.add(s);
    }
   
    /**
     * Remove um selecionavel da lista, tornando-o não interagivel
     * @param s 
     */
    public void removerSelecionavel(Selecionavel s) {
       selecionaveis.remove(s);
    }

    /**
     * Retorna a lista com as diferentes camadas de renderizacao
     * @return 
     */
    public ArrayList<ArrayList<Renderizavel>> getRenderizaveis() {
        return camadasRenderizaveis;
    }
   
    /**
     * Procura nos selecionaveis o objeto desejado, dado um x e um y
     * @param x
     * @param y
     * @return ListaSelecionavel
     */
    public ArrayList<Selecionavel> findSelecionavel(int x, int y) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(x,y))
               selecao.add(s);
       }
       return selecao;
    }
    
    /**
     * Procura nos selecionaveis o objeto desejado, dado um ponto p
     * @param p
     * @return ListaSelecionavel
     */
    public ArrayList<Selecionavel> findSelecionavel(Point p) {
       ArrayList<Selecionavel> selecao = new ArrayList<>();
       for (Selecionavel s : selecionaveis) {
           if (s.isInside(p))
               selecao.add(s);
       }
       return selecao;
    }
   
    /**
     * Realiza a troca de turno do jogador 1 para 2 e vice-versa
     * @param turno 
     */  
    public void trocarTurno(int turno) {
        this.turno = turno;
        if (turno < 3) rodada++;
    }
    
    /**
     * Retorna o turno (1 ou 2)
     * @return 
     */
    public int getTurno(){
        return turno;
    }
    
    /**
     * Retorna que rodada se encontra o jogo
     * @return 
     */
    public int getRodada(){
        return rodada;
    }

    /**
     * Retorna o descarte
     * @return descarte
     */
    public Descarte getDescarte() {
        return descarte;
    }

    /**
     * Define o descarte
     * @param descarte 
     */
    public void setDescarte(Descarte descarte) {
        this.descarte = descarte;
    }
    
    /**
     * Adiciona uma barra na Lista de barras, para ser utilizada posteriormente
     * @param barra 
     */
    public void addBar(javax.swing.JProgressBar barra) {
        if (barras == null)
            barras = new ArrayList<>();
        barras.add(barra);
    }
    
    /**
     * Adiciona a barra de vida ao jogador (1 ou 2)
     * @param player
     * @param jogador 
     */
    public void setBar(Player player, int jogador) {
        if (jogador == 1 || jogador == 2) {
            jogador -= 1;
            if(barras.get(jogador) != null)
                player.setBarra(barras.get(jogador));
        }
    }
    
    /**
     * Define uma área de texto para o log
     * @param textArea 
     */
    public void setLog(javax.swing.JTextArea textArea) {
        logger = new Logger(textArea);
    }
    
    /**
     * Funcao que inicia o fim dos tempos
     * @param jogador 
     */
    public void gameOver(int jogador) {
        System.out.println("Jogador " + String.valueOf(jogador) + " perdeu!");
        players = new Player[2];
        startMenu();
    }
    
    /**
     * Inicia a tela inicial
     */
    public void startMenu(){
        sceneManager.startMenu();
    }
    
    /**
     * Apaga todas as listas de selecionaveis e renderizaveis
     */
    public void clearSystems() {
        camadasRenderizaveis = new ArrayList<>();
        selecionaveis = new ArrayList<>();
        turno = 1;
        rodada = 0;
        players = new Player[2];
        logger = new Logger(logger.logArea);
    }
    
    /**
     * Inicia o jogo
     */
    public void startGame(){
        camadasRenderizaveis = new ArrayList<>();
        selecionaveis = new ArrayList<>();
        turno = 1;
        rodada = 0;
        players = new Player[2];
        logger = null;
        sceneManager.startGame();
    }
    
    /**
     * Realiza um log
     * @param s 
     */
    public void log(String s) {
        logger.addLog(s);
    }
    
    /**
     * Adiciona uma nova animação a animações
     * @param a 
     */
    public void addAnimation(java.awt.event.ActionListener a) {
        sceneManager.addAnimation(a);
    }
    
    /**
     * Remove uma animação
     * @param a 
     */
    public void removeAnimation(java.awt.event.ActionListener a) {
        sceneManager.removeAnimation(a);
    }
}