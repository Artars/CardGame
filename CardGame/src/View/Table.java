/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Cartas.Renderizavel;
import cardgame.GameManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Classe responsável por desenhar todos os objetos renderizaveis
 * @author Arthur
 */
public class Table extends JPanel {

    /**
     * Construtor bobo
     */
    public Table() {
    }
    
    //Sobrescrita do metodo paintComponent da classe JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //Desenha todas as camadas de renderizavel
        for(ArrayList<Renderizavel> a: GameManager.getInstance().getRenderizaveis()){ 
            for(Renderizavel r: a)
                r.draw((Graphics2D) g2);
        }
    }

    

    
}