/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import Controller.CardController;
import Model.CardModel;
import View.Back;
import View.Background;
import View.BoardFrame;
import View.Table;
import View.TelaInicial;
import javax.swing.JFrame;

/**
 *
 * @author Arthur
 */
public class SceneManager {
    
    JFrame actualWindow;
    
    public void startMenu(){
        if(actualWindow != null)
            actualWindow.setVisible(false);
        Back b = new Back();
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
    
}
