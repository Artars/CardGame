/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Arthur
 */
public class Back extends JPanel {
    
    Image background;
    
    public Back (){
        background = Toolkit.getDefaultToolkit().createImage("img/background.jpg");
        System.out.println("Batata1");
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D )g;
        g2.drawImage(background, 0, 0, g.getClipBounds().width, g.getClipBounds().height, null);
        System.out.println("Batata");
    }
    
}
