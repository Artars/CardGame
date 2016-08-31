/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arthur
 */
public abstract class Carta implements Comparable {
    
    private int numero;
    private int naipe;
    private boolean enabled;
    private int jogador;
    private Image sprite;
    private int multiplicador;

    @Override
    public int compareTo(Object t) {
        if (t instanceof Carta){
            if (this.numero == ((Carta) t).numero){
                if (this.naipe == ((Carta) t).naipe)
                    return 0;
                return (this.naipe > ((Carta) t).naipe) ? 1:-1;
            }
            return (this.numero > ((Carta) t).numero) ? 1:-1;
        }
        return 0;
    }
    
    public enum Naipes{
        Paus,
        Ouros,
        Copas,
        Espadas;
    }
    
    private String NumeroToString(){
        switch(this.numero){
            case 1:
                return "ace";
            case 11:
                return "jack";
            case 12:
                return "queen";
            case 13:
                return "king";
            default:
                return String.valueOf(this.numero);
        }
    }
    
    private String NaipeToString(){
        switch(this.naipe){
            case 0:
                return "clubs";
            case 1:
                return "diamonds";
            case 2:
                return "hearts";
            case 3:
                return "spades";
            default:
                return "clubs";
        }
    }
    
    
    public Carta (int n) {
        this.numero = n % 13;
        this.naipe = n / 13;
        
        String imgPath = "img/" + this.NumeroToString() + "_of_" + this.NaipeToString();
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Draw(Graphics2D g) {
        
    }
    
    public abstract void ColocarCarta ();

    public int getNumero() {
        return numero;
    }

    public int getNaipe() {
        return naipe;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getJogador() {
        return jogador;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
    
}
