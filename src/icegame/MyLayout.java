/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icegame;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author gaitanesnikos
 */
public class MyLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(10, parent.getWidth() / 10);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(10, 50);
    }

    @Override
    public void layoutContainer(Container parent) {
        //Component []childs= new Component[parent.getComponents().length];
        //childs=parent.getComponents().clone();
        int numOfChild = parent.getComponentCount();
        for (int i = 0; i < numOfChild; i++) {
            if (parent.getComponent(i) instanceof JPanel) {
                JPanel child = (JPanel) parent.getComponent(i);
                if (parent.getComponent(i) instanceof Player) {
                   // if (child.getBackground() == Color.RED) {
                     //   child.setLocation(child.getLocation());
                     
                   // } else if (child.getBackground() == Color.black) {
                        child.setLocation(child.getLocation());

                   // }
                }

                child.setSize(parent.getPreferredSize());

                if (parent.getComponent(i) instanceof Ball) {
                    Ball ball = (Ball) parent.getComponent(i);
                    child.setLocation(child.getLocation());
                    child.setLocation(200, 200);
                    ball.setLocation(ball.getPosx(), ball.getPosy());
                    child.setSize(ball.getRadious(), ball.getRadious());
                   
                }
            }
        }
    }
}
