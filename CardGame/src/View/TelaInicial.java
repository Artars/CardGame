/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import cardgame.GameManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Tela inicial do jogo, com um menu de seleção para os diferentes modos de jogos
 * @author Arthur
 */
public class TelaInicial extends javax.swing.JFrame {
    
    Background board;
    
    /**
     * Cria uma nova tela inicial, tendo como parametro o background
     * @param back 
     */
    public TelaInicial(Background back) {
        initComponents();
        //jPanel2.add(back);
        init(back);
    }
    
    /**
     * Inicia a janela
     * @param board 
     */
    private void init(Background board){
      
        Dimension area = new Dimension(boardPanel.getWidth(), boardPanel.getHeight());
        
        jBInit.setVisible(false);
        
        this.board = board;
        this.board.setPreferredSize(area);//set dimensao do painel de desenho
        this.board.setBackground(new Color(124,179,66));//set cor de fundo       
        this.boardPanel.setLayout(new GridLayout(1, 1));
        
        this.boardPanel.add(board);
        board.add(jBInit, 1, 0);
        jBInit.setLocation((boardPanel.getWidth()-jBInit.getWidth())/2, 
                (boardPanel.getHeight()-jBInit.getHeight())/2);
        jBInit.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();
        jBInit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(905, 612));
        setResizable(false);

        jBInit.setText("Começar o jogo");
        jBInit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addComponent(jBInit)
                .addGap(185, 185, 185))
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jBInit)
                .addGap(137, 137, 137))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInitActionPerformed
        // TODO add your handling code here:
        GameManager.getInstance().startGame();
        this.setVisible(false);
        //this.finalize();
    }//GEN-LAST:event_jBInitActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton jBInit;
    // End of variables declaration//GEN-END:variables
}
