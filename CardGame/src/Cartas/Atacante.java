/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cartas;

import Model.BoardHolder;
import cardgame.GameManager;
import java.awt.Color;
import java.util.ArrayList;

/**
 *  Classe capaz de atacar outras cartas ou o jogador
 * @author Arthur
 */
public class Atacante extends Atacavel {

    private int forca;
    
    //Construtor ---------------------------------------------------------------
    /**
     * Contrutor do atacante usando as posições x e y e o número n da carta
     * @param x
     * @param y
     * @param n 
     */
    public Atacante(int x, int y, int n) {
        super(x,y,n);
        switch(this.numero) {
            case 1:
                maxVida = 1;
                forca = 11;
                break;
            case 11:
                maxVida = 8;
                forca = 8;
                break;
            case 12:
                maxVida = 12;
                forca = 8;
                break;
            case 13:
                maxVida = 8;
                forca = 12;
                break;    
            default:
                maxVida = 12 - this.numero;
                forca = this.numero;   
        }
        
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }
    
    /**
     * Contrutor do atacantes usando o numero n da carta
     * @param n 
     */
    public Atacante(int n) {
        super(n);
        switch(this.numero) {
            case 1:
                maxVida = 1;
                forca = 11;
                break;
            case 11:
                maxVida = 8;
                forca = 8;
                break;
            case 12:
                maxVida = 12;
                forca = 8;
                break;
            case 13:
                maxVida = 8;
                forca = 12;
                break;    
            default:
                maxVida = 12 - this.numero;
                forca = this.numero;   
        }
        
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }

    //Funcoes publicas ---------------------------------------------------------
    /**
     * Função de clique com argumentos opcionais
     * @param args
     */
    @Override
    public void onClick(Object[] args) {
        int size = 0;
        if(args != null)
            size = args.length;
        int inimigo = (jogador == 1) ? 2:1;
        
        try {
            switch(size) {
                case 0:
                    if (!realizouAcao)
                        selecionado = true;
                    grow(1.25f, 0.25f);
                    break;

                //Caso tenha clicado só no board
                case 1:
                    //Se colocar em campo
                    BoardHolder b = (BoardHolder) args[0];
                    
                    if ((b.getJogador() == jogador) && !onBoard && b.getIndex() != -1) {
                        mover(b, b.getIndex());
                    }
                    //Descarte
                    else if (b.getJogador() == 0 && !onBoard) {
                        descartar();
                    }
                    //Atacar diretamente o jogador
                    else if (onBoard && b.getJogador() == inimigo) {
                        ataque(b,b.getIndex(),false);
                    }
                    break;

                default:
                    Atacavel a = (Atacavel) args[1];

                    if (a.getJogador() == inimigo && a.isAtacavel()) {
                        if (a.getIndex() == this.index) {            
                            ataque(a,false);
                        }
                    }
                    break;
            }
        }
        catch (ClassCastException e){
            
        }
    }

    @Override
    public ArrayList<String> getAtributos() {
        ArrayList<String> atributos = new ArrayList<>();
        
        atributos.add("Atacante");
        atributos.add("HP: " + String.valueOf(vidaAtual) + "/" + String.valueOf(maxVida * multiplicador));
        atributos.add("STR: " + String.valueOf(forca * multiplicador));
        atributos.add("Multi.: x" + String.valueOf(multiplicador));
        if(realizouAcao)
            atributos.add("Realizou ação");
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.DARK_GRAY);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.DARK_GRAY);
        if(realizouAcao)
            colors.add(Color.BLACK);

        return colors;
    }

    /**
     * Realiza o ataque sobre uma carta a. Possui uma variável invertido para
     * inverter o sentido de movimento de ataque.
     * @param a
     * @param invertido 
     */
    public void ataque(Atacavel a, boolean invertido) {
        realizouAcao = true;
        GameManager.getInstance().log(
        "Ataque," + this.toString() + "," + a.toString() + "," +
        this.boardParent + "," + this.index + "," +
        a.getBoardParent() + "," + a.getIndex());
        attackMovement(invertido);
        a.levarDano(forca * multiplicador);
    }
    
    /**
     * Realiza o ataque sobre um tabuleiro. Possui uma variável invertido para
     * inverter o sentido de movimento de ataque.
     * @param b
     * @param invertido 
     */
    public void ataque(BoardHolder b, int index, boolean invertido) {
        realizouAcao = true;
        GameManager.getInstance().log(
        "Ataque," + this.toString() + "," + "Jogador" + b.getJogador() + "," +
        this.boardParent + "," + this.index + "," +
        b + "," + index);
        attackMovement(invertido);
        b.levaDano(index, forca * multiplicador);
    }

    
    //Funções privadas ---------------------------------------------------------
    /**
     * Realiza a animação de ataque, pode ser invertido.
     * @param invertido 
     */
    private void attackMovement(boolean invertido) {
        int sinal = (invertido) ? -1:1;
        java.awt.Rectangle[] points = new java.awt.Rectangle[3];
        points[0] = new java.awt.Rectangle(rect);
        points[1] = new java.awt.Rectangle(rect);
        points[2] = new java.awt.Rectangle(rect);
        points[0].y += 20 * sinal;
        points[1].y -= 20 * sinal;
        float[] durations = new float[3];
        durations[0] = .25f;
        durations[1] = .125f;
        durations[2] = .125f;
        movePath(points, durations);
        ScaleAnimation s = new ScaleAnimation(this);
        s.setDelay(.25f);
        s.setScale(1.25f, .125f);
    }
   
}
