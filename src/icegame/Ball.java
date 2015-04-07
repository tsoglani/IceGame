/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author gaitanesnikos
 */
public class Ball extends JPanel implements Runnable {

    private Random random = new Random();
    private Rectangle rec = new Rectangle();
    private Player[] players = new Player[2];
    private MyFrame frame;
    private boolean goLeft;
    private int posx, posy, radious;
    private Point startingPoint;
    private int speed = 3;
    private int posYSpeed = 0;
    private boolean goUp;

    public Ball(int posx, int posy, int radius, MyFrame frame, Player player1, Player player2) {
        rec.setBounds(posx, posy, radius, radius);
        startingPoint = new Point(posx, posy);
        setBackground(Color.ORANGE);
        setPosx(posx);
        setPosy(posy);
        this.frame = frame;
        setRadious(radius);
        players[0] = player1;
        players[1] = player2;
    }

    public void startingPosition() {
        setPosx(startingPoint.x);
        setPosy(startingPoint.y);
        posYSpeed = 0;
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//    }

    @Override
    public void run() {
        goLeft = random.nextBoolean();
        while (true) {
            try {
                Thread.sleep(20);

                if (goLeft) {
                    //  setLocation(getPosx()-1,getPosy());
                    setPosx(getPosx() - speed);
                } else {
                    // setLocation(getPosx()+1,getPosy());
                    setPosx(getPosx() + speed);
                }
                if (getBounds().intersects(players[0].getBounds())) {
                    goLeft = !goLeft;
                    setPosx(getPosx() + 2 * speed);
                  
                    new Thread(players[0]).start();
                }
                if (getBounds().intersects(players[1].getBounds())) {
                    goLeft = !goLeft;
                    setPosx(getPosx() - 2 * speed);

             
                    new Thread(players[1]).start();

                }

                if (getLocation().y + posYSpeed >= frame.getHeight() - frame.getHeight() / 10) {//up and douwn
                    goUp = true;
                    if (posYSpeed < 0) {
                        posYSpeed = -posYSpeed;
                    }

                }
                if (getLocation().y - posYSpeed <= 0) {
                    goUp = false;
                    if (posYSpeed < 0) {
                        posYSpeed = -posYSpeed;
                    }
                }




                if (getLocation().x < 0) //score
                {
                    if (players[0].isHavingPlayerRightSide()) {

                        players[0].updateScore();
                    } else {
                        players[1].updateScore();
                    }
                    startingPosition();
                    System.out.println("goal");
                }
                if (getLocation().x > frame.getWidth()) //score
                {
                    if (!players[1].isHavingPlayerRightSide()) {
                        players[1].updateScore();
                    } else {
                        players[0].updateScore();
                    }
                    System.out.println("goal ");
                    startingPosition();
                }




                if (goUp) {
                    setPosy(getPosy() - posYSpeed);
                  
                } else {
                    setPosy(posYSpeed + getPosy());

                }


                players[0].nonSelectedPlayerAlgo();
                players[1].nonSelectedPlayerAlgo();




                revalidate();
                repaint();
                frame.repaint();
                frame.validate();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

    public void nextLevel(boolean passLevel) {
        try {

            for (Player player : players) {
                if (!player.isIsMyPlayer()) {
                    if (!passLevel) {

                        System.out.println(" you faild .. play again level "+player.getDificulty());
                    } else {
                        player.setDificulty(player.getDificulty() + 1);
                        System.out.println("congratulation you go to level " + (player.getDificulty()));
                    }
                }
                player.setScore(0);
            }

            frame.setPlayerStartingPositions();
            startingPosition();
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getRadious() {
        return radious;
    }

    public void setRadious(int radious) {
        this.radious = radious;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getPosYSpeed() {
        return posYSpeed;
    }

    public boolean isGoLeft() {
        return goLeft;
    }

    public void setPosYSpeed(int posYSpeed) {
        this.posYSpeed = posYSpeed;
    }
}
