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
import Main.GameManager;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

/**
 *  Classe reponsavel por gerenciar as cartas ainda não colocadas
 * @author Arthur
 */
public class Baralho implements Renderizavel, Serializable{
    
    //Variaveis ----------------------------------------------------------------
    private ArrayList<Integer> cartas;
    private ImageIcon sprite;
    private Rectangle rect;

    //Construtor ---------------------------------------------------------------
    /**
     * Contrutor que toma como parâmetro o numero de cartas que deverá ter no
     * baralho
     * @param n 
     */
    public Baralho(int n) {
        cartas = new ArrayList<>();
        for(int i = 0; i < n; i++)
            cartas.add(i);
        shuffle();
        
        if(this.sprite == null)
            sprite = new ImageIcon("img/back.png");
        
        rect = new Rectangle(0,0,74,94);
        GameManager.getInstance().adicionarRender(this, 0);
    }

    //Funcoes de implementacao obrigatoria -------------------------------------
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(sprite.getImage(), rect.x, rect.y, rect.width, rect.height, null);   
    }
    
    @Override
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, 0);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 0);
    }
    
    //Funcoes Privadas ---------------------------------------------------------
    /**
     * Embaralha as cartas
     */
    private void shuffle() {
        Collections.shuffle(cartas);
        String sequence = "Sincronizar";
        for(int i: cartas) {
            sequence.concat("," + String.valueOf(i));
        }
        GameManager.getInstance().log(sequence);
    }
    
    
    /**
     * Dado um indice i, ele cria uma carta do tipo correto
     * @param i
     * @return carta
     */
    private Carta criarCarta(Integer i) {
        int n = i;
        Carta c;
        switch (n /13) {
            case 0:
                c = new Atacante(n);
                break;
            case 1:
                c = new Defensor(n);
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
    
    /**
     * Pega cartas do descarte caso falte do baralho
     */
    private void fillCards() {
        ArrayList<Integer> descarte;
        descarte = GameManager.getInstance().getDescarte().getDescarteList();
        Collections.shuffle(descarte);
        cartas.addAll(descarte);
    }
    
    //Funcoes Publicas ---------------------------------------------------------
    /**
     * Retira uma lista com n cartas
     * @param n
     * @return lista
     */
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
    
    /**
     *  Sincroniza esse baralho com outro, dada uma string com todos os numeros
     * @param s 
     */
    public void sincronize(String s) {
        ArrayList<Integer> novaLista = new ArrayList<>();
        String[] parts;
        parts = s.split(",");
        for (int i = 1; i < parts.length; i++) {
            novaLista.add(Integer.parseInt(parts[i]));
        }
        cartas = novaLista;
    }
    
    public String getBaralho() {
        String baralho = "Sincronizar";
        for (int c:cartas) {
           baralho = baralho + "," + String.valueOf(c);
        }
        
        return baralho;
    }
    
    //Getters e setter ---------------------------------------------------------
    /**
     * Define a posição do baralho dado um retângulo
     * @param rect 
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
    /**
     * Define a posição do baralho dado um retângulo definido por inteiros
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public void setRect(int x, int y, int width, int height) {
        rect = new Rectangle(x,y,width,height);
    }
    
}
