/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author gaitanesnikos
 */
public class MyFrame extends JFrame {

    private Player player1;
    private Player player2;
    static int speed = 10;
    Thread thread;
    Ball ball;

    public MyFrame() {
        init();
        add(player1);
        add(player2);


        add(ball);

        thread = new Thread(ball);
        thread.start();
        player1.setBackground(Color.red);
        player2.setBackground(Color.black);
setPlayerStartingPositions();
        setLayout(new MyLayout());
        setFocusable(true);
        addKeyListener(keyListener);
        // System.out.println(getHeight());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void init() {
         setSize(1000, 500);
        player1 = new Player(true,this);
        player2 = new Player(false,this);
        ball = new Ball(getWidth() / 2, getHeight() / 2, 25, this, player1, player2);
        player1.setBall(ball);
        player2.setBall(ball);
    }

    public void setPlayerStartingPositions() {
        if (player2.isHavingPlayerRightSide()) {
            player2.setLocation(getWidth() - getWidth() / 40, getHeight() / 2);
       
        } else {
            player2.setLocation(getWidth() / 120, getHeight() / 2);
        }
        if (player1.isHavingPlayerRightSide()) {
            player1.setLocation(getWidth() - getWidth() / 40, getHeight() / 2);
        } else {
            player1.setLocation(getWidth() / 120, getHeight() / 2);
        }
    }
    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (KeyEvent.VK_UP == e.getKeyCode()) {
               
                player1.setLocation(player1.getLocation().x, player1.getLocation().y - speed);
            }
            if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                
                player1.setLocation(player1.getLocation().x, player1.getLocation().y + speed);
            }
        
            repaint();
            validate();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.green);
        if (player1.isHavingPlayerRightSide()) {
            g.drawString(Integer.toString(player1.getScore()), getWidth()/2+getWidth()/5, getHeight()/5);
        }else{
        g.drawString(Integer.toString(player1.getScore()), getWidth()/2-getWidth()/5, getHeight()/5);
        }
        
         if (player2.isHavingPlayerRightSide()) {
            g.drawString(Integer.toString(player2.getScore()), getWidth()/2+getWidth()/5, getHeight()/5);
        }else{
        g.drawString(Integer.toString(player2.getScore()), getWidth()/2-getWidth()/5, getHeight()/5);
        }
        
    }
}
