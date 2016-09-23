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
 * Objeto com funcao de demonstrar o que foi selecionado
 * @author Arthur
 */
public class IndicadorClicado implements Renderizavel {

    //Variaveis ----------------------------------------------------------------
    private Rectangle rect;
    private boolean enabled;
    
    //Construtor
    public IndicadorClicado() {
        enabled = false;
    }
    
    //Funcoes ------------------------------------------------------------------
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

    //Define a sua localização e tamanho do objeto
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    //Torna o objeto visivel ou nao
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
            GameManager.getInstance().adicionarRender(this, 1);
        else
            removeRenderer();
    }
    
}
