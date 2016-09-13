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
    /*
    int cartaSlc;
    int posSlc.
    */
    
    private int turno = 0;
    private int jogadorAtual;
    
    Rectangle[] workspaces;

    public CardController() {
        workspaces = new Rectangle[25];
        /*cartaSlc = -1;
        posSlc = -1;*/
        selecionados = new ArrayList<>();
        clicado = null;
    }
    
    /*public int getWorkspace (int x, int y){
        int width = view.getBoardPanel().getWidth()/4;   
        if (x >= width && x <= width*3){
            int height = view.getBoardPanel().getHeight()/36;
            i
        }
        
    }*/
    
    public void addView(Observer view){
        this.view = (BoardFrame)view;
        this.CreateWorkspaces(workspaces);
    }
    
    public void addModel(Observer model) {
        this.model = (CardModel) model;
        UpdateWorkspaces();
    }
    
 /*   public void setcartaSlc (int cartaSlc){
        this.cartaSlc = cartaSlc;
    }

    public void setposSlc (int posSlc){
        this.posSlc = posSlc;
    }*/
    
    public void UpdateWorkspaces (){
        int i = 0;
        int j = 0;
        int n = 0;
        int width = 0;
        int height = 0;
        width =  view.getBoardPanel().getBounds().width /10;
        height = view.getBoardPanel().getBounds().height /6;
        int margem = height /6;
        Point[] locations = new Point[7];
        
        for(Rectangle reckt : workspaces) {
            reckt.reshape((5*width /2)+(n*width)+3, (margem * (i+1) + height * i)+3, width-6, height-6);
            if (j % 5 == 0)
                locations[i] = new Point((5*width /2)+(n*width), (margem * (i+1) + height * i));
            j++;
            n = j%5;
            i = j/5;
        }
        locations[5] = new Point(locations[2].x - width, locations[2].y);
        locations[6] = new Point(110,110);
        model.UpdateBoardLocations(locations, width, height);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        ArrayList <Selecionavel> clicados = GameManager.getInstance().findSelecionavel(x, y); 
        
        if (clicados.size() > 1 && clicado == null && ((Carta) clicado).isClicavel(GameManager.getInstance().getTurno())){
            clicado = clicados.get(1);
        }
        else if (clicado != null){
            //É o segundo clique
            if (clicados.size() == 1) {
                //Segundo Clique seleciona Board
                if (((Carta) clicado).getJogador() < 3 && ((BoardHolder) clicados.get(0)).getJogador() == ((Carta) clicado).getJogador()/2){
                    //Pegou da Mão e Colocou no Board
                    //clicado.onClick((BoardHolder) clicados.get(0));
                    System.out.println("Pegou da Mão e Colocou no Board");
                }
                else if (((Carta) clicado).getJogador() > 2 && ((BoardHolder) clicados.get(0)).getJogador() == ((Carta) clicado).getJogador()/2){
                    //Pegou do Board e Colocou no mesmo Board
                    System.out.println("Pegou do Board e Colocou no mesmo Board");
                }
                else if (((Carta) clicado).getJogador() > 2 && ((BoardHolder) clicados.get(0)).getJogador() != ((Carta) clicado).getJogador()/2){
                    //Pegou do Board e Colocou no outro Board
                    System.out.println("Pegou do Board e Colocou no outro Board");
                }
            }
            else if (clicados.size() == 2) {
                //Segundo Clique seleciona Carta
                if (((Carta) clicado).getJogador() > 2 && ((BoardHolder) clicados.get(0)).getJogador() == ((Carta) clicado).getJogador()/2){
                    //Pegou do Board e Colocou no mesmo Board
                    System.out.println("Pegou do Board e Colocou no mesmo Board");
                }
                else if (((Carta) clicado).getJogador() > 2 && ((BoardHolder) clicados.get(0)).getJogador() != ((Carta) clicado).getJogador()/2){
                    //Pegou do Board e Colocou no outro Board
                    System.out.println("Pegou do Board e Colocou no outro Board");
                }
            }
            if (((Carta) clicado).getJogador() < 3 && ((BoardHolder) clicados.get(0)).getJogador() == 0){
                //Pegou da Mão e Colocou no Descarte
                System.out.println("Pegou do Board e Colocou no outro Board");
            }
            clicado = null;
        }
    }
    /*
    @Override
    public void mouseClicked(MouseEvent me) {
        UpdateWorkspaces();
        int j = 0;
        Carta C = null;
        for(Rectangle reckt : workspaces) {
            if (reckt.inside(me.getX(), me.getY())) {
                System.out.println("Clicaram no retangulo " + j);
                if (j >= 5){
                    if (j < 10 && cartaSlc < 20 && cartaSlc >= 15) {
                        C = model.getBoard(1,cartaSlc%5);

                        C.Acao(model.getBoard(2,j%5));
                        System.out.println("Ataque no retangulo " + j);
                        cartaSlc = -1;
                    }
                    else if (j < 15 && j >= 10 && cartaSlc >= 20){
                        C = model.getMao(1,cartaSlc%5);

                        model.descarte(C);
                        cartaSlc = -1;
                        System.out.println("Descarte no retangulo " + j);
                    }
                    else if (j < 15 && j >= 10 && cartaSlc == -1) model.pegarCartas(1);
                    else if (j < 20 && j >= 15){
                        if (cartaSlc != -1){
                            if (cartaSlc < 20) C = model.getBoard(1,cartaSlc%5);
                            else C = model.getMao(1,cartaSlc%5);

                           C.Acao(model.getBoard(1,j%5));
                           cartaSlc = -1;
                           System.out.println("Acao no retangulo " + j);
                        }
                        else {
                            if (model.getBoard(1, j%5) != null) cartaSlc = j;
                            if (cartaSlc != -1) System.out.println("Carta selecionada no retangulo " + j);
                        } 
                    }
                    else if (j < 25 && j >= 10){
                        if (cartaSlc == -1){
                            if (model.getMao(1, j%5) != null) cartaSlc = j;
                            if (cartaSlc != -1) System.out.println("Carta selecionada no retangulo " + j);
                        }
                        else cartaSlc = -1;
                    }
                    else cartaSlc = -1;
                }
                else cartaSlc = -1;
                break;
            }
            else if (j == 24) cartaSlc = -1;
            j++;
        }
        C = null;
        view.repaint();
    }
    */
    
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
