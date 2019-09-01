/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cartas;

import Main.GameManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Classe responsavel por fazer a animação de crescimento de um objeto
 * @author Arthur
 */
public class ScaleAnimation implements ActionListener{
    Selecionavel parent;
    float dt = 0.01f;
    private int steps;
    private int maxSteps;
    Rectangle originalRect;
    private float scale;
    private float delay;
    private boolean enabled;
    private boolean shrink;
    private int delayStep;
    
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

    /**
     * Construtor da animação, recebendo como parâmetro o objeto que será
     * animado
     * @param parent 
     */
    public ScaleAnimation(Selecionavel parent) {
        this.parent = parent;
        this.delay = 0;
        enabled = false;
    }
    
    /**
     * Define a escala de crescimento e duração da animação. Após definir, a 
     * animação já iniciará
     * @param scale
     * @param duration 
     */
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
        maxSteps = (int) (duration/dt);
        steps = maxSteps;
        currentX = 0;
        currentY = 0;
        currentWidth = 0;
        currentHeight = 0;
        
        enabled = true;
        shrink = false;
        addToListener();
    }
    
    /**
     * Define quanto delay deverá ter a animação antes de começar
     * @param delay 
     */
    public void setDelay(float delay) {
        this.delayStep = (int) (delay/dt);
    }
    
    /**
     * Adiciona à animação ao timer que realizará em períodos regulares
     */
    private void addToListener(){
        GameManager.getInstance().addAnimation(this);
    }
    
    /**
     * Retira à animação do processamento
     */
    private void removeListener(){
        GameManager.getInstance().removeAnimation(this);
    }
    
    /**
     * Corpo do loop a ser utilizado na função principal
     */
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
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (delayStep > 0)
            delayStep--;
        else {
            if (enabled) {
                if(steps > 0) {
                    loopBody();
                    steps--;
                }
                if(steps < 1) {
                    if(!shrink) {
                        shrink = true;
                        speedX *= -1;
                        speedY *= -1;
                        speedWidth *= -1;
                        speedHeight *= -1;

                        steps = maxSteps;
                        currentX = currentY = currentWidth = currentHeight = 0;
                    }
                    else {
                        parent.getRect().height = originalRect.height;
                        parent.getRect().width = originalRect.width;
                        removeListener();
                    }
                }
            }
        }
    }
}
