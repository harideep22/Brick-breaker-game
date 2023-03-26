/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bbmain;

import javax.swing.JFrame;

/**
 *
 * @author harid
 */
public class BBMain {

    public static final int WIDTH=640 ,HEIGHT=480;
    public static void main(String[] args) {
        
        JFrame theFrame=new JFrame("BRICK BREAKER");
        
        GamePanel thePanel=new GamePanel();
        
        Thread theThread=new Thread(thePanel);
        
        theFrame.setLocation(500,200); 
        theFrame.setResizable(false);
        theFrame.setSize(WIDTH,HEIGHT);
        
        theFrame.add(thePanel);
        theThread.start();
        
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        
    }
}
