/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bbmain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author harid
 */
public class GamePanel extends JPanel implements Runnable {
    
    //fields
    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMouseListener;
    private int mouseX;
    //entities
    Ball theBall;
    Paddle thePaddle;
    Map theMap;
    HUD theHud;
    
    
    public GamePanel(){
        
        init();
         
    }
    public void init(){
        mouseX=0;
        theBall=new Ball();
        thePaddle=new Paddle();
        theHud=new HUD();
        theMouseListener=new MyMouseMotionListener();
        theMap=new Map(6,10);
        addMouseMotionListener(theMouseListener);
        
        running=true;
        image=new BufferedImage(BBMain.WIDTH,BBMain.HEIGHT,BufferedImage.TYPE_INT_RGB);
        g=(Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void run() { //Game loop
        while(running){
            
            update();
            
            draw();
            
            repaint();
            
            try{
                Thread.sleep(10);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void checkCollision(){
        Rectangle ballRect=theBall.getRect();
        Rectangle paddleRect=thePaddle.getRect();
        if(ballRect.intersects(paddleRect)){
            theBall.setDy(-theBall.getDy());
            if(theBall.getX()<mouseX+thePaddle.getWidth()/4){
                theBall.setDx(theBall.getDx()-0.5);
            }
            if(theBall.getX()<mouseX+thePaddle.getWidth() && theBall.getX()> mouseX+thePaddle.getWidth()/4){
                theBall.setDx(theBall.getDx()+0.5);
            }
        }
        
        A: for(int row=0;row<theMap.getMapArray().length;row++){
            for(int col=0;col<theMap.getMapArray()[0].length;col++){
                if(theMap.getMapArray()[row][col]>0){
                    int brickX=theMap.HOR_PAD+col*theMap.getBrickwidth();
                    int brickY=theMap.VERT_PAD+row*theMap.getBrickHeight();
                    int brickWidth=theMap.getBrickwidth();
                    int brickHeight=theMap.getBrickHeight();

                    Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    if(ballRect.intersects(brickRect)){
                        theMap.hitBrick(row,col);
                        
                        // theBall.setDy(-theBall.getDy());
                        
                        if (theBall.getX() + 19 <= brickRect.x || theBall.getX() + 1 >= brickRect.x + brickWidth) { // if the ball goes beyond the brick then inside the brick in x direction then we change x direction
                                theBall.setDx(-theBall.getDx());
                            } else {
                                theBall.setDy(-theBall.getDy());
                            }
                        theHud.addScore(50);
                        break A;
                    }
                }
            }
        }
    }
    public void update(){
        checkCollision();
        theBall.update();
    }
    public void draw(){
        g.setColor(new Color(31, 190, 214));
        g.fillRect(0,0,BBMain.WIDTH,BBMain.HEIGHT);
        theMap.draw(g);
        theBall.draw(g);
        thePaddle.draw(g);
        theHud.draw(g);
        if(theMap.isThereAWin()==true){
            drawWin();
            running=false;
        }
        if(theBall.youLose()==true){
            drawLoser();
            running=false;
        }
    }
    public void drawWin(){
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New",Font.BOLD,50));
        g.drawString("WINNER!!!!", 200, 200);
    }
    public void drawLoser(){
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New",Font.BOLD,50));
        g.drawString("LOSER!!!!!",200,200);
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        
        
        g2.drawImage(image,0,0,BBMain.WIDTH,BBMain.HEIGHT,null);
        g2.dispose();
    }
    
    private class MyMouseMotionListener implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX=e.getX();
            thePaddle.mouseMoved(e.getX());
        }
        
    }
}
