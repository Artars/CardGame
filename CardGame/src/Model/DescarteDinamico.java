/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Arthur
 */
public class DescarteDinamico extends BoardHolder {
    
    private boolean over;
    
    public DescarteDinamico(Rectangle rect, int player) {
        super(rect, player);
        over = false;
    }

    public DescarteDinamico(int player) {
        super(player);
        over = false;
    }
    
    public void draw(Graphics2D g) {
        Color background = new Color(51,105,30);
        for (int i = 0; i < 5; i++) {
            Rectangle frame = getFrame(i);
            Color fillColor = Color.DARK_GRAY;
            g.setColor(fillColor);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
            g.setColor(background);
            frame = getCardRect(i);
            g.fillRect(frame.x, frame.y, frame.width, frame.height);
        }
        if(over) {
            Rectangle frame = getCardRect(0);
            g.setColor(Color.YELLOW);
            g.fillRect(rect.x, rect.y, rect.width, 3);
            g.fillRect(rect.x, rect.y + rect.height - 3, rect.width, 3);
            g.fillRect(rect.x, rect.y, 3, rect.height);
            g.fillRect(rect.x + rect.width - 3, rect.y, 3, rect.height);
        }
    }
    
   @Override
    public void onHover(int x, int y) {
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onHover(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        if(rect.inside(x,y))
            over = true;
    }

    @Override
    public void onLeave() {
        over = false;
    }
    
}
