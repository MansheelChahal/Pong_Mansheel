/*
 * Author: Mansheel Chahal
 * Description: Pong game. this is the main class
 * 
 */

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener, ActionListener{ // using applet class
	
	final int WIDTH=700, HEIGHT=500; //define the height and width of the application window
	Thread thread; // ?
	humanPaddle p1; //player 1
	humanPaddle p2; //player 2
	Ball b1; // defining a ball
	boolean gameStarted; 
	boolean showInstructions=false;
	Graphics gfx;
	Image img;
	Button button1, instructionButton; 
	public void init() { //initializing method
		this.resize(WIDTH, HEIGHT);
		gameStarted=false;
		this.addKeyListener(this);
		p1 = new humanPaddle(1); 
		b1 = new Ball(); 
		p2 = new humanPaddle(2); 
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
		
		playButton();
		// making an instruction button
		instructionButton=new Button("Instructions");  
		instructionButton.setBounds(350,200,80,30);//
		add(instructionButton);
		instructionButton.addActionListener(this);
		
		
	}
	
	public void paint(Graphics g) { //
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (b1.getX()<-10 || b1.getX()>710) { // checking if the ball crosses the bounds
			gfx.setColor(Color.red);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			gfx.drawString("Game Over", 350, 250);
			
			if (b1.getX()<-10 ) { // check if the ball crosses player 1's line
				p2.increaseScore();
			}
			else if (b1.getX()>710) { // check if the ball crosses player 1's line
				p1.increaseScore();
			}
			b1.setBallAgain(); //resets the position of the ball
			playButton(); // show play button again
			// initialize paddle position
			p1.initializePaddle();
			p2.initializePaddle();
		}
		if (p1.getIntScore()==3 || p2.getIntScore()==3) {  // checking score
			gameStarted=false;
			p1.initializeScore();
			p2.initializeScore();
		}
		
		if (showInstructions) {
			gfx.setColor(Color.white);
			displayInstructions(gfx);
		}
		
		if (!gameStarted) { // at the start
			gfx.setColor(Color.cyan);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			gfx.drawString("Pong", 300, 100);
			gfx.drawString("Click Play to Begin", 270, 150);
		}
		
		else if (gameStarted){ //run the game
			remove(button1);
			remove(instructionButton);
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
			gfx.setColor(Color.GREEN);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			// displaying the score
			gfx.drawString(p1.getScore(), 100, 100); 
			gfx.drawString(p2.getScore(), 600, 100);
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g) { //update method
		paint(g);
	}

	
	public void run() { // from implemening Runnable
		
		for(;;) { //infinite loop
			if (gameStarted) {
				p1.move();
				p2.move();
				b1.move(p1, p2);
			}
			else if (!gameStarted) {
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void playButton() {
		button1=new Button("Play");  
		button1.setBounds(300,25,80,30);//
		add(button1);
		button1.addActionListener(this);
	}
	
	
	public void displayInstructions(Graphics g) {
		String s1="Player 1 controls the left paddle using W and S.";
		String s2="Player 2 controls the right paddle using Up arrow and Down arrow";
		String s3="First to 5 points wins the game";
		String s4="After 5 points, press ENTER or click the Play button to restart";
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
		g.setColor(Color.white);
		g.drawString(s1, 150, 300);
		g.drawString(s2, 150, 325);
		g.drawString(s3, 150, 350);
		g.drawString(s4, 150, 375);
	}

	// keypress functions
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted=true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(true);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p2.setUpAccel(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p2.setDownAccel(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			p1.setUpAccel(false);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			p1.setDownAccel(false);
		}
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		
	}

	public void actionPerformed(ActionEvent b) {
		// TODO Auto-generated method stub
		String sString = b.getActionCommand();
		if (sString.equals("Play")) {
			if (showInstructions==false) {
				gameStarted=true;	
			}
			System.out.println("button is pressed");
		}
		else if(sString.equals("Instructions")) {
			showInstructions=!showInstructions;
			System.out.println("instructions showing");
		}
		repaint();
	}
	
	
}
