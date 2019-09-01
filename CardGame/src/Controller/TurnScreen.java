/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cartas.Renderizavel;
import View.Background;
import Main.GameManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arthur
 */
public class TurnScreen implements Renderizavel{

    private Rectangle rect;
    private boolean enable;
    private Image sprite;

    /**
     * Contrutor que toma como parâmetro a tela.
     * @param rect 
     */
    public TurnScreen(Rectangle rect) {
        this.rect = rect;
        enable = false;
        try {
            sprite = ImageIO.read(new File("img/turn.png"));
        } catch (IOException ex) {
           Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameManager.getInstance().adicionarRender((Renderizavel)this, 2);
    }
    
    /**
     * Contrutor tendo como parâmetros a localização de onde ele será desenhado
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public TurnScreen(int x, int y, int width, int height) {
        this.rect = new Rectangle(x,y,width,height);
        enable = false;
        try {
            sprite = ImageIO.read(new File("img/turn.png"));
        } catch (IOException ex) {
           Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameManager.getInstance().adicionarRender((Renderizavel)this, 2);
    }
    
    /**
     * Contrutor sem argumentos
     */
    public TurnScreen() {
        enable = false;
        try {
            sprite = ImageIO.read(new File("img/turn.png"));
        } catch (IOException ex) {
           Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameManager.getInstance().adicionarRender((Renderizavel)this, 2);
    }
    
    /**
     * Retorna o retângulo
     * @return rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Define o retângulo
     * @param rect 
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
    @Override
    public void draw(Graphics2D g) {
        if (enable) {
            g.drawImage(sprite, rect.x,rect.y,rect.width,rect.height, null);
        }
    }

    @Override
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, 0);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 2);
    }

    /**
     * Torna a imagem ativa ou inativa
     * @param enable 
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
