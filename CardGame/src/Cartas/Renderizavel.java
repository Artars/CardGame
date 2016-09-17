/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Graphics2D;

/**
 *
 * @author Arthur
 */
public interface Renderizavel {
    public abstract void draw(Graphics2D g);
    public abstract void removeRenderer();
}
