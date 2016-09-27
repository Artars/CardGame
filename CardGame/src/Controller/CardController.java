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
import View.Table;
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
import javax.swing.JButton;

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

    public CardController() {
        selecionados = new ArrayList<>();
        clicado = null;
        indicador = new IndicadorClicado();
    }
    
    //Adiciona o view ao controle
    public void addView(Observer view){
        this.view = (BoardFrame)view;
    }
    
    //Adiciona um model ao controle
    public void addModel(CardModel model) {
        this.model = (CardModel) model;
        UpdateWorkspaces();
    }
    
    //Passa as localizacoes e tamanhos dos retangulos para o model
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
        turno = GameManager.getInstance().getTurno();
        
        //Seleciona uma carta caso nada esteja selecionado
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
        //Nao clicou em nada, deseleciona o que for necessario
        else if (clicados.isEmpty()){
            clicado = null;
            indicador.setEnabled(false);
        }
        //Clicou em algum objeto tendo selecionado a carta
        else if (clicado != null){
            //Clicou em apenas um objeto (BoardHolder)
            if (clicados.size() == 1) {
                if (clicados.get(0) instanceof BoardHolder ) {
                    BoardHolder b = (BoardHolder) clicados.get(0);
                    if (b.getIndex(x,y) != -1)
                        ((Carta) clicado).onClick(b);
                }
            }
            //Clicou em dois objeto, o boardholder e a carta
            else if (clicados.size() > 1) {
                ((Carta) clicado).onClick((BoardHolder) clicados.get(0), (Carta) clicados.get(1));
            }
            clicado = null;
            indicador.setEnabled(false);
        }
        view.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
    
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }
    
    //Se moveu o mouse, procura todos os objetos que estão sobre ele, e comunica os objetos
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
        int turno = GameManager.getInstance().getTurno();
        
        //Clicou no botao de troca de turno
        if (ae.getActionCommand() == "Turno") {
            JButton Turno = (JButton) ae.getSource();
            
            if (Turno.getText().contains("Trocar turno")){
                clicado = null;
                
                if (Turno.getText().contains("1"))GameManager.getInstance().trocarTurno(2);
                else GameManager.getInstance().trocarTurno(1);
                
                Turno.setText("Terminar turno");
                turno = GameManager.getInstance().getTurno();
                model.trocarTurno(turno);
                view.updateTurnText(turno);
                view.repaint();
            }
            else if (Turno.getText() == "Terminar turno"){
                clicado = null;
                Turno.setText("Trocar turno " + turno);
                GameManager.getInstance().trocarTurno(0);
                model.trocarTurno(0);
                view.repaint();
            }
        }
    }
    
    //Inicia a janela principaç
    public void startMainWindow(){
        view.setMinimumSize(new Dimension(600, 400));
        view.setVisible(true);
    }
}
