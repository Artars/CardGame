/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.CardController;
import Controller.MultController;
import Model.CardModel;
import View.Background;
import View.BoardFrame;
import View.BoardMult;
import View.MultiplayerChat;
import View.Table;
import View.TelaInicial;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Classe responsável por gerenciar as janelas
 * @author Arthur
 */
public class SceneManager {
    
    JFrame actualWindow;
    JFrame secondaryWindow;
    Timer animationTimer;
    
    /**
     * Construtor basico
     */
    public SceneManager () {
        actualWindow = null;
        animationTimer = new Timer(10,null);
        animationTimer.start();
    }
    
    /**
     * Abre a tela inicial
     */
    public void startMenu(){
        if(actualWindow != null)
            actualWindow.setVisible(false);
        if(secondaryWindow != null)
            secondaryWindow.setVisible(false);
        Background b = new Background();
        TelaInicial menu = new TelaInicial(b);
        actualWindow = menu;
        menu.setVisible(true);
        menu.repaint();
    }
    
    /**
     * Inicia o jogo
     */
    public void startGame() {
        if(actualWindow != null)
            actualWindow.setVisible(false);
        Table board = new Table();
        BoardFrame view = new BoardFrame(board);
        CardModel model = new CardModel();
        CardController controller = new CardController();
        controller.addView(view);
        controller.addModel(model);
        view.addController(controller);
        controller.startMainWindow();
        actualWindow = view;
    }
    
    public void startMultChat() {
        if(actualWindow != null)
            actualWindow.setVisible(false);
        MultiplayerChat chat = new MultiplayerChat();
        MultController controller = new MultController();
        controller.addChat(chat);
        actualWindow = chat;
        chat.setVisible(true);
    }
    
    public void startMultGame(){
        Table board = new Table();
        BoardMult view = new BoardMult(board);
        CardModel model = new CardModel();
        MultController controller = ((MultiplayerChat) actualWindow).getController();
        controller.addView(view);
        controller.addModel(model);
        view.addController(controller);
        controller.startMainWindow();
        secondaryWindow = actualWindow;
        actualWindow = view;
        
    }
    
    /**
     * Adiciona uma nova animação ao timer
     * @param a 
     */
    public void addAnimation(ActionListener a) {
        animationTimer.addActionListener(a);
        if(animationTimer.getActionListeners().length == 1)
            animationTimer.addActionListener(new ScreenRefresher(actualWindow));
    }
    
    /**
     * Remove animações do timer
     * @param a 
     */
    public void removeAnimation(ActionListener a) {
        animationTimer.removeActionListener(a);
        if(animationTimer.getActionListeners().length == 1)
            animationTimer.removeActionListener((animationTimer.getActionListeners())[0]);
    }
    
    /**
     * Reinicia o timer
     */
    public void resetAnimator() {
        animationTimer = new Timer(10, null);
    }
}
