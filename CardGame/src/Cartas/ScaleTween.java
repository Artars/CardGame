/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur
 */
public class ScaleTween implements Runnable{
    Selecionavel parent;
    float dt = 0.01f;
    private int steps;
    Rectangle originalRect;
    private float scale;
    private float delay;
    
    //SpeedParams
    float speedX;
    float speedY;
    float speedWidth;
    float speedHeight;
    
    //Current location paramans
    private float currentX;
    private float currentY;
    private float currentWidth;
    private float currentHeight;

    
    public ScaleTween(Selecionavel parent) {
        this.parent = parent;
        this.delay = 0;
    }
    
    public void setScale(float scale, float duration) {
        duration *= 0.5f;
        this.scale = scale;
        originalRect = new Rectangle(parent.getRect());
        
        Rectangle target = new Rectangle(originalRect);
        target.width = (int) (target.getWidth() * scale);
        target.height = (int) (target.getHeight() * scale);
        target.x = (int) (originalRect.x + (originalRect.width - target.width)/2);
        target.y = (int) (originalRect.y + (originalRect.height - target.height)/2);
        
        speedX = ((target.x - originalRect.x) / duration);
        speedY = ((target.y - originalRect.y) / duration);
        speedWidth = ((target.width - originalRect.width) / duration);
        speedHeight = ((target.height - originalRect.height) / duration);
        steps = (int) (duration/dt);
    }
    
    public void setDelay(float delay) {
        this.delay = delay;
    }

    @Override
    public void run() {   
        currentX = 0;
        currentY = 0;
        currentWidth = 0;
        currentHeight = 0;
        
        while (delay > 0) {
            try {
                Thread.sleep((int) (dt * 1000));
                delay -= dt;
            }
            catch(InterruptedException e) {
                break;
            }
        }
        
        for(int i = 0; i < steps/2; i++) {
            loopBody();
            try {
                Thread.sleep((int) (dt * 1000));
            }
            catch(InterruptedException e) {
                break;
            }
        }
        speedX *= -1;
        speedY *= -1;
        speedWidth *= -1;
        speedHeight *= -1;
        
        currentX = currentY = currentWidth = currentHeight = 0;
        for(int i = 0; i < steps/2; i++) {
            loopBody();
            try {
                Thread.sleep((int) (dt * 1000));
            }
            catch(InterruptedException ex) {
                Logger.getLogger(ScaleTween.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        
        parent.getRect().height = originalRect.height;
        parent.getRect().width = originalRect.width;
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
}
