/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Cartas.Renderizavel;
import Main.GameManager;
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
        ArrayList<ArrayList<Renderizavel>> a = GameManager.getInstance().getRenderizaveis();
        for(int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                if (a.get(i).get(j) != null)
                    a.get(i).get(j).draw(g2);
            }
        }
    }
    
}

    

