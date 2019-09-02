/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Main.GameManager;
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
        
        jBSingle.setVisible(false);
        
        this.board = board;
        this.board.setPreferredSize(area);//set dimensao do painel de desenho
        this.board.setBackground(new Color(124,179,66));//set cor de fundo       
        this.boardPanel.setLayout(new GridLayout(1, 1));
        
        this.boardPanel.add(board);
        board.add(jBSingle, 1, 0);
        board.add(jBMulti, 1, 0);
//        jBSingle.setLocation((boardPanel.getWidth()-jBSingle.getWidth())/2, 
//                (boardPanel.getHeight()-jBSingle.getHeight())/2);
//        jBMulti.setLocation(jBSingle.getLocation().x, jBSingle.getLocation().y + 20);
        jBSingle.setVisible(true);
        jBMulti.setVisible(true);
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
        jBSingle = new javax.swing.JButton();
        jBMulti = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jBSingle.setText("Local");
        jBSingle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBSingle.setMaximumSize(new java.awt.Dimension(100, 50));
        jBSingle.setMinimumSize(new java.awt.Dimension(200, 100));
        jBSingle.setPreferredSize(new java.awt.Dimension(100, 50));
        jBSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSingleActionPerformed(evt);
            }
        });

        jBMulti.setText("Online");
        jBMulti.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBMulti.setMaximumSize(new java.awt.Dimension(100, 50));
        jBMulti.setMinimumSize(new java.awt.Dimension(200, 100));
        jBMulti.setPreferredSize(new java.awt.Dimension(100, 50));
        jBMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMultiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addGroup(boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBMulti, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(jBSingle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jBMulti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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

    private void jBSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSingleActionPerformed
        // TODO add your handling code here:
        GameManager.getInstance().startSingleGame();
        this.setVisible(false);
        //this.finalize();
    }//GEN-LAST:event_jBSingleActionPerformed

    private void jBMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMultiActionPerformed
        // TODO add your handling code here:
        GameManager.getInstance().startMultConnection();
        this.setVisible(false);
    }//GEN-LAST:event_jBMultiActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton jBMulti;
    private javax.swing.JButton jBSingle;
    // End of variables declaration//GEN-END:variables
}
