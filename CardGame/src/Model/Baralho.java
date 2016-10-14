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
import Cartas.Renderizavel;
import cardgame.GameManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *  Classe reponsavel por gerenciar as cartas ainda não colocadas
 * @author Arthur
 */
public class Baralho implements Renderizavel{
    
    //Variaveis ----------------------------------------------------------------
    private ArrayList<Integer> cartas;
    private Image sprite;
    private Rectangle rect;

    //Construtor -------------------------------------------------------------
    public Baralho(int n) {
        cartas = new ArrayList<>();
        for(int i = 0; i < n; i++)
            cartas.add(i);
        shuffle();
        
        if(this.sprite == null){
            try {
                sprite = ImageIO.read(new File("img/back.png"));
            } catch (IOException ex) {
                Logger.getLogger(Carta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        rect = new Rectangle(0,0,74,94);
        GameManager.getInstance().adicionarRender(this, 0);
    }

    //Funcoes de implementacao obrigatoria -------------------------------------
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(sprite, rect.x, rect.y, rect.width, rect.height, null);   
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 0);
    }
    
    //Funcoes Privadas ---------------------------------------------------------
    //Embaralha as cartas
    private void shuffle() {
        Collections.shuffle(cartas);
    }
    
    //Dado um indice i, ele cria uma carta do tipo correto
    private Carta criarCarta(Integer i) {
        int n = i;
        Carta c;
        switch (n /13) {
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
        c.setRect(new Rectangle(rect));
        return c;
    }
    
    //Pega cartas do descarte caso falte do baralho
    private void fillCards() {
        ArrayList<Integer> descarte;
        descarte = GameManager.getInstance().getDescarte().getDescarteList();
        Collections.shuffle(descarte);
        cartas.addAll(descarte);
    }
    
    //Funcoes Publicas ---------------------------------------------------------
    //Retira uma lista com n cartas
    public ArrayList<Carta> retirarCartas(int n) {
        ArrayList<Carta> a = new ArrayList<>();
        
        if (cartas.size() < n)
            fillCards();
        
        for(int i=0; i < n; i++){
            int get = cartas.get(0);
            cartas.remove(0);
            a.add(criarCarta(get));
        }
        return a;
    }
    
    //Getters e setter ---------------------------------------------------------
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
    public void setRect(int x, int y, int width, int height) {
        rect = new Rectangle(x,y,width,height);
    }
    
    public void setRect(int x, int y) {
        rect.x = x;
        rect.y = y;
    }
    
}
