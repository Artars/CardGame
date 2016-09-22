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
    private int jogador;
    private int vidaMax;
    private int vidaAtual;
    private float vida;
    
    JProgressBar barra;
    
    public Player(int jogador, int vida) {
        this.jogador = jogador;
        this.vidaAtual = this.vidaMax = vida;
        this.vida = 0;
        
        barra = null;
    }
    
    public void setBarra(JProgressBar barra) {
        barra.setMinimum(0);
        barra.setMaximum(vidaMax);
        
        this.barra = barra;
        updateBarra();
    }
    
    private void updateBarra(){
        barra.setString(String.valueOf(vidaAtual) + "/" + String.valueOf(vidaMax));
        barra.setValue(vidaAtual);
    }
    
    public void perderVida(int dano) {
        vidaAtual -= dano;
        updateBarra();
        System.out.println("Jogador " + this.jogador + " perdeu " + dano + " de vida!");
        
        if (vidaAtual < 0) {
            GameManager.getInstance().gameOver(jogador);
        }
    }
    
    
    
}
