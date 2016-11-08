/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Não é mais utilizada, foi substituida por uma versão mais estável, que
 * é a animação.
 * @author Arthur
 */
/*
public class TransladationTween implements Runnable, ActionListener {  
    private Selecionavel parent;
    private ArrayList<Rectangle> targets;
    private ArrayList<Float> durations; 
    private float speedX;
    private float speedY;
    private float speedWidth;
    private float speedHeight;
    private float dt = 0.01f;
    private float currentX;
    private float currentY;
    private float currentWidth;
    private float currentHeight;
    private int steps;
    private float delay;
    
    private boolean targeting;
    
    public TransladationTween (Selecionavel parent) {
        this.parent = parent;
        this.targets = new ArrayList<>();
        this.durations = new ArrayList<>();
        this.delay = 0;
    }
    
    public void addTarget(Rectangle targetRect, float time) {
        targets.add(targetRect);
        durations.add(time);
    }
    
    public void setTarget(Rectangle target, float time) {
        Rectangle parRect = parent.getRect();
        speedX = ((target.x - parRect.x) / time);
        speedY = ((target.y - parRect.y) / time);
        speedWidth = ((target.width - parRect.width) / time);
        speedHeight = ((target.height - parRect.height) / time);
        steps = (int) (time/dt);
        
        currentX = 0;
        currentY = 0;
        currentWidth = 0;
        currentHeight = 0;
        targeting = false;
    }
    
    public void setDelay(float delay) {
        this.delay = delay;
    }
    
    @Override
    public void run() {   
        int i = 0;
        
        while (delay > 0) {
            try {
                Thread.sleep((int) (dt * 1000));
                delay -= dt;
            }
            catch(InterruptedException e) {
                break;
            }
        }
        
        while (i < targets.size()) {
            setTarget(targets.get(i), durations.get(i));
            currentX = 0;
            currentY = 0;
            currentWidth = 0;
            currentHeight = 0;
            for(int j = 0; j < steps; j++) {
                loopBody();
                try {
                    Thread.sleep((int) (dt * 1000));
                }
                catch(InterruptedException ex) {
                    Logger.getLogger(TransladationTween.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }
            i++;
        }
        parent.getRect().setRect(targets.get(targets.size() - 1));
        redraw();
    }
    
    private void loopBody() {
        Rectangle deltaRect = new Rectangle();
            currentX += speedX * dt;
            currentY += speedY * dt;
            currentWidth += speedWidth * dt;
            currentHeight += speedHeight * dt;
            if(Math.abs(currentX) >= 1) {
                deltaRect.x = (int) currentX;
                currentX -= (int) currentX;
                redraw();
            }
            if(Math.abs(currentY) >= 1) {
                deltaRect.y = (int) currentY;
                currentY -= (int) currentY;
                redraw();
            }
            if(Math.abs(currentWidth) >= 1) {
                deltaRect.width = (int) currentWidth;
                currentWidth -= (int) currentWidth;
                redraw();
            }
            if(Math.abs(currentHeight) >= 1) {
                deltaRect.height = (int) currentHeight;
                currentHeight -= (int) currentHeight;
                redraw();
            }
            parent.getRect().x += deltaRect.x;
            parent.getRect().y += deltaRect.y;
            parent.getRect().width += deltaRect.width;
            parent.getRect().height += deltaRect.height;
    }
    
    private void redraw() {
        cardgame.GameManager.getInstance().redraw();
    }

    private void addToListener(){
    
    }
    
    private void removeListener(){
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!targeting && targets.size() > 0) {
            setTarget(targets.get(0), durations.get(0));
            targets.remove(0);
            durations.remove(0);
        }
        
        if (steps > 0) {
            loopBody();
            steps--;
        }
        if(steps < 0) {
            parent.getRect().setRect(targets.get(targets.size() - 1));
            redraw();
            targeting = false;
        }
        
        if (!targeting && targets.size() < 1) {
            removeListener();
        }
    }
    
}
*/