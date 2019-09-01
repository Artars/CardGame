/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cartas.Renderizavel;
import Main.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Objeto com funcao de demonstrar o que foi selecionado
 * @author Arthur
 */
public class IndicadorClicado implements Renderizavel {

    //Variaveis ----------------------------------------------------------------
    private Rectangle rect;
    private boolean enabled;
    private int width;
    
    /**
     * Contrutor basico
     */
    public IndicadorClicado() {
        enabled = false;
        width = 6;
    }
    
    //Funcoes ------------------------------------------------------------------
    @Override
    public void draw(Graphics2D g) {
        if (enabled) {
            Rectangle dRect = new Rectangle(this.rect);
            
            dRect.x -= width / 2;
            dRect.y -= width / 2;
            dRect.width += width - 1;
            dRect.height += width - 1;
            
            
            g.setColor(new Color(118,255,3, 200));
            for(int i = 0; i < width; i++) {
                g.drawRoundRect(dRect.x + i, dRect.y + i, dRect.width - 2*i, dRect.height - 2*i, 
                        2*(width -i), 3*(width - i));
                //g.drawRoundRect(100, 100, 50, 100, 10, 10);
            }
        }
    }
    
    @Override
    public void adicionarRenderer() {
        GameManager.getInstance().adicionarRender(this, 0);
    }
    
    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 1);
    }

    /**
     * Define a sua localização e tamanho do objeto
     * @param rect 
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Torna o objeto visivel ou nao
     * @param enabled 
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
            GameManager.getInstance().adicionarRender(this, 1);
        else
            removeRenderer();
    }
    
}
