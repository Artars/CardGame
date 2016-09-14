/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

/**
 *
 * @author Arthur
 */
public interface Atacavel {
    public void levarDano (int dano);
    public void recuperarVida (int cura);
    public boolean estaVivo();
    public void die();
}
