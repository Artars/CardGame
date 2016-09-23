/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *  Interface implementada por todas as cartas que podem ser interagidas com o
 *  mouse
 * @author Arthur
 */
public interface Selecionavel {
    //Retorna se as coordenadas x e y se encontram dentro do objeto
    public abstract boolean isInside(int x, int y);
    //Retorna se o ponto se encontra dentro do objeto
    public abstract boolean isInside(Point p);
    //Funcao realizada ao se clicar em um objeto
    public abstract void onClick();
    //Funcao chamada ao se passar o mouse sobre o objeto nas coordenadas x e y
    public abstract void onHover(int x, int y);
    //Funcao chamada ao se passar o mouse sobre o objeto no ponto p
    public abstract void onHover(Point p);
    //Funcao chamada quando o mouse sai de sobre o objeto
    public abstract void onLeave();
    //Retorna o retangulo com area clicavel do objeto
    public abstract Rectangle getRect();
}
