/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbmain;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author harid
 */
public class Map {
    private int[][] theMap;
    private int brickHeight,brickWidth;
    
    public final int HOR_PAD=80, VERT_PAD=50;
    
    public Map(int row,int col){
        initMap(row,col);
        
        brickWidth=(BBMain.WIDTH-2*HOR_PAD)/col;
        brickHeight=(BBMain.HEIGHT/2-VERT_PAD*2)/row;
    }
    
    public void initMap(int row,int col){
        theMap=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int r=(int)(Math.random()*4+1);
                theMap[i][j]=r;
            }
        }
    }
    public void draw(Graphics2D g){
        for(int row=0;row<theMap.length;row++){
            for(int col=0;col<theMap[0].length;col++){
                if(theMap[row][col]>0){
                    if(theMap[row][col]==1){
                        g.setColor(new Color(200,200,200));
                    }
                    if(theMap[row][col]==2){
                        g.setColor(new Color(150,150,150));
                    }
                    if(theMap[row][col]==3){
                        g.setColor(new Color(100,100,100));
                    }
                    if(theMap[row][col]==4){
                        g.setColor(new Color(50,50,50));
                    }
                    
                    g.fillRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD,brickWidth , brickHeight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.WHITE);
                    g.drawRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD,brickWidth , brickHeight);
                }
            }
        }
    }
    public boolean isThereAWin(){
        boolean thereIsAWin=false;
        int win_value=0;
        for(int row=0;row<theMap.length;row++){
            for(int col=0;col<theMap[0].length;col++){
                win_value+=theMap[row][col];
            }
        }
        if(win_value==0){
            thereIsAWin=true;
        }
        return thereIsAWin;
    }
    public int[][] getMapArray(){return theMap;}
    public void setBrick(int row,int col,int val){
        theMap[row][col]=val;
    }
    
    public int getBrickwidth(){return brickWidth;}
    public int getBrickHeight(){return brickHeight;}
    
    public void hitBrick(int row, int col){
        theMap[row][col]-=1;
        if(theMap[row][col]<0){
            theMap[row][col]=0;
        }
    }
    
}
