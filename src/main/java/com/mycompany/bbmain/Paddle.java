/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbmain;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author harid
 */
public class Paddle {
    // declaration
    private double x;
    
    private int width,height;
    public final int YPOS=BBMain.HEIGHT-100;
    
    public Paddle(){
        width=100;
        height=20;
        x=BBMain.WIDTH/2-width/2;
    }
    
    public void update(){
    
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x, YPOS, width, height);
    }
    public void mouseMoved(int mouseXpos){
        x=mouseXpos;
        if(x>BBMain.WIDTH-width){
            x=BBMain.WIDTH-width;
        }
    }
    public Rectangle getRect(){
        return new Rectangle((int)x, YPOS,width,height);
    }
    
    public int getWidth(){
        return width;
    }
}
