/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cartas.Atacante;
import Cartas.Atacavel;
import Cartas.Carta;
import Cartas.Curandeiro;
import Cartas.Selecionavel;
import Model.CardModel;
import View.BoardFrame;
import View.BoardMult;
import View.MultiplayerChat;
import cardgame.GameManager;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;

/**
 *
 * @author Arthur
 */
public class MultController implements MouseListener, MouseMotionListener, ActionListener, Observer {
    private boolean host;
    private int jogador;
    private BoardMult view;
    private MultiplayerChat chat;
    private Servidor servidor;
    private CardModel model;
    private Selecionavel clicado;
    private ArrayList <Selecionavel> selecionados;
    private int turno;
    private IndicadorClicado indicador;

    public MultController() {
        selecionados = new ArrayList<>();
        clicado = null;
        indicador = new IndicadorClicado();
        host = false;
        turno = 1;
    }
    
    /**
     * Adiciona view ao controle
     * @param view 
     */
    public void addView(Observer view){
        this.view = (BoardMult)view;
    }
    
    /**
     * Adiciona um model ao controle
     * @param model 
     */
    public void addModel(CardModel model) {
        this.model = (CardModel) model;
        UpdateWorkspaces();
    }
    
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
    
    public void addChat(MultiplayerChat chat) {
        this.chat = chat;
        chat.addController(this);
    }

    public void host() {
        this.jogador = 1;
        host = true;
        servidor = new Servidor(this);
        (new Thread(servidor)).start();
    }
    
    public void conectar(String ip) {
        servidor = new Servidor(this, ip);
        (new Thread(servidor)).start();
        this.jogador = 2;
    }
    
    public void enviarComando(String s) {
        String[] parts = s.split(",");
        
        if(parts[0].equals("Fala")) {
            servidor.write(s);
        }
        else {
            if(turno == jogador) {
                servidor.write(s);
            }
        }
    }
    
    public void receberComando(String s) {
        String[] parts = s.split(",");
        if(parts != null) {
            Atacante a;
            Atacavel alvo;
            Cartas.Defensor d;
            Model.BoardHolder b1, b2;
            if(parts[0].equals("Ataque")) {
                b1 = model.getBoard(
                        Integer.parseInt(parts[3]));
                a = (Atacante) (b1.getCarta(
                        Integer.parseInt(parts[4])));
                b2 = model.getBoard(Integer.parseInt(parts[5]));
                if(parts[2].charAt(0) == 'J') {
                    a.ataque(b2, Integer.parseInt(parts[6]), true);
                }
                else {
                    alvo = (Atacavel) (b2.getCarta(
                        Integer.parseInt(parts[6])));
                    a.ataque(alvo, true);
                }
            }
            else if (parts[0].equals("Cura")) {
                b1 = model.getBoard(
                        Integer.parseInt(parts[3]));
                Curandeiro c = (Curandeiro) (b1.getCarta(
                        Integer.parseInt(parts[4])));
                b2 = model.getBoard(Integer.parseInt(parts[5]));
                if(parts[2].charAt(0) == 'J') {
                    c.curar(b2, Integer.parseInt(parts[6]));
                }
                else {
                    alvo = (Atacavel) (b2.getCarta(
                        Integer.parseInt(parts[6])));
                    c.curar(b2, alvo);
                }
            }
            else if (parts[0].equals("Mover")){
                b1 = model.getBoard(
                        Integer.parseInt(parts[3]));
                alvo = (Atacavel) (b1.getCarta(
                        Integer.parseInt(parts[4])));
                alvo.setEscondido(false);
                b2 = model.getBoard(Integer.parseInt(parts[5]));
                alvo.mover(b2, Integer.parseInt(parts[6]));
            }
            else if (parts[0].equals("Trocou")) {
                b1 = model.getBoard(
                        Integer.parseInt(parts[3]));
                d = (Cartas.Defensor) (b1.getCarta(
                        Integer.parseInt(parts[4])));
                b2 = model.getBoard(Integer.parseInt(parts[5]));
                alvo = (Atacavel) (b2.getCarta(
                        Integer.parseInt(parts[6])));
                d.trocar(b2, alvo);
            }
            else if(parts[0].equals("Descarte")) {
                b1 = model.getBoard(Integer.parseInt(parts[2]));
                Carta car = b1.getCarta(
                        Integer.parseInt(parts[3]));
                if (car != null) {
                    car.setEscondido(false);
                    car.descartar();
                }
            }
            else if(parts[0].equals("Turno")) {
                trocarTurno(Integer.parseInt(parts[1]));
            }
            else if (parts[0].equals("Console")) {
            
            }
            else if (parts[0].equals("Sincronizar")) {
                model.sincronizarBaralho(s);
                model.trocarTurno(turno, jogador);
                model.inverterTabuleiro(jogador);
            }
            else if (parts[0].equals("Fala")) {
                String textoJunto = "";
                boolean primeiro = true;
                for(int i = 2; i < parts.length; i++) {
                    if(primeiro) {
                        textoJunto = textoJunto + parts[i];
                        primeiro = false;
                    }
                    else
                        textoJunto = textoJunto + "," + parts[i];
                }
                chat.getTextArea().append(parts[1] + ": " + textoJunto + "\n");
            }
        }
    }
    
    public void conseguiuConectar() {
        chat.conectado();
        GameManager.getInstance().observeLog(this);
        if(jogador == 1) {
            String baralho = model.getBaralho();
            System.out.println(baralho);
            enviarComando(baralho);
            model.trocarTurno(turno, jogador);
            model.inverterTabuleiro(jogador);
        }
    }
    
    public void trocarTurno(int trocTurno) {
        GameManager.getInstance().log("Turno," + trocTurno);
        turno = trocTurno;
        GameManager.getInstance().trocarTurno(turno);
        view.setTurnText(turno);
        model.trocarTurno(turno, jogador);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        ArrayList <Selecionavel> clicados = GameManager.getInstance().findSelecionavel(x, y); 
        turno = GameManager.getInstance().getTurno();
        
        if(turno != jogador)
            return;
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
            if(turno == jogador) {
                int jogadorOposto = (jogador == 1)? 2:1;
                trocarTurno(jogadorOposto);
            }
            /*
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
            */
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
            }
            catch (Exception e)
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
              try {
                model = (CardModel) obj_in.readObject ();
                GameManager.getInstance().clearSystems();
                model.updateGameManager();
                indicador = new IndicadorClicado();
                clicado = null;
                //turnScreen = new TurnScreen(this.view.getBoardPanel().getBounds());
                turno = GameManager.getInstance().getTurno();
                view.updateTurnText(turno);
                view.repaint();
                GameManager.getInstance().log("Console,Jogo carregado");
              }
              catch (ClassCastException e) {
                  System.out.println("Wrong file");
              }
              
            }
            catch (Exception e)
            {
              System.out.println (e.toString ());
              System.out.println("Can't read file");
            }
        }
    }
    
    
    public void startMainWindow(){
        view.setMinimumSize(new Dimension(600, 400));
        view.setVisible(true);
    }

    @Override
    public void update(Observable o, Object o1) {
        enviarComando((String) o1);
    }
}
