/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import javax.swing.JTextArea;

/*
MODELO
Função, Nome1, Nome2, Tabuleiro1, Posicao1, Tabuleiro2, Posicao2

Ataque
Ataque, Nome1, Nome2, Tabuleiro1, Posicao1, Tabuleiro2, Posicao2

Curar
Curar, Nome1, Nome2, Tabuleiro1, Posicao1, Tabuleiro2, Posicao2

Mover
Mover, Nome1, Nome2, Tabuleiro1, Posicao1, Tabuleiro2, Posicao2

Descartar
Descartar, Nome1, Tabuleiro1, Posicao1

Trocar de Turno
Turno, Nome1
*/

/**
 * Classe responsável por fazer log de todas as ações realizadas
 * @author Arthur
 */
public class Logger {
    JTextArea logArea;
    ArrayList<String> commands;
    
    /**
     * Construtor que tem como parâmetro a àrea em que será escrito os logs
     * @param logArea 
     */
    public Logger (JTextArea logArea) {
        this.logArea = logArea;
        commands = new ArrayList<>();
    }
    
    /**
     * Adiciona um novo log
     * @param command 
     */
    public void addLog(String command) {
        commands.add(command);
        formatCommand(command);
    }
    
    /**
     * Formata um comando para algo legível
     * @param command 
     */
    private void formatCommand(String command) {
        String[] parts = command.split(",");
        
        String formatado;
        if(parts[0].equals("Ataque"))
            formatado = "'" + parts[1] + "' atacou '" + parts[2] + "'";
        else if (parts[0].equals("Cura"))
            formatado =  "'" + parts[1] + "' curou '" + parts[2] + "'";
        else if (parts[0].equals("Mover"))
            formatado = "'" + parts[1] + "' se moveu para a posição " + parts[2];
        else if (parts[0].equals("Trocou"))
            formatado = "'" + parts[1] + "' trocou de posicao com '" + parts[2] + "'";
        else if (parts[0].equals("Descarte"))
            formatado = "'" + parts[1] + "' foi descartado";
        else if (parts[0].equals("Turno"))
            formatado = "\n" + "--- " + "Turno do jogador " + parts[1] + " ---";
        else if (parts[0].equals("Console"))
            formatado = "\n" + "*** " + parts[1] + " ***";
        else
            formatado = null;
    
        if(formatado != null)
            logArea.append(formatado + "\n");
        
    }
}
