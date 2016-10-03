/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class TransladationTween implements Runnable {

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
    
    public TransladationTween (Selecionavel parent) {
        this.parent = parent;
        this.targets = new ArrayList<>();
        this.durations = new ArrayList<>();
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
    }
    
    @Override
    public void run() {   
        int i = 0;
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
                catch(InterruptedException e) {
                    break;
                }
            }
            i++;
        }
        parent.getRect().setRect(targets.get(targets.size() - 1));
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
            }
            if(Math.abs(currentY) >= 1) {
                deltaRect.y = (int) currentY;
                currentY -= (int) currentY;
            }
            if(Math.abs(currentWidth) >= 1) {
                deltaRect.width = (int) currentWidth;
                currentWidth -= (int) currentWidth;
            }
            if(Math.abs(currentHeight) >= 1) {
                deltaRect.height = (int) currentHeight;
                currentHeight -= (int) currentHeight;
            }
            parent.getRect().x += deltaRect.x;
            parent.getRect().y += deltaRect.y;
            parent.getRect().width += deltaRect.width;
            parent.getRect().height += deltaRect.height;
    }
    
}
