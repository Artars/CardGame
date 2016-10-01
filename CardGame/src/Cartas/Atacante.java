/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Atacante extends Atacavel {

    private int forca;
    
    //Construtor ---------------------------------------------------------------
    public Atacante(int x, int y, int n) {
        super(x,y,n);
        switch(this.numero) {
            case 1:
                maxVida = 11;
                forca = 11;
                break;
            case 11:
                maxVida = 10;
                forca = 10;
                break;
            case 12:
                maxVida = 15;
                forca = 10;
                break;
            case 13:
                maxVida = 10;
                forca = 15;
                break;    
            default:
                maxVida = 12 - this.numero;
                forca = this.numero;   
        }
        
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }
    
    public Atacante(int n) {
        super(n);
        switch(this.numero) {
            case 1:
                maxVida = 11;
                forca = 11;
                break;
            case 11:
                maxVida = 10;
                forca = 10;
                break;
            case 12:
                maxVida = 15;
                forca = 10;
                break;
            case 13:
                maxVida = 10;
                forca = 15;
                break;    
            default:
                maxVida = 12 - this.numero;
                forca = this.numero;   
        }
        
        vidaAtual = maxVida;
        vida = (float) vidaAtual / (maxVida * multiplicador);
    }

    //Funcoes publicas ---------------------------------------------------------
    @Override
    public void onClick(Object[] args) throws IllegalArgumentException {
        int size = 0;
        if(args != null)
            size = args.length;
        int inimigo = (jogador == 1) ? 2:1;
        
        try {
            switch(size) {
                case 0:
                    if (!realizouAcao)
                        selecionado = true;
                    grow(1.5f);
                    break;

                //Caso tenha clicado só no board
                case 1:
                    //Se colocar em campo
                    BoardHolder b = (BoardHolder) args[0];
                    
                    if ((b.getJogador() == jogador) && !onBoard && b.getIndex() != -1) {
                        boardParent.retiraCarta(index);
                        b.insereCarta(this);
                        boardParent = b;
                        realizouAcao = true;
                        onBoard = true;
                    }
                    //Descarte
                    else if (b.getJogador() == 0 && !onBoard) {
                        descartar();
                    }
                    //Atacar diretamente o jogador
                    else if (onBoard && b.getJogador() == inimigo) {
                        b.levaDano(index, forca * multiplicador);
                        realizouAcao = true;
                        attackMovement();
                    }
                    break;

                default:
                    Atacavel a = (Atacavel) args[1];

                    if (a.getJogador() == inimigo && a.isAtacavel()) {
                        if (a.getIndex() == this.index) {
                            a.levarDano(forca * multiplicador);            
                            realizouAcao = true;
                            attackMovement();
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
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.DARK_GRAY);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.DARK_GRAY);

        return colors;
    }
    
    private void attackMovement() {
        java.awt.Rectangle[] points = new java.awt.Rectangle[2];
        points[0] = new java.awt.Rectangle(rect);
        points[1] = new java.awt.Rectangle(rect);
        points[0].y -= 25;
        float[] durations = new float[2];
        durations[0] = durations[1] = 0.25f;
        movePath(points, durations);
        grow(2f);
    }
}
