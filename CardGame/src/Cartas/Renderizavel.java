/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Graphics2D;

/**
 * Interface de todos os objetos que podem ser desenhados na tela
 * @author Arthur
 */
public interface Renderizavel {
    /**
     * Função responsável por desenhar o objeto dado um graphics
     * @param g 
     */
    public abstract void draw(Graphics2D g);
    
    /**
     * Adiciona o objeto à lista de renderizaveis
     */
    public abstract void adicionarRenderer();
    /**
     * Remove o objeto da lista de renderizaveis
     */
    public abstract void removeRenderer();
}
