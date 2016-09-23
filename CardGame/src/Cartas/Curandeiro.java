/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Model.BoardHolder;
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
    public void onClick() {
        if (!realizouAcao)
            selecionado = true;
    }

    //Funcoes publicas ---------------------------------------------------------
    @Override
    public void onClick(BoardHolder b, Carta c) {
        Atacavel a;
        
        if(c instanceof Atacavel)
            a = (Atacavel) c;
        else
            return;
        
        if (b.getJogador() == jogador && a.isAtacavel()) {
            if (a.getNumero() == this.numero)
                multiplicador ++;
            a.recuperarVida(cura * multiplicador);
            realizouAcao = true;
            //Se descarta
            descartar();
                
        }
    }

    @Override
    public void onClick(BoardHolder b) {
        if (b.getJogador() == 0) {
            descartar();
        }
    }
    
    @Override
    public ArrayList<String> getAtributos() {
        ArrayList<String> atributos = new ArrayList<>();
        
        atributos.add("Cura: " + String.valueOf(cura));
        atributos.add("Multi.: x" + String.valueOf(multiplicador));
        
        return atributos;
    }

    @Override
    public ArrayList<Color> getAtributosColor() {
        ArrayList<Color> colors = new ArrayList<>();
        
        colors.add(Color.RED);
        colors.add(Color.DARK_GRAY);

        return colors;
    }
    
}
