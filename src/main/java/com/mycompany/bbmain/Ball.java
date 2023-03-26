/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbmain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author harid
 */
public class Ball {
    private double x,y,dx,dy;
    private int ballSize=30;
    public Ball(){
        x=200;
        y=200;
        dx=3;
        dy=5;
    }
    public void update(){
        setPostion();
    }
    public void setPostion(){
        x+=dx;
        y+=dy;
        if(x<0){
            dx=-dx;
        }
        if(y<0){
            dy=-dy;
        }
        if(x>BBMain.WIDTH-ballSize){
            dx=-dx;
        }
        if(y>BBMain.HEIGHT-ballSize){
            dy=-dy;
        }
        
    }
    public void draw(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int)x,(int)y,ballSize,ballSize);
    }
    public Rectangle getRect(){
        return new Rectangle((int)x, (int)y,ballSize,ballSize);
    }
    public void setDy(Double v_dy) {
            dy=v_dy;
    }
    public Double getDy(){
        return dy;
    }
    public Double getDx(){
        return dx;
    }
    
    public void setDx(Double v_dx){
        dx=v_dx;
    }
    
    public Double getX(){
        return x;
    }
    
    public boolean youLose(){
        boolean loser=false;
        if(y>BBMain.HEIGHT-ballSize*2){
            loser=true;
        }
        return loser;
    }
    
}
