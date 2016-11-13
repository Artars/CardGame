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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observer;
import javax.swing.JButton;

/**
 * Classe responsável por fazer o controle do jogo
 * @author Arthur
 */
public class CardController implements MouseListener, MouseMotionListener, ActionListener {

    private BoardFrame view;
    private CardModel model;
    private Selecionavel clicado;
    private ArrayList <Selecionavel> selecionados;
    private IndicadorClicado indicador;
    private TurnScreen turnScreen;
    
    private int turno = 0;
    private int jogadorAtual;

    /**
     * Construtor básico
     */
    public CardController() {
        selecionados = new ArrayList<>();
        clicado = null;
        indicador = new IndicadorClicado();
        turnScreen = new TurnScreen();
    }
    
    /**
     * Adiciona view ao controle
     * @param view 
     */
    public void addView(Observer view){
        this.view = (BoardFrame)view;
        turnScreen.setRect(this.view.getBoardPanel().getBounds());
    }
    
    /**
     * Adiciona um model ao controle
     * @param model 
     */
    public void addModel(CardModel model) {
        this.model = (CardModel) model;
        UpdateWorkspaces();
        model.ComprarDeck(1, false);
    }
    
    /**
     * Passa as localizacoes e tamanhos dos retangulos para o model
     */
    private void UpdateWorkspaces (){
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
                clicado.onClick(null);
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
            if (clicados.size() > 0) {
                clicado.onClick(clicados.toArray());
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
        if (ae.getActionCommand().equals("Turno")) {
            JButton Turno = (JButton) ae.getSource();
            clicado = null;
            indicador.setEnabled(false);
            
            if (turno > 2){
                if (turno == 3)
                    turno = 2;
                else 
                    turno = 1;
                GameManager.getInstance().trocarTurno(turno);
                Turno.setText("Terminar turno");
                turno = GameManager.getInstance().getTurno();
                GameManager.getInstance().log("Turno," + turno);
                model.trocarTurno(turno);
                view.updateTurnText(turno);
                turnScreen.setEnable(false);
                view.repaint();
            }
            else if (turno < 3){
                turno += 2;
                GameManager.getInstance().trocarTurno(turno);
                if (turno == 3) 
                    Turno.setText("Trocar para turno " + 2);
                else
                    Turno.setText("Trocar para turno " + 1);
                model.trocarTurno(turno);
                turnScreen.setEnable(true);
                view.repaint();
            }
        }
        
    else if (ae.getActionCommand().equals("Save")) {
            try
            {
              FileOutputStream f_out = new
                     FileOutputStream ("data/card.data");
              ObjectOutputStream obj_out = new
                     ObjectOutputStream (f_out);
              obj_out.writeObject (model);
              GameManager.getInstance().log("Console,Jogo salvo");
              f_out.close();
            }
            catch (IOException e)
            {
              System.out.println (e.toString ());
              System.out.println ("Can't save file");
            }
        }
        
        else if (ae.getActionCommand().equals("Load")) {
            try
            {
                FileInputStream f_in = new
                    FileInputStream ("data/card.data");
                ObjectInputStream obj_in = new
                    ObjectInputStream (f_in);
                model = (CardModel) obj_in.readObject ();
                GameManager.getInstance().clearSystems();
                model.updateGameManager();
                indicador = new IndicadorClicado();
                clicado = null;
                turnScreen = new TurnScreen(this.view.getBoardPanel().getBounds());
                turno = GameManager.getInstance().getTurno();
                view.updateTurnText(turno);
                view.repaint();
                GameManager.getInstance().log("Console,Jogo carregado");              
            }
            catch (IOException e)
            {
              System.out.println (e.toString ());
              System.out.println("Can't read file");
            }
            catch (ClassNotFoundException e) {
                System.out.println (e.toString ());
                System.out.println("Wrong type file");
            }
        }
    }
    
    /**
     * Inicia a janela principal
     */
    public void startMainWindow(){
        view.setMinimumSize(new Dimension(600, 400));
        view.setVisible(true);
    }
}
