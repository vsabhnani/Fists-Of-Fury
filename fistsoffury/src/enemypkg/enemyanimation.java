/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemypkg;

import java.awt.*;//needed for graphics
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;//needed for JFrame window

public class enemyanimation extends JFrame implements ActionListener //need this to make a window
{ //open program

    Timer tm = new Timer(5, this); // "this" refers to the ActionListener at the top
    int x = 0;
    int velX =  4;
    int y = 0;
     int velY = 0;
    
    
    public enemyanimation() 
    {
        tm.start();//starts the timer and begins the animation process
    }

    public void paint(Graphics g) {
        super.paint(g);//allows for painting and drawing to take place in the frame we have created

        g.setColor(Color.blue);
        g.fillRect(x, y, 100, 100);
        tm.start();

    }

    public void actionPerformed(ActionEvent e) 
    {
        x = x +velX;
        repaint();

        
    }

    

    

   
}