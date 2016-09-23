/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import cardgame.GameManager;
import javax.swing.JProgressBar;

/**
 *
 * @author Arthur
 */
public class Player {
    //Variaveis ----------------------------------------------------------------
    private int jogador;
    private int vidaMax;
    private int vidaAtual;
    private float vida;
    
    JProgressBar barra;
    
    //Construtores -------------------------------------------------------------
    public Player(int jogador, int vida) {
        this.jogador = jogador;
        this.vidaAtual = this.vidaMax = vida;
        this.vida = 0;
        
        barra = null;
    }
    
    //Funcao Privada -----------------------------------------------------------
    //Atualiza a barra para o tanto de vida do jogador
    private void updateBarra(){
        barra.setString(String.valueOf(vidaAtual) + "/" + String.valueOf(vidaMax));
        barra.setValue(vidaAtual);
    }
    
    //Funcoes Publicas ---------------------------------------------------------
    //Define a barra de vida do jogador
    public void setBarra(JProgressBar barra) {
        barra.setMinimum(0);
        barra.setMaximum(vidaMax);
        
        this.barra = barra;
        updateBarra();
    }
    
    //Retira vida do jogador
    public void perderVida(int dano) {
        vidaAtual -= dano;
        updateBarra();
        System.out.println("Jogador " + this.jogador + " perdeu " + dano + " de vida!");
        
        if (vidaAtual < 0) {
            GameManager.getInstance().gameOver(jogador);
        }
    }
    
    
    
}
