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
 *  Tipo de carta capaz de curar outras cartas ou o jogador
 * @author Arthur
 */
public class Curandeiro extends Carta {

    private int cura;

    /**
     * Construtor do Curandeiro para um numero N
     * @param n 
     */
    public Curandeiro(int n) {
        super(n);
        switch(numero) {
            case 1:
                cura = 11;
                break;
            case 11:
                cura = 8;
                break;
            case 12:
                cura = 8;
                break;
            case 13:
                cura = 8;
                break;    
            default:
                cura = this.numero;
        }
        cura *= 2;
    }

    @Override
    public void onClick(Object[] args) {
        int size = 0;
        if(args != null)
            size = args.length;
        try{
            switch(size) {
                case 0:
                    if (!realizouAcao)
                        selecionado = true;
                    grow(1.25f, 0.25f);
                    break;
                    
                case 1:
                    BoardHolder b = (BoardHolder) args[0];
                    if (b.getJogador() == 0) {
                        descartar();
                    }
                    else if(b.getJogador() == this.getJogador()){
                        try {
                            int index = b.getIndex();
                            curar(b, index);
                            descartar();
                        }
                        catch(java.lang.IndexOutOfBoundsException e) {
                            System.out.println (e.toString ());
                            System.out.println("Posicao invalida");
                        }
                    }
                    break;
                
                case 2:
                    BoardHolder b1 = (BoardHolder) args[0];
                    Atacavel a = (Atacavel) args[1];
                    if (b1.getJogador() == jogador && a.isAtacavel()) {
                        curar(b1,a);
                        descartar();
                    }
                    break;
            }
        }
        catch(ClassCastException e) {
            System.out.println (e.toString ());
            System.out.println("Argumento do tipo errado");
        }
    }

    //Funcoes publicas ---------------------------------------------------------    
    @Override
    public ArrayList<String> getAtributos() {
        ArrayList<String> atributos = new ArrayList<>();
        
        atributos.add("Curandeiro");
        atributos.add("Cura: " + String.valueOf(cura));
        atributos.add("Multi.: x" + String.valueOf(multiplicador));
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.DARK_GRAY);
        colors.add(Color.RED);
        colors.add(Color.DARK_GRAY);

        return colors;
    }
    
    /**
     * Realiza uma cura sobre uma carta a, presente no board b
     * @param b
     * @param a 
     */
    public void curar(BoardHolder b, Atacavel a) {
        for(int i = 0; i < 5; i++) {
            Carta c = b.getCarta(i);
            if(c != null && c.getNumero() == this.numero) {
                multiplicador = c.getMultiplicador() + 1;
                break;
            }
        }
        a.recuperarVida(cura * multiplicador);
            GameManager.getInstance().log(
            "Cura," + this.toString() + "," + a.toString() + "," +
            this.boardParent + "," + this.index + "," +
            a.getBoardParent() + "," + a.getIndex());
        realizouAcao = true;
        //Se descarta
    }
    
    /**
     * Realiza uma cura sobre um tabuleiro na posição index
     * @param b
     * @param index 
     */
    public void curar(BoardHolder b, int index) {
        if (index != -1 && b.getCarta(index) == null) {
            for(int i = 0; i < 5; i++) {
                Carta c = b.getCarta(i);
                if(c != null && c.getNumero() == this.numero) {
                    multiplicador = c.getMultiplicador() + 1;
                    break;
                }
            }
            
            b.curaJogador(index, cura * multiplicador);
            GameManager.getInstance().log(
            "Cura," + this.toString() + ",Jogador " + b.getJogador() + "," +
            this.boardParent + "," + this.index + "," +
            b + "," + index);
            
            realizouAcao = true;
        }
    }
    
}
