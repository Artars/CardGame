/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;

/**
 *
 * @author Arthur
 */
public interface Selecionavel {
    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point p);
    public abstract void onClick();
    public abstract void onHover(int x, int y);
    public abstract void onHover(Point p);
    public abstract void onLeave();
}
