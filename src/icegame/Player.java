/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icegame;

import javax.swing.JPanel;

/**
 *
 * @author gaitanesnikos
 */
public class Player extends JPanel implements Runnable {

    private int dificulty = 1;
    private Ball ball;
    private boolean isMyPlayer;
    private int score;
    private boolean hasPlayerRightSide = true;
    private MyFrame frame;
//    public Player(Ball ball) {
//        this.ball = ball;
//       
//    }

    public Player(boolean isMyPlayer, MyFrame frame) {
        this.isMyPlayer = isMyPlayer;
        this.frame = frame;
        if (isMyPlayer) {
            hasPlayerRightSide = false;
        }
    }

    @Override
    public void run() {
        ball.setPosYSpeed((getLocation().y + getHeight() / 2 - ball.getPosy() - ball.getHeight() + ball.getHeight() / 2) / 4 - 1);
        System.out.println(ball.getPosYSpeed());

    }

    public void nonSelectedPlayerAlgo() {
        if (score>=3&&isMyPlayer) {
            ball.nextLevel(true);
        }else if (score>=3&&!isMyPlayer){
        
        ball.nextLevel(false);
        }   
        if(isMyPlayer){
        return;
        }
        
        int currentDifilty = (int) (dificulty / (frame.getWidth() / (float) ball.getPosx()));


        if (ball.isGoLeft() && hasPlayerRightSide||dificulty<=1) {
            currentDifilty = 1;
        }
      
        if (ball.getPosy() - ball.getHeight() > getLocation().y && getLocation().y + getHeight() < frame.getHeight()) {

            setLocation(getLocation().x, getLocation().y + currentDifilty);
        }

        if (ball.getPosy() - ball.getHeight() < getLocation().y && ball.getPosy() + ball.getHeight() > 0) {
            setLocation(getLocation().x, getLocation().y - currentDifilty);
        }
    }
    

    public void updateScore() {
        score++;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public boolean isHavingPlayerRightSide() {
        return hasPlayerRightSide;
    }

    public void setHasPlayerRightSide(boolean hasPlayerRightSide) {
        this.hasPlayerRightSide = hasPlayerRightSide;
    }

    public boolean isIsMyPlayer() {
        return isMyPlayer;
    }

    public int getScore() {
        return score;
    }

    public int getDificulty() {
        return dificulty;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }
}
