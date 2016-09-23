/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Graphics2D;

/**
 * Interface de todas as cartas que podem ser desenhadas na tela
 * @author Arthur
 */
public interface Renderizavel {
    //Funcao responsavel por desenhar o objeto
    public abstract void draw(Graphics2D g);
    //Remove o objeto da lista de renderizaveis
    public abstract void removeRenderer();
}
