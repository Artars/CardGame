/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cartas.Renderizavel;
import cardgame.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Arthur
 */
public class IndicadorClicado implements Renderizavel {

    private Rectangle rect;
    private boolean enabled;
    
    public IndicadorClicado() {
        enabled = false;
    }
    
    
    @Override
    public void draw(Graphics2D g) {
        if (enabled) {
            g.setColor(new Color(255,255,0,120));
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }
    }

    @Override
    public void removeRenderer() {
        GameManager.getInstance().removerRender(this, 1);
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
            GameManager.getInstance().adicionarRender(this, 1);
        else
            removeRenderer();
    }
    
}
