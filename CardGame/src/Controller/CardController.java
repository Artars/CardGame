/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cartas.Carta;
import Cartas.Selecionavel;
import Model.BoardHolder;
import Model.CardModel;
import View.BoardFrame;
import cardgame.GameManager;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Arthur
 */
public class CardController implements MouseListener, MouseMotionListener, ActionListener {

    private BoardFrame view;
    private CardModel model;
    private Selecionavel clicado;
    private ArrayList <Selecionavel> selecionados;
    private IndicadorClicado indicador;
    
    private int turno = 0;
    private int jogadorAtual;
    
    Rectangle[] workspaces;

    public CardController() {
        workspaces = new Rectangle[25];
        selecionados = new ArrayList<>();
        clicado = null;
        indicador = new IndicadorClicado();
    }
    
    public void addView(Observer view){
        this.view = (BoardFrame)view;
        this.CreateWorkspaces(workspaces);
    }
    
    public void addModel(CardModel model) {
        this.model = (CardModel) model;
        UpdateWorkspaces();
    }
    
    public void UpdateWorkspaces (){
        int i = 0;
        int j = 0;
        int height = view.getBoardPanel().getBounds().height / 5;
        int width = (int) (0.7 * height);
        int margem = height /6;
        Point[] locations = new Point[7];
        
        locations[0] = new Point((11*width /2), (margem * 4 + height * 3));
        for(i = 1; i < 4; i++) {
            locations[i] = new Point((5*width /2), (margem * i + height * (i-1)));
        }
        locations[4] = new Point(0, (margem * 4 + height * 3));
        locations[5] = new Point(locations[2].x - width, locations[2].y);
        locations[6] = new Point(110,110);
        model.UpdateBoardLocations(locations, width, height);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        ArrayList <Selecionavel> clicados = GameManager.getInstance().findSelecionavel(x, y); 
        int turno = GameManager.getInstance().getTurno();
        
        if (clicados.size() > 1 && clicado == null){
            Carta c = (Carta) clicados.get(1);
            if (c.isClicavel(turno)) {
                clicado = clicados.get(1);
                indicador.setRect(clicado.getRect());
                indicador.setEnabled(true);
                clicado.onClick();
                System.out.println("Algo foi selecionado");
            }
        }
        else if (clicados.size() == 0){
            clicado = null;
            indicador.setEnabled(false);
        }
        else if (clicado != null){
            //É o segundo clique
            if (clicados.size() == 1) {
                if (clicados.get(0) instanceof BoardHolder ) {
                    BoardHolder b = (BoardHolder) clicados.get(0);
                    if (b.getIndex(x,y) != -1)
                        ((Carta) clicado).onClick(b);
                }
            }
            else if (clicados.size() > 1) {
                ((Carta) clicado).onClick((BoardHolder) clicados.get(0), (Carta) clicados.get(1));
            }
            clicado = null;
            indicador.setEnabled(false);
        }
        view.repaint();
    }
    
    public void mousePressed(MouseEvent me) {
    
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        
        for(Selecionavel s: selecionados)
            s.onLeave();
        selecionados.clear();
        
        selecionados = GameManager.getInstance().findSelecionavel(x, y);
        
        for(Selecionavel s: selecionados)
            s.onHover(x, y);
        
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Oi");
        if (ae.getActionCommand() == "Turno") {
            clicado = null;
            GameManager.getInstance().trocarTurno();
            turno = GameManager.getInstance().getTurno();
            model.ComprarDeck(turno);
            view.updateTurnText(turno);
            view.repaint();
        }
    }
    
    public void startMainWindow(){
        view.setMinimumSize(new Dimension(600, 400));
        view.setVisible(true);
    }

    private void CreateWorkspaces(Rectangle[] workspaces) {
        int width = 0;
        int height = 0;
        width =  view.getBoardPanel().getBounds().width /10;
        height = view.getBoardPanel().getBounds().height /6;
        int margem = height /6;
     
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                workspaces[i*5+j] = new Rectangle((5*width /2)+(j*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            }
        }
    }
}
