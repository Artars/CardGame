/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author Arthur
 */

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

public class Logger {
    JTextArea logArea;
    ArrayList<String> commands;
    
    public Logger (JTextArea logArea) {
        this.logArea = logArea;
        commands = new ArrayList<>();
    }
    
    public void addLog(String command) {
        commands.add(command);
        formatCommand(command);
    }
    
    private void formatCommand(String command) {
        String[] parts = command.split(",");
        
        String formatado;
        switch(parts.length) {
            case 7:
                formatado = parts[0] + ": '" + parts[1] + "' em '" + parts[2] + "'";
                break;
            case 4:
                formatado =  parts[0] + ": '" + parts[1] + "'";
                break;
            case 2:
                formatado = parts[0] + " do jogador " + parts[1];
                break;
            default:
                formatado = "Batata";
        }
        logArea.append(formatado + "\n");
        
    }
    
    private String melhoraVerbo(String s) {
        return null;
    }
}
