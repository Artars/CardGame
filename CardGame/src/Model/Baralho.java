/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Atacante;
import Cartas.Carta;
import Cartas.Curandeiro;
import Cartas.Defensor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arthur
 */
public class Baralho {
    
    private ArrayList<Integer> cartas;
    private Image sprite;

    public Baralho(int n) {
        cartas = new ArrayList<>();
        for(int i = 0; i < n; i++)
            cartas.add(new Integer(i));
        shuffle();
        
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File("img/back.png"));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void shuffle() {
        Collections.shuffle(cartas);
    }
    
    private Carta criarCarta(Integer i) {
        int n = i.intValue();;
        Carta c;
        switch (n/13) {
            case 0:
                c = new Defensor(n);
                break;
            case 1:
                c = new Atacante(n);
                break;
            case 2:
                c = new Curandeiro(n);
                break;
            case 3:
                c = new Atacante(n);
                break;
            default:
                c = new Defensor(n);
        }
        return c;
    }
    
    public ArrayList<Carta> retirarCartas(int n) {
        ArrayList<Carta> a = new ArrayList<>();
        
        for(int i=0; i < n; i++){
            int get = cartas.get(0);
            cartas.remove(0);
            a.add(criarCarta(get));
        }
        return a;
    }
    
    public void Draw(Graphics2D g, int x, int y) {
        int sizeHeight = g.getClip().getBounds().height / 6 - 6;
        int sizeWidth = g.getClip().getBounds().width / 10 - 6;
        
        sizeHeight = 94;
        sizeWidth = 74;
        
        g.drawImage(sprite, x, y, sizeWidth, sizeHeight, null);   
    }
    
}
