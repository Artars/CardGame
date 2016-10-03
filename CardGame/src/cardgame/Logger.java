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
        if(parts.length == 3)
            formatado = parts[1] + " " + parts [0] + " " + parts[2];
        else
            formatado = "Batata";
        logArea.append(formatado + "\n");
        
    }
}
