/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Cartas.Carta;
import Cartas.Renderizavel;
import Cartas.Selecionavel;
import cardgame.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Arthur
 */
public class BoardHolder implements Renderizavel, Selecionavel, Serializable {
    
    //Variaveis ----------------------------------------------------------------
    protected Rectangle rect;
    protected Carta[] cartas;
    protected int jogador;
    private int destaque;
    protected int pocketWidth;
    protected int pocketHeight;
    private Player player;
    private boolean invertido;

    //Contrutores --------------------------------------------------------------
    public BoardHolder(Rectangle rect, int player) {
        this.rect = rect;
        this.jogador = player;
        this.cartas = new Carta[5];
        this.destaque = -1;
        
        GameManager.getInstance().adicionarRender((Renderizavel)this,0);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
        if (jogador == 1 || jogador == 2) {
            this.player = new Player(jogador, 100);
            GameManager.getInstance().setBar(this.player, jogador);
        }
        else
            this.player = null;
    }
    
    public BoardHolder(int player) {
        this.rect = new Rectangle(0,0,0,0);
        this.jogador = player;
        this.cartas = new Carta[5];
        this.destaque = -1;
        
        GameManager.getInstance().adicionarRender((Renderizavel)this,0);
        GameManager.getInstance().adicionarSelecionavel((Selecionavel)this);
        if (jogador == 1 || jogador == 2) {
            this.player = new Player(jogador, 100);
            GameManager.getInstance().setBar(this.player, jogador);
        }
        else
            this.player = null;
    }
    
    //Funcoes de Implementacao Obrigatoria -------------------------------------
    //Rederizavel -------
    @Override
    public void draw(Graphics2D g) {
        Color background = new Color(51,105,30);
        Color destaqueColor = new Color(120,144,156);
        g.setColor(Color.RED);
        g.fillRect(rect.x,rect.y,rect.width,rect.height);
        for (int i = 0; i < 5; i++) {
            Rectangle frame = getFrame(i);
            Color fillColor = (i != destaque) ? Color.DARK_GRAY : Color.YELLOW;
            g.setColor(fillColor);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
            g.setColor(background);
            frame = getCardRect(i);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
        }
    }
    
    @Override
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, 0);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this,0);
    }

    //Clicavel ----------
    @Override
    public boolean isInside(int x, int y) {
        return rect.contains(x, y);
    }

    @Override
    public boolean isInside(Point p) {
        return rect.contains(p);
    }

    @Override
    public void onHover(int x, int y) {
        for (int i = 0; i < 5; i++) {
            Rectangle cardZone = getCardRect(i);
            if(cardZone.contains(x,y)){
                destaque = i;
                if(invertido)
                    //destaque = 4 - destaque;
                break;
            }
        }
    }

    @Override
    public void onHover(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        for (int i = 0; i < 5; i++) {
            Rectangle cardZone = getCardRect(i);
            if(cardZone.contains(x,y)){
                destaque = i;
                if(invertido)
                    destaque = 4 - destaque;
                break;
            }
        }
    }
    
    @Override
    public void onLeave() {
        destaque = -1;
    }
    
    @Override
    public void onClick(Object[] args) {
    
    }
    
    @Override
    public Rectangle getRect() {
        return rect;
    }
    
    @Override
    public String toString(){
        return String.valueOf(jogador);
    }
    
    //Funcoes Privadas ---------------------------------------------------------
    //Ajusta o tamanho e posições das cartas
    private void ajustCard () {
        for (Carta c : cartas) {
            if (c != null) {
                c.setRect(getCardRect(c.getIndex()));
            }
        }
    }
    
    //Retorna as posições e tamanhos de cada retangulo do tabuleiro
    protected Rectangle getFrame(int n) {
        if(invertido)
            n = 4 - n;
        return new Rectangle(rect.x + n *(pocketWidth + 6), rect.y,
                pocketWidth + 6, pocketHeight + 6);
    }
    
    //Adiciona um vetor a uma lista
    private void adicionaVetorLista(Carta[] v, ArrayList<Carta> l) {
        if (v != null && l != null) {
            for (Carta c:v) {
                if (c != null)
                l.add(c);
            }
        }
    }
    
    /*
        Atualiza o multiplicador de todas as cartas
        Para isso conta com as cartas desse board e do descarte
    */
    public void atualizarMultiplicadores() {
        ArrayList<Carta> cartasOrdenadas = new ArrayList<>();
        adicionaVetorLista(cartas, cartasOrdenadas);
        Carta[] descarte = GameManager.getInstance().getDescarte().getCartas();
        adicionaVetorLista(descarte, cartasOrdenadas);
        Collections.sort(cartasOrdenadas);
        
        ArrayList<Carta> auxiliar = new ArrayList<>();
        int size = cartasOrdenadas.size();
        for(int i = 0; i < size; i++) {
            Carta c = cartasOrdenadas.get(i);
            
            if(auxiliar.isEmpty()) {
                auxiliar.add(c);
            }
            else {
                Carta outraC = auxiliar.get(0);
                if(outraC != c) {
                    if(c.getNumero() == outraC.getNumero())
                        auxiliar.add(c);
                    else {
                        for (Carta ca:auxiliar) {
                            if(ca.getJogador() != -1)
                                ca.setMultiplicador(auxiliar.size());
                        
                        }
                        auxiliar.clear();
                        auxiliar.add(c);
                    }    
                }
            }
            if (i == size-1) {
                for (Carta ca:auxiliar) {
                    if(ca.getJogador() != -1)
                        ca.setMultiplicador(auxiliar.size());                      
                }
                auxiliar.clear();
            }
        }
    }
    
    //Funcoes Publicas ---------------------------------------------------------
    //Insere carta na posição n do board
    public boolean insereCarta(Carta c, int n) {
        if(cartas[n] == null) {
            cartas[n] = c;
            cartas[n].move(getCardRect(n));
            cartas[n].setIndex(n);
            cartas[n].setBoardParent(this);
            System.out.println("Adicionado carta em " + getCardRect(n));
            if(jogador == 1 || jogador == 2)
                atualizarMultiplicadores();
            return true;
        }
        return false;
    }
    
    //Insere a carta na posicao em que o mouse se encontra sobre
    public boolean insereCarta(Carta c) {
        int n = destaque;
        if(destaque != -1 && cartas[n] == null) {
            cartas[n] = c;
            cartas[n].move(getCardRect(n));
            cartas[n].setIndex(n);
            cartas[n].setBoardParent(this);
            System.out.println("Adicionado carta em " + getCardRect(n));
            if(jogador == 1 || jogador == 2)
                atualizarMultiplicadores();
            return true;
        }
        return false;
    }
    
    //Retorna a posicao e tamanho da carta de indice n
    public Rectangle getCardRect(int n) {
        if(invertido)
            n = 4 - n;
        return new Rectangle(rect.x + n * (pocketWidth + 6) + 3,
                rect.y + 3, pocketWidth, pocketHeight);
    }
    
    //Reinicia as ações de todas as cartas
    public void resetarAcoes(){
        for (int i = 0; i < 5; i++) {
            if (cartas[i] != null)
                cartas[i].setRealizouAcao(false);
        }
    }
    
    //Retira a carta na posicao n
    public void retiraCarta(int n) {
        cartas[n] = null;
        if(jogador == 1 || jogador == 2)
            atualizarMultiplicadores();
    }
    
    //Retorna o indice da carta localizada nas coordenadas x e y. -1 se não há carta
    public int getIndex(int x, int y) {
        for(int i = 0; i < 5; i++) {
            Rectangle frame = getCardRect(i);
            if (frame.contains(x,y)) {
                    return i;
            }
        }
        return -1;
    }
    
    public int getIndex() {
        return destaque;
    }
    
    //Realiza dano no jogador
    public void levaDano(int index, int dano) {
        if(jogador == 1 || jogador == 2) {
            if (index == destaque && cartas[index] == null) {
                player.perderVida(dano);
                BlinkingAnimation b = new BlinkingAnimation(getCardRect(index));
                b.setDelay(0.375f);
                b.setDuration(1f);
            }
        }
    }
    
    public void curaJogador(int index, int cura) {
        if(jogador == 1 || jogador == 2) {
            player.recuperarVida(cura);
            BlinkingAnimation b = new BlinkingAnimation(getCardRect(index));
            b.setColor(new Color(0,0,255,120));
            b.setDelay(0.375f);
            b.setDuration(1f);
        }
    }
    
    //Determina a visibilidade das cartas
    public void cardVisibility(boolean visibility) {
        for(Carta c:cartas) {
            if(c != null)
                c.setEscondido(visibility);
        }
    }
    
    //Inverte o taabuleiro
    public void inverter() {
        invertido = !invertido;
        ajustCard();
    }
    
    public int cardCount() {
        int total = 0;
        for(Carta c:cartas) {
            if(c != null)
                total++;
        }
        return total;
    }
    
    //Getters e Setters --------------------------------------------------------    
    public Carta getCarta(int n) {
        return cartas[n];
    }   
    
    public void setRect (Rectangle r) {
        this.rect = r;
        ajustCard();
    }
    
    public void setRect (int x, int y) {
        this.rect.x = x;
        this.rect.y = y;
        ajustCard();
    }
    
    public void setRect (int x, int y, int width, int height) {
        this.rect = new Rectangle(x,y,width,height);
        ajustCard();
    }
    
    public void setRect (Point p) {
        this.rect.x = (int) p.getX();
        this.rect.y = (int) p.getY();
        ajustCard();
    }
    
    public void setPocketDimensions (int width, int height) {
        this.pocketHeight = height;
        this.pocketWidth = width;
        ajustCard();
    }

    public int getJogador() {
        return jogador;
    }
    
    public Player getPlayer() {
        return player;
    }
}

class BlinkingAnimation implements java.awt.event.ActionListener, Renderizavel {

    private Rectangle rect;
    private int steps = 0;
    private int blinkInterval = 8;
    private int blinkCounter = 0;
    private int delayStep = 0;
    private float dt = 0.01f;
    private Color color = new Color(255,0,0,200);
    private boolean isVisible = false;
    
    public BlinkingAnimation(Rectangle rect) {
        this.rect = new Rectangle(rect);
        GameManager.getInstance().adicionarRender(this, 1);
    }
    
    public void setDelay(float delay) {
        delayStep = (int) (delay/dt);
    }
    
    public void setDuration(float duration) {
        steps = (int) (duration / 0.01f);
        addToListener();
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    private void addToListener(){
        GameManager.getInstance().addAnimation(this);
    }
    
    private void removeListener(){
        GameManager.getInstance().removeAnimation(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (delayStep < 1) {
            if(steps > 0) {
                steps--;
                blinkCounter--;
                if(blinkCounter < 1) {
                    blinkCounter = blinkInterval;
                    isVisible = !isVisible;
                }
            }
            else {
                removeListener();
                removeRenderer();
            }
        }
        else
            delayStep--;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        
        if(isVisible)
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    @Override
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, 1);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 1);
    }

}
