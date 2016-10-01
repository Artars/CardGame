/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Arthur
 */
public class TransladationTween implements Runnable {

    private Selecionavel parent;
    private Rectangle target;
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
    
    public TransladationTween (Selecionavel parent) {
        this.parent = parent;
    }
    
    public void setTarget(Rectangle targetRect, float time) {
        Rectangle parRect = parent.getRect();
        target = targetRect;
        speedX = ((target.x - parRect.x) / time);
        speedY = ((target.y - parRect.y) / time);
        speedWidth = ((target.width - parRect.width) / time);
        speedHeight = ((target.height - parRect.height) / time);
        steps = (int) (time/dt);
    }
    
    @Override
    public void run() {   
        currentX = parent.getRect().x;
        currentY = parent.getRect().y;
        currentWidth = parent.getRect().width;
        currentHeight = parent.getRect().height;
        for(int i = 0; i < steps; i++) {
            currentX += speedX * dt;
            currentY += speedY * dt;
            currentWidth += speedWidth * dt;
            currentHeight += speedHeight * dt;
            parent.getRect().setRect(currentX, currentY, currentWidth, currentHeight);
            try {
                Thread.sleep((int) (dt * 1000));
            }
            catch(InterruptedException e) {
                break;
            }
        }
        parent.getRect().setRect(target);
    }
    
}
