package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

import java.util.Random;
import javax.swing.Timer;

import javax.swing.JPanel;


public class Graphics2 extends JPanel implements ActionListener{
	boolean game_working= true;
	JPanel panel = new JPanel();
	public int initial_snake_length=6;
	public char snake_direction= 'D';
	public int score;
	public int X;
	public int Y;
	int []x= new int[25];
	int []y= new int[25];
	Random random;
	Timer timer;
	
	public Graphics2() {
		random= new Random();
		this.setPreferredSize(new Dimension(600,600));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new UserKeyboard());
		Begin();
	}
	public void Begin() {
		apple();
		game_working=true;
		timer = new Timer(100,this);
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		g.setColor(Color.PINK);
		g.fillOval(X, Y, 25, 25);
		g.setColor(Color.BLUE);
		g.drawString("Score:" +score, 300, 550);
		for(int i=initial_snake_length;i>0;i--) {
				g.setColor(Color.ORANGE);
				g.fillRect(x[i], y[i], 25, 25);
		}
	}
	public void apple() {
		X=random.nextInt((int)(600/25))*25;
		Y=random.nextInt((int)(600/25))*25;
	}
	public void movement() {
		for( int i=initial_snake_length;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch (snake_direction) {
		case 'U':
			y[0]=y[0]-25;
			break;
		case 'D':
			y[0]=y[0]+25;
			break;
		case 'R':
			x[0]=x[0]+25;
			break;
		case 'L':
			x[0]=x[0]-25;
			break;
		}
	}
	public void apple_eaten() {
		if((x[0]==X)&&(y[0]==Y)) {
			initial_snake_length++;
			score++;
			apple();
		}
	}
	public void Collision() {
		for(int i= initial_snake_length;i>0;i--) {
			if((x[0]== x[i])&&(y[0]==y[i])) {
				game_working=false;
			}
		}
		if(x[0]<0) game_working=false;
		if(x[0]>600) game_working= false;
		if(y[0]<0) game_working= false;
		if(y[0]>600) game_working= false;
		
		if(game_working==false)timer.stop();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(game_working) {
			movement();
			apple_eaten();
			Collision();
		}
		repaint();
	}
	public class UserKeyboard extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent button) {
			switch (button.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(snake_direction!='R') {
					snake_direction='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(snake_direction!='L') {
					snake_direction='R';
				}
				break;
			case KeyEvent.VK_UP:
				if(snake_direction!='D') {
					snake_direction='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(snake_direction!='U') {
					snake_direction='D';
				}
				break;
			}
		}
		
	}
	
}
