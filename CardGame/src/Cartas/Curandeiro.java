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
 *
 * @author Arthur
 */
public class Curandeiro extends Carta {

    private int cura;

    public Curandeiro(int n) {
        super(n);
        this.cura = this.numero;
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
                            GameManager.getInstance().log(    
                            "Descarte," + this.toString() + "," +
                            this.boardParent + "," + this.index);
                        descartar();
                    }
                    break;
                
                case 2:
                    BoardHolder b1 = (BoardHolder) args[0];
                    Atacavel a = (Atacavel) args[1];
                    if (b1.getJogador() == jogador && a.isAtacavel()) {
                        if (a.getNumero() == this.numero)
                            multiplicador ++;
                        a.recuperarVida(cura * multiplicador);
                            GameManager.getInstance().log(
                            "Cura," + this.toString() + "," + a.toString() + "," +
                            this.boardParent + "," + this.index + "," +
                            a.getBoardParent() + "," + a.getIndex());
                        realizouAcao = true;
                        //Se descarta
                        descartar();

                    }
            }
        }
        catch(ClassCastException e) {
        
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
    
}
