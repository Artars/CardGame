/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import Controller.CardController;
import Model.CardModel;
import View.Background;
import View.BoardFrame;
import View.Table;
import View.TelaInicial;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Arthur
 */
public class SceneManager {
    
    JFrame actualWindow;
    Timer animationTimer;
    
    public SceneManager () {
        actualWindow = null;
        animationTimer = new Timer(10,null);
        animationTimer.start();
    }
    
    public void startMenu(){
        if(actualWindow != null)
            actualWindow.setVisible(false);
        Background b = new Background();
        TelaInicial menu = new TelaInicial(b);
        actualWindow = menu;
        menu.setVisible(true);
        menu.repaint();
    }
    
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
    
    public void redraw(){
        actualWindow.repaint();
    }
    
    public Timer getAnimator() {
        return animationTimer;
    }
    
    public void addAnimation(ActionListener a) {
        animationTimer.addActionListener(a);
        if(animationTimer.getActionListeners().length == 1)
            animationTimer.addActionListener(new ScreenRefresher(actualWindow));
    }
    
    public void removeAnimation(ActionListener a) {
        animationTimer.removeActionListener(a);
        if(animationTimer.getActionListeners().length == 1)
            animationTimer.removeActionListener((animationTimer.getActionListeners())[0]);
    }
    
    public void resetAnimator() {
        animationTimer = new Timer(10, null);
    }
}
