/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbmain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author harid
 */
public class HUD {
    private int score;
    
    public HUD(){
        init();
    }
    
    public void init(){
        score=0;
    }
    
    public void draw(Graphics2D g){
        g.setFont(new Font("Courier New",Font.PLAIN,14));
        g.setColor(Color.RED);
        g.drawString("SCORE :"+score,20,20);
    }
    public int getScore(){
        return score;
    }
    public void addScore(int scoreToAdd){
        score+=scoreToAdd;
    }
    
}
