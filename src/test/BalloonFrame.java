package test;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class BalloonFrame extends Frame implements ActionListener{

   private Button grow, shrink, left, right, close;
   private Balloon theBalloon;

   public BalloonFrame() {
      setTitle("Balloon");

      setLayout(new FlowLayout());

      grow = new Button("Grow");
      add(grow);
      grow.addActionListener(this);

      shrink = new Button("Shrink");
      add(shrink);
      shrink.addActionListener(this);

      left = new Button("Left");
      add(left);
      left.addActionListener(this);

      right = new Button("Right");
      add(right);
      right.addActionListener(this);

      close = new Button("Exit");
      add(close);
      close.addActionListener(this);
      
      theBalloon = new Balloon();

   }

   public static void main(String args[]) {
      BalloonFrame f = new BalloonFrame();
      f.setName("Balloons");
      f.setBackground(Color.CYAN);
      f.setSize(600, 600);
      f.setVisible(true);
   }

   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == grow)
         theBalloon.grow();
      if (event.getSource()  == shrink)
         theBalloon.shrink();
      if (event.getSource()  == left)
         theBalloon.moveLeft();
      if (event.getSource() == right)
         theBalloon.moveRight();
      if (event.getSource() == close)
         System.exit(0);

      repaint();
   }

   public void paint(Graphics g) {
      theBalloon.display(g);
   }
}
